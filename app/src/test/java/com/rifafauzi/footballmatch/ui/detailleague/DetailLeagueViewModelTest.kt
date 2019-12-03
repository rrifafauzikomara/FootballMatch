package com.rifafauzi.footballmatch.ui.detailleague

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.rifafauzi.footballmatch.FakeData
import com.rifafauzi.footballmatch.common.Result
import com.rifafauzi.footballmatch.model.leagues.Leagues
import com.rifafauzi.footballmatch.model.leagues.LeaguesResponse
import com.rifafauzi.footballmatch.repository.leagues.LeaguesRepository
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
 * Created by rrifafauzikomara on 2019-11-28.
 */

@RunWith(MockitoJUnitRunner::class)
class DetailLeagueViewModelTest {

    @Rule
    @JvmField
    var rule  = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var leaguesRepository: LeaguesRepository

    @Mock
    lateinit var leaguesDetailLeagueViewModel: DetailLeagueViewModel

    private val data = FakeData.getDummyListLeagues()[0]
    private val leaguesObs = mock<Observer<Result<List<Leagues>>>>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        leaguesDetailLeagueViewModel = DetailLeagueViewModel(leaguesRepository)
    }

    @Test
    fun getDetailLeague() {
        val response = LeaguesResponse(
            listOf(),
            listOf(
                Leagues(
                    data.idLeague,
                    data.strLeague,
                    data.dateFirstEvent,
                    data.strBanner,
                    data.strSport,
                    data.strDescriptionEN,
                    data.strBadge,
                    data.strCountry
                )
            )
        )
        Mockito.`when`(leaguesRepository.getDetailLeague(data.idLeague)).thenReturn(Observable.just(response))
        leaguesDetailLeagueViewModel.getDetailLeague(data.idLeague)
        with(leaguesDetailLeagueViewModel) {
            detailLeague.observeForever(leaguesObs)
            verify(leaguesRepository).getDetailLeague(data.idLeague)
        }
    }
}