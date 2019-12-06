package com.rifafauzi.footballmatch.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rifafauzi.footballmatch.di.factory.ViewModelFactory
import com.rifafauzi.footballmatch.ui.detailleague.DetailLeagueViewModel
import com.rifafauzi.footballmatch.ui.detailmatch.DetailMatchViewModel
import com.rifafauzi.footballmatch.ui.detailplayer.PlayerDetailViewModel
import com.rifafauzi.footballmatch.ui.detailteam.DetailTeamViewModel
import com.rifafauzi.footballmatch.ui.leagues.LeaguesViewModel
import com.rifafauzi.footballmatch.ui.nextmatch.NextMatchViewModel
import com.rifafauzi.footballmatch.ui.player.PlayerListViewModel
import com.rifafauzi.footballmatch.ui.previousmatch.PreviousMatchViewModel
import com.rifafauzi.footballmatch.ui.searchmatch.SearchMatchViewModel
import com.rifafauzi.footballmatch.ui.searchteam.SearchTeamViewModel
import com.rifafauzi.footballmatch.ui.standings.StandingsViewModel
import com.rifafauzi.footballmatch.ui.teamoverview.TeamOverviewViewModel
import com.rifafauzi.footballmatch.ui.teams.TeamViewModel
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
    internal abstract fun bindViewModelFactory(factory : ViewModelFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LeaguesViewModel::class)
    internal abstract fun providesLeaguesViewModel(viewModel : LeaguesViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailLeagueViewModel::class)
    internal abstract fun providesDetailLeagueViewModel(viewModel: DetailLeagueViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NextMatchViewModel::class)
    internal abstract fun providesNextMatchViewModel(viewModel: NextMatchViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PreviousMatchViewModel::class)
    internal abstract fun providesPreviousMatchViewModel(viewModel: PreviousMatchViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailMatchViewModel::class)
    internal abstract fun providesDetailMatchViewModel(viewModel: DetailMatchViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchMatchViewModel::class)
    internal abstract fun providesSearchMatchViewModel(viewModel: SearchMatchViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PlayerDetailViewModel::class)
    internal abstract fun providesPlayerDetailViewModel(viewModel: PlayerDetailViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailTeamViewModel::class)
    internal abstract fun providesDetailTeamViewModel(viewModel: DetailTeamViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PlayerListViewModel::class)
    internal abstract fun providesPlayerListViewModel(viewModel: PlayerListViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchTeamViewModel::class)
    internal abstract fun providesSearchTeamViewModel(viewModel: SearchTeamViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StandingsViewModel::class)
    internal abstract fun providesStandingsViewModel(viewModel: StandingsViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TeamOverviewViewModel::class)
    internal abstract fun providesTeamOverviewViewModel(viewModel: TeamOverviewViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TeamViewModel::class)
    internal abstract fun providesTeamViewModel(viewModel: TeamViewModel) : ViewModel

}