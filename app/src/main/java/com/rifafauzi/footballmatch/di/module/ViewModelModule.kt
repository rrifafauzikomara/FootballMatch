package com.rifafauzi.footballmatch.di.module

import androidx.lifecycle.ViewModel
import com.rifafauzi.footballmatch.ui.leagues.LeaguesViewModel
import com.rifafauzi.footballmatch.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by rrifafauzikomara on 2019-11-22.
 */

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LeaguesViewModel::class)
    internal abstract fun providesHomeViewModel(viewModel : LeaguesViewModel) : ViewModel

}