package com.seisekulov.whackamole.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seisekulov.whackamole.domain.GetBestScoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    getBestScoreUseCase: GetBestScoreUseCase
) : ViewModel() {

    private val _bestScore = MutableLiveData(0)
    val bestScore: LiveData<Int> = _bestScore

    init {
        _bestScore.value = getBestScoreUseCase()
    }
}