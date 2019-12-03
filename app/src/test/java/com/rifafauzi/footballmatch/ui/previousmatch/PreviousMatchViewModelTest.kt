package com.rifafauzi.footballmatch.ui.previousmatch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.rifafauzi.footballmatch.FakeData
import com.rifafauzi.footballmatch.common.Result
import com.rifafauzi.footballmatch.model.match.Match
import com.rifafauzi.footballmatch.model.match.MatchResponse
import com.rifafauzi.footballmatch.repository.match.MatchRepository
import com.rifafauzi.footballmatch.utils.RxImmediateSchedulerRule
import com.rifafauzi.footballmatch.utils.mock
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by rrifafauzikomara on 2019-12-03.
 */

@RunWith(MockitoJUnitRunner::class)
class PreviousMatchViewModelTest {

    @Rule
    @JvmField
    var rule  = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var matchRepository: MatchRepository

    @Mock
    lateinit var previousMatchViewModel: PreviousMatchViewModel

    private val data = FakeData.getDummyListMatch()[1]
    private val leagues = FakeData.getDummyListLeagues()[0]
    private val matchObs = mock<Observer<Result<List<Match>>>>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        previousMatchViewModel = PreviousMatchViewModel(matchRepository)
    }

    @Test
    fun getPrevMatch() {
        val response = MatchResponse(
            listOf(
                Match(
                    data.idEvent,
                    data.strHomeTeam,
                    data.strAwayTeam,
                    data.dateEvent,
                    data.intHomeScore,
                    data.intAwayScore,
                    data.strLeague,
                    data.strHomeGoalDetails,
                    data.strAwayGoalDetails,
                    data.strHomeLineupSubstitutes,
                    data.strAwayLineupSubstitutes,
                    data.strHomeLineupGoalkeeper,
                    data.strAwayLineupGoalkeeper,
                    data.strHomeLineupDefense,
                    data.strAwayLineupDefense,
                    data.strHomeLineupMidfield,
                    data.strAwayLineupMidfield,
                    data.strHomeLineupForward,
                    data.strAwayLineupForward,
                    data.idHomeTeam,
                    data.idAwayTeam,
                    data.strSport
                )
            ),
            listOf()
        )
        Mockito.`when`(matchRepository.getPrevMatch(leagues.idLeague)).thenReturn(Observable.just(response))
        previousMatchViewModel.getPrevMatch(leagues.idLeague)
        with(previousMatchViewModel) {
            prevMatch.observeForever(matchObs)
            verify(matchRepository).getPrevMatch(leagues.idLeague)
        }
    }
}