package com.seisekulov.whackamole.domain.data

import javax.inject.Inject

class ScoreRepositoryImpl @Inject constructor(
    private val preferencesManager: PreferencesManager
) : ScoreRepository {
    override fun getScore(): Int {
        return preferencesManager.getScore()
    }

    override fun setScore(value: Int) {
        preferencesManager.setScore(value)
    }
}