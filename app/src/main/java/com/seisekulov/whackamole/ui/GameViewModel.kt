package com.seisekulov.whackamole.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seisekulov.whackamole.domain.GetBestScoreUseCase
import com.seisekulov.whackamole.domain.SetBestScoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val getBestScoreUseCase: GetBestScoreUseCase,
    private val setBestScoreUseCase: SetBestScoreUseCase
) : ViewModel() {

    private val _bestScore = MutableLiveData(0)
    val bestScore: LiveData<Int> = _bestScore

    init {
        _bestScore.value = getBestScoreUseCase()
    }

    fun setScore(value:Int){
        setBestScoreUseCase(value)
    }
}