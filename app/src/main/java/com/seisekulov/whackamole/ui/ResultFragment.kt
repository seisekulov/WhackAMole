package com.seisekulov.whackamole.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.seisekulov.whackamole.R
import com.seisekulov.whackamole.databinding.FragmentResultBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment : Fragment(R.layout.fragment_result) {

    private val viewModel by viewModels<ResultViewModel>()
    private val viewBinding by viewBinding(FragmentResultBinding::bind)
    private val navArgs: ResultFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.score.text = navArgs.score.toString()

        viewModel.bestScore.observe(viewLifecycleOwner){
            viewBinding.bestScore.text=it.toString()
        }

        viewBinding.menuButton.setOnClickListener {
            findNavController().popBackStack(R.id.startFragment,true)
            findNavController().navigate(R.id.startFragment) }
        viewBinding.playAgainButton.setOnClickListener { findNavController().navigate(R.id.gameFragment)
       }
    }
}