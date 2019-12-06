package com.rifafauzi.footballmatch.di.module

import com.rifafauzi.footballmatch.api.ApiService
import com.rifafauzi.footballmatch.repository.leagues.LeaguesRepository
import com.rifafauzi.footballmatch.repository.match.MatchRepository
import com.rifafauzi.footballmatch.repository.player.PlayerRepository
import com.rifafauzi.footballmatch.repository.standings.StandingsRepository
import com.rifafauzi.footballmatch.repository.teams.TeamsRepository
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

    @Provides
    @Singleton
    fun provideMatchRepository(apiService: ApiService) = MatchRepository(apiService)

    @Provides
    @Singleton
    fun provideTeamRepository(apiService: ApiService) = TeamsRepository(apiService)

    @Provides
    @Singleton
    fun providePlayerRepository(apiService: ApiService) = PlayerRepository(apiService)

    @Provides
    @Singleton
    fun provideStandingRepository(apiService: ApiService) = StandingsRepository(apiService)

}