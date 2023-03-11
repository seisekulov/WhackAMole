package com.seisekulov.whackamole.domain

import com.seisekulov.whackamole.domain.data.ScoreRepository
import javax.inject.Inject

class SetBestScoreUseCase @Inject constructor(
    private val scoreRepository: ScoreRepository
) {
    operator fun invoke(value: Int) {
        return scoreRepository.setScore(value)
    }
}