package com.seisekulov.whackamole.domain

import com.seisekulov.whackamole.domain.data.ScoreRepository
import javax.inject.Inject

class GetBestScoreUseCase @Inject constructor(
    private val scoreRepository: ScoreRepository
) {
    operator fun invoke(): Int {
        return scoreRepository.getScore()
    }
}