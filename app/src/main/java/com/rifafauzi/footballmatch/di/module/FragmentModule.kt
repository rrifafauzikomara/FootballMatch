package com.rifafauzi.footballmatch.di.module

import com.rifafauzi.footballmatch.ui.detailleague.DetailLeagueFragment
import com.rifafauzi.footballmatch.ui.leagues.LeaguesFragment
import com.rifafauzi.footballmatch.ui.nextmatch.NextMatchFragment
import com.rifafauzi.footballmatch.ui.previousmatch.PreviousMatchFragment
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

}