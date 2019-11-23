package com.rifafauzi.footballmatch.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rifafauzi.footballmatch.ui.detailleague.DetailLeagueViewModel
import com.rifafauzi.footballmatch.ui.leagues.LeaguesViewModel
import com.rifafauzi.footballmatch.utils.ViewModelKey
import com.rifafauzi.footballmatch.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by rrifafauzikomara on 2019-11-22.
 */

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory : ViewModelFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LeaguesViewModel::class)
    internal abstract fun providesLeaguesViewModel(viewModel : LeaguesViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailLeagueViewModel::class)
    internal abstract fun providesDetailLeagueViewModel(viewModel: DetailLeagueViewModel) : ViewModel

}