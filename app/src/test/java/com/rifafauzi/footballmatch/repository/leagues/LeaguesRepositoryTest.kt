package com.rifafauzi.footballmatch.repository.leagues

import com.rifafauzi.footballmatch.FakeData
import com.rifafauzi.footballmatch.utils.mock
import com.rifafauzi.footballmatch.model.leagues.LeaguesResponse
import io.reactivex.Observable
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by rrifafauzikomara on 2019-12-02.
 */

@RunWith(MockitoJUnitRunner::class)
class LeaguesRepositoryTest {

    @Mock
    lateinit var repository: LeaguesRepository

    private val data = FakeData.getDummyListLeagues()[0]

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = mock(LeaguesRepository::class.java)
    }

    @Test
    fun list_checkNullList() {
        val list = repository.getListLeagues()
        Assert.assertNull(list)
    }

    @Test
    fun detail_checkNullList() {
        val detail = repository.getDetailLeague(data.idLeague)
        Assert.assertNull(detail)
    }

    @Test
    fun getListLeagues() {
        val list = mock<Observable<LeaguesResponse>>()

        Mockito.`when`(repository.getListLeagues()).thenReturn(list)

        val result = repository.getListLeagues()
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$list] must be matches on each other!",
            result, CoreMatchers.`is`(list)
        )
    }

    @Test
    fun getDetailLeague() {
        val detail = mock<Observable<LeaguesResponse>>()

        Mockito.`when`(repository.getDetailLeague(data.idLeague)).thenReturn(detail)

        val result = repository.getDetailLeague(data.idLeague)
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$detail] must be matches on each other!",
            result, CoreMatchers.`is`(detail)
        )
    }
}