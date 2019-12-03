package com.rifafauzi.footballmatch.ui.leagues

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
 * Created by rrifafauzikomara on 2019-12-02.
 */


@RunWith(MockitoJUnitRunner::class)
class LeaguesViewModelTest {

    @Rule
    @JvmField
    var rule  = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var leaguesRepository: LeaguesRepository

    @Mock
    lateinit var leaguesViewModel: LeaguesViewModel

    private val leaguesObs = mock<Observer<Result<List<Leagues>>>>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        leaguesViewModel = LeaguesViewModel(leaguesRepository)
    }

    @Test
    fun getListLeagues() {
        val data = FakeData.getDummyListLeagues()[0]
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
        Mockito.`when`(leaguesRepository.getListLeagues()).thenReturn(Observable.just(response))
        leaguesViewModel.getListLeagues()
        with(leaguesViewModel) {
            leagues.observeForever(leaguesObs)
            verify(leaguesRepository).getListLeagues()
        }
    }
}