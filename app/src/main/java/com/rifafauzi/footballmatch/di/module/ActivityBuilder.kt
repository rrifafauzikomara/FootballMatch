package com.rifafauzi.footballmatch.di.module

import com.rifafauzi.footballmatch.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by rrifafauzikomara on 2019-10-29.
 */
 
@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector
    abstract fun bindMainActivity() : MainActivity
}