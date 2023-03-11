package com.seisekulov.whackamole.di

import com.seisekulov.whackamole.domain.data.PreferencesManager
import com.seisekulov.whackamole.domain.data.PreferencesManagerImpl
import com.seisekulov.whackamole.domain.data.ScoreRepository
import com.seisekulov.whackamole.domain.data.ScoreRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class Module {

    @Binds
    abstract fun bindScoreRepository(scoreRepositoryImpl: ScoreRepositoryImpl): ScoreRepository

    @Binds
    abstract fun bindPreferencesManager(preferencesManagerImpl: PreferencesManagerImpl): PreferencesManager
}