package com.rifafauzi.footballmatch

import android.widget.AutoCompleteTextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.rifafauzi.footballmatch.ui.main.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock


/**
 * Created by rrifafauzikomara on 2019-12-03.
 */

@RunWith(AndroidJUnit4::class)
class SearchFragmentTest {

    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    private lateinit var stringEmpty: String
    private lateinit var stringToBeTyped: String

    @Before
    fun initValidString() {
        // Specify a valid string.
        stringEmpty = "aaa"
        stringToBeTyped = "Arsenal"
    }

    @Test
    fun goToSearchMatchFragment() {
        // init NavController
        mock(NavController::class.java)

        // display RV in leagues fragment
        onView(withId(R.id.rvLeagues)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(R.id.rvLeagues)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        // open detail league and show data
        onView(withId(R.id.layoutDetailLeagues)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(R.id.btnSearchMatch)).perform(click())

        // open search match and search match empty
        onView(isAssignableFrom(AutoCompleteTextView::class.java)).perform(typeText(stringEmpty))
            .perform(pressImeActionButton())
        Thread.sleep(1000)


        // remove text in search view
        onView(isAssignableFrom(AutoCompleteTextView::class.java)).perform(clearText())
            .perform(pressImeActionButton())
        Thread.sleep(1000)


        // search again with close the keyboard
        onView(isAssignableFrom(AutoCompleteTextView::class.java)).perform(typeText(stringToBeTyped), closeSoftKeyboard())
            .perform(pressImeActionButton())
        Thread.sleep(1000)
    }
}