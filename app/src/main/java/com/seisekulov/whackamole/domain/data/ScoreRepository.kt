package com.seisekulov.whackamole.domain.data

interface ScoreRepository {
    fun getScore(): Int

    fun setScore(value: Int)
}