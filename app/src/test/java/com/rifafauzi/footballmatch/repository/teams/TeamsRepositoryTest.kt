package com.rifafauzi.footballmatch.repository.teams

import com.rifafauzi.footballmatch.FakeData
import com.rifafauzi.footballmatch.utils.mock
import com.rifafauzi.footballmatch.model.teams.TeamResponse
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
class TeamsRepositoryTest {

    @Mock
    lateinit var repository: TeamsRepository

    private val data = FakeData.getDummyListMatch()[0]

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = Mockito.mock(TeamsRepository::class.java)
    }

    @Test
    fun checkNullListTeam() {
        val list = repository.getTeam(data.idHomeTeam)
        Assert.assertNull(list)
    }

    @Test
    fun getTeam() {
        val list = mock<Observable<TeamResponse>>()

        Mockito.`when`(repository.getTeam(data.idHomeTeam)).thenReturn(list)

        val result = repository.getTeam(data.idHomeTeam)
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$list] must be matches on each other!",
            result, CoreMatchers.`is`(list)
        )
    }
}