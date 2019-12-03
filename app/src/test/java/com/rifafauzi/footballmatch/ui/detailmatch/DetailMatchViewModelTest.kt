package com.rifafauzi.footballmatch.ui.detailmatch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.rifafauzi.footballmatch.FakeData
import com.rifafauzi.footballmatch.common.Result
import com.rifafauzi.footballmatch.model.match.Match
import com.rifafauzi.footballmatch.model.match.MatchResponse
import com.rifafauzi.footballmatch.model.teams.Team
import com.rifafauzi.footballmatch.model.teams.TeamResponse
import com.rifafauzi.footballmatch.repository.match.MatchRepository
import com.rifafauzi.footballmatch.repository.teams.TeamsRepository
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
class DetailMatchViewModelTest {

    @Rule
    @JvmField
    var rule  = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var matchRepository: MatchRepository

    @Mock
    lateinit var teamsRepository: TeamsRepository

    @Mock
    lateinit var matchViewModel: DetailMatchViewModel

    private val matchObs = mock<Observer<Result<List<Match>>>>()
    private val teamObs = mock<Observer<Result<List<Team>>>>()

    private val data = FakeData.getDummyListMatch()[0]
    private val dataHome = FakeData.getDummyListTeam()[0]
    private val dataAway = FakeData.getDummyListTeam()[1]

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        matchViewModel = DetailMatchViewModel(matchRepository, teamsRepository)
    }

    @Test
    fun getDetailMatch() {
        val response = MatchResponse(listOf(
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
        ), listOf())
        Mockito.`when`(matchRepository.getDetailMatch(data.idEvent)).thenReturn(Observable.just(response))
        matchViewModel.getDetailMatch(data.idEvent)
        with(matchViewModel) {
            detailMatch.observeForever(matchObs)
            verify(matchRepository).getDetailMatch(data.idEvent)
        }
    }

    @Test
    fun getDetailTeamHome() {
        val response = TeamResponse(
            listOf(
                Team(
                    dataHome.idTeam,
                    dataHome.strTeamBadge
                )
            )
        )
        Mockito.`when`(teamsRepository.getTeam(dataHome.idTeam)).thenReturn(Observable.just(response))
        matchViewModel.getDetailTeamHome(dataHome.idTeam)
        with(matchViewModel) {
            teamHome.observeForever(teamObs)
            verify(teamsRepository).getTeam(dataHome.idTeam)
        }
    }

    @Test
    fun getDetailTeamAway() {
        val response = TeamResponse(
            listOf(
                Team(
                    dataAway.idTeam,
                    dataAway.strTeamBadge
                )
            )
        )
        Mockito.`when`(teamsRepository.getTeam(dataAway.idTeam)).thenReturn(Observable.just(response))
        matchViewModel.getDetailTeamAway(dataAway.idTeam)
        with(matchViewModel) {
            awayTeam.observeForever(teamObs)
            verify(teamsRepository).getTeam(dataAway.idTeam)
        }
    }
}