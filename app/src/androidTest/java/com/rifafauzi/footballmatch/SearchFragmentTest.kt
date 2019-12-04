package com.rifafauzi.footballmatch

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.runner.AndroidJUnit4
import com.rifafauzi.footballmatch.ui.leagues.LeaguesFragment
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by rrifafauzikomara on 2019-12-03.
 */

@RunWith(AndroidJUnit4::class)
class SearchFragmentTest {

    @Test
    fun goToSearchMatchFragment() {
        // Create a mock NavController
        val mockNavController = mock(NavController::class.java)

        // Create a graphical FragmentScenario for the TitleScreen
        val openLeaguesFragment = launchFragmentInContainer<LeaguesFragment>()
        openLeaguesFragment.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }

        // Verify that performing a click prompts the correct Navigation action
        onView(withId(R.id.rvLeagues)).check(matches(isDisplayed()))
        onView(withId(R.id.rvLeagues)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        verify(mockNavController).navigate(R.id.detailLeagueFragment)
    }

}