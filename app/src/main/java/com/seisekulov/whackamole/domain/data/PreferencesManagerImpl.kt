package com.seisekulov.whackamole.domain.data

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PreferencesManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : PreferencesManager {

    private val preferences = context.getSharedPreferences(BEST_SCORE_NAME, Context.MODE_PRIVATE)

    override fun getScore(): Int {
        return preferences.getInt(BEST_SCORE_KEY, 0)
    }

    override fun setScore(value: Int) {
        preferences.edit {
            putInt(BEST_SCORE_KEY, value)
        }
    }

    companion object {
        private const val BEST_SCORE_NAME = "BEST_SCORE_NAME"
        private const val BEST_SCORE_KEY = "BEST_SCORE_KEY"
    }
}