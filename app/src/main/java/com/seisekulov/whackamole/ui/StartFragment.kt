package com.seisekulov.whackamole.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.seisekulov.whackamole.R
import com.seisekulov.whackamole.databinding.FragmentStartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment : Fragment(R.layout.fragment_start) {
    private val viewModel by viewModels<StartViewModel>()
    private val viewBinding by viewBinding(FragmentStartBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.playButton.setOnClickListener { findNavController().navigate(R.id.gameFragment) }
        viewModel.bestScore.observe(viewLifecycleOwner) {
            viewBinding.bestScoreValue.text = it.toString()
        }
    }
}