package com.rifafauzi.footballmatch.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.rifafauzi.footballmatch.FootballApp
import com.rifafauzi.footballmatch.data.local.room.LeaguesDatabase
import com.rifafauzi.footballmatch.data.remote.ApiService
import com.rifafauzi.footballmatch.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by rrifafauzikomara on 2019-10-29.
 */

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: FootballApp): Context = app

    @Provides
    @Singleton
    fun provideApplications(app: FootballApp): Application = app

    @Provides
    @Singleton
    fun provideDatabase(context: Context) = Room.databaseBuilder(context, LeaguesDatabase::class.java, DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun providesSharedPreference(sharedPreferences: SharedPreferences): SharedPreferences.Editor = sharedPreferences.edit()
}