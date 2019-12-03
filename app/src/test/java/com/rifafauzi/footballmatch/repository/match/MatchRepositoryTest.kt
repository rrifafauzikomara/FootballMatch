package com.rifafauzi.footballmatch.repository.match

import com.rifafauzi.footballmatch.FakeData
import com.rifafauzi.footballmatch.utils.mock
import com.rifafauzi.footballmatch.model.match.MatchResponse
import io.reactivex.Observable
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by rrifafauzikomara on 2019-12-02.
 */

@RunWith(MockitoJUnitRunner::class)
class MatchRepositoryTest {

    @Mock
    lateinit var repository: MatchRepository

    private val data = FakeData.getDummyListLeagues()[0]

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = Mockito.mock(MatchRepository::class.java)
    }

    @Test
    fun checkNullListNextMatch() {
        val list = repository.getNextMatch(data.idLeague)
        Assert.assertNull(list)
    }

    @Test
    fun checkNullListPrevMatch() {
        val list = repository.getPrevMatch(data.idLeague)
        Assert.assertNull(list)
    }

    @Test
    fun checkNullListDetailMatch() {
        val list = repository.getDetailMatch(data.idLeague)
        Assert.assertNull(list)
    }

    @Test
    fun checkNullListSearchMatch() {
        val list = repository.searchMatch("a")
        Assert.assertNull(list)
    }

    @Test
    fun getNextMatch() {
        val list = mock<Observable<MatchResponse>>()

        Mockito.`when`(repository.getNextMatch(data.idLeague)).thenReturn(list)

        val result = repository.getNextMatch(data.idLeague)
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$list] must be matches on each other!",
            result, CoreMatchers.`is`(list)
        )
    }

    @Test
    fun getPrevMatch() {
        val list = mock<Observable<MatchResponse>>()

        Mockito.`when`(repository.getPrevMatch(data.idLeague)).thenReturn(list)

        val result = repository.getPrevMatch(data.idLeague)
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$list] must be matches on each other!",
            result, CoreMatchers.`is`(list)
        )
    }

    @Test
    fun getDetailMatch() {
        val list = mock<Observable<MatchResponse>>()

        Mockito.`when`(repository.getDetailMatch(data.idLeague)).thenReturn(list)

        val result = repository.getDetailMatch(data.idLeague)
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$list] must be matches on each other!",
            result, CoreMatchers.`is`(list)
        )
    }

    @Test
    fun searchMatch() {
        val list = mock<Observable<MatchResponse>>()

        Mockito.`when`(repository.searchMatch("a")).thenReturn(list)

        val result = repository.searchMatch("a")
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$list] must be matches on each other!",
            result, CoreMatchers.`is`(list)
        )
    }
}