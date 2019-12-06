package com.rifafauzi.footballmatch.di.module

import com.rifafauzi.footballmatch.ui.detailleague.DetailLeagueFragment
import com.rifafauzi.footballmatch.ui.detailmatch.DetailMatchFragment
import com.rifafauzi.footballmatch.ui.detailplayer.PlayerDetailFragment
import com.rifafauzi.footballmatch.ui.detailteam.DetailTeamFragment
import com.rifafauzi.footballmatch.ui.leagues.LeaguesFragment
import com.rifafauzi.footballmatch.ui.nextmatch.NextMatchFragment
import com.rifafauzi.footballmatch.ui.player.PlayerListFragment
import com.rifafauzi.footballmatch.ui.previousmatch.PreviousMatchFragment
import com.rifafauzi.footballmatch.ui.searchmatch.SearchMatchFragment
import com.rifafauzi.footballmatch.ui.searchteam.SearchTeamFragment
import com.rifafauzi.footballmatch.ui.standings.StandingsFragment
import com.rifafauzi.footballmatch.ui.teamoverview.TeamOverviewFragment
import com.rifafauzi.footballmatch.ui.teams.TeamFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by rrifafauzikomara on 2019-11-22.
 */

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributesLeaguesFragment(): LeaguesFragment

    @ContributesAndroidInjector
    abstract fun contributesDetailLeagueFragment(): DetailLeagueFragment

    @ContributesAndroidInjector
    abstract fun contributesNextMatchFragment(): NextMatchFragment

    @ContributesAndroidInjector
    abstract fun contributesPreviousMatchFragment(): PreviousMatchFragment

    @ContributesAndroidInjector
    abstract fun contributesSearchMatchFragment() : SearchMatchFragment

    @ContributesAndroidInjector
    abstract fun contributesDetailMatchFragment() : DetailMatchFragment

    @ContributesAndroidInjector
    abstract fun contributesPlayerDetailFragment() : PlayerDetailFragment

    @ContributesAndroidInjector
    abstract fun contributesDetailTeamFragment() : DetailTeamFragment

    @ContributesAndroidInjector
    abstract fun contributesPlayerListFragment() : PlayerListFragment

    @ContributesAndroidInjector
    abstract fun contributesSearchTeamFragment() : SearchTeamFragment

    @ContributesAndroidInjector
    abstract fun contributesStandingsFragment() : StandingsFragment

    @ContributesAndroidInjector
    abstract fun contributesTeamOverViewFragment() : TeamOverviewFragment

    @ContributesAndroidInjector
    abstract fun contributesTeamFragment() : TeamFragment

}