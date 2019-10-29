package com.rifafauzi.footballmatch.di.module

import com.rifafauzi.footballmatch.data.local.room.LeaguesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by rrifafauzikomara on 2019-10-29.
 */
 
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideLeaguesListDao(db: LeaguesDatabase) = db.getAllLeagues()

}