package com.rifafauzi.footballmatch.di.module

import android.app.Application
import android.content.Context
import com.rifafauzi.footballmatch.FootballApp
import com.rifafauzi.footballmatch.api.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by rrifafauzikomara on 2019-11-22.
 */

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: FootballApp) : Context = app

    @Provides
    @Singleton
    fun provideApplications(app : FootballApp) : Application = app

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

}