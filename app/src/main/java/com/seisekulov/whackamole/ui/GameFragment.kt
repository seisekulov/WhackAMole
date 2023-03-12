package com.seisekulov.whackamole.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.seisekulov.whackamole.R
import com.seisekulov.whackamole.databinding.FragmentGameBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class GameFragment() : Fragment(R.layout.fragment_game) {
    private val viewModel by viewModels<GameViewModel>()
    private val viewBinding by viewBinding(FragmentGameBinding::bind)
    private val navArgs : GameFragmentArgs by navArgs()

    private lateinit var timer: CountDownTimer
    private var defaultValue : Long = 750
    private var score = 0
    private val arrayImages: Array<Array<FrameLayout?>> = Array(3) { arrayOfNulls(3) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        defaultValue=navArgs.levelValue
        viewBinding.timer.text = getString(R.string.time, String.format("%02d", DURATION / INTERVAL))



        timer = object : CountDownTimer(DURATION, defaultValue) {

            override fun onTick(millisUntilFinished: Long) {
                setGameView(millisUntilFinished)
            }

            override fun onFinish() {
                viewModel.setScore(score)
                findNavController().navigate(GameFragmentDirections.actionGameFragmentToResultFragment(score))
            }
        }.start()

        viewModel.bestScore.observe(viewLifecycleOwner) {
            viewBinding.score.text = it.toString()
        }
    }

    override fun onPause() {
        timer.cancel()
        super.onPause()
    }

    private fun setGameView(millisUntilFinished: Long) {
        val randomRow = Random.nextInt(HOLE_ROWS)
        val randomColumn = Random.nextInt(HOLE_COLUMNS)

        viewBinding.timer.text = getString(R.string.time, String.format("%02d", millisUntilFinished / 1000))

        for (i in 0 until HOLE_ROWS) {
            for (j in 0 until HOLE_COLUMNS) {
                val frameLayout = FrameLayout(requireContext())
                val imageView = ImageView(requireContext())
                val params = TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT,
                    1.0f
                )

                params.setMargins(10, 10, 10, 10)
                frameLayout.layoutParams = params
                frameLayout.setBackgroundResource(R.drawable.grass)

                if (i == randomRow && j == randomColumn) {
                    imageView.setImageResource(R.drawable.mole)
                    imageView.setOnClickListener {
                        score++
                        viewBinding.score.text = getString(R.string.score, score.toString())
                        imageView.setImageResource(R.drawable.whack)
                        imageView.isEnabled = false
                    }
                    imageView.shakeView()
                }

                frameLayout.addView(imageView)
                arrayImages[i][j] = frameLayout
            }
        }

        viewBinding.tableLayout.removeAllViewsInLayout()

        for (i in 0 until HOLE_ROWS) {
            val tableRow = TableRow(requireContext())
            tableRow.layoutParams = TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                1.0f
            )
            tableRow.gravity = Gravity.CENTER
            for (j in 0 until HOLE_COLUMNS) {
                tableRow.addView(arrayImages[i][j], j)
            }

            viewBinding.tableLayout.addView(tableRow, i)
        }
    }

    private fun View.shakeView() {
        ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, 30F, 0F, 30F).apply {
            interpolator = AccelerateDecelerateInterpolator()
            duration = 500
            start()
        }
    }

    companion object {
        const val DEFAULT_VALUE : Long = 750
        const val DURATION: Long = 30000
        const val INTERVAL: Long = 1000
        const val HOLE_ROWS = 3
        const val HOLE_COLUMNS = 3
    }
}