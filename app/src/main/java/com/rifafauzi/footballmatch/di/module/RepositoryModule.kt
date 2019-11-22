package com.rifafauzi.footballmatch.di.module

import com.rifafauzi.footballmatch.api.ApiService
import com.rifafauzi.footballmatch.repository.LeaguesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by rrifafauzikomara on 2019-11-22.
 */

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideLeaguesRepository(apiService: ApiService) = LeaguesRepository(apiService)

}