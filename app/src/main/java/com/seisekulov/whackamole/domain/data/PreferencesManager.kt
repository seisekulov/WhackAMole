package com.seisekulov.whackamole.domain.data

interface PreferencesManager {
    fun getScore(): Int

    fun setScore(value: Int)
}