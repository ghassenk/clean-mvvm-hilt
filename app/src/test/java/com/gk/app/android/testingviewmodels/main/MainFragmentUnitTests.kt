package com.gk.app.android.testingviewmodels.main

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.gk.app.android.testingviewmodels.MainViewModelFake
import com.gk.app.android.testingviewmodels.R
import com.gk.app.android.testingviewmodels.ui.main.MainFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@SmallTest
@RunWith(AndroidJUnit4::class)
class MainFragmentUnitTests {

    private lateinit var fragmentScenario: FragmentScenario<MainFragment>
    private var fragment: MainFragment? = null

    @Before
    fun setUp() {
        val factory = MainFragment.Factory(MainViewModelFake())

        // Use launchFragmentInContainer() instead of launch()
        fragmentScenario = launchFragmentInContainer<MainFragment>(
            factory = factory
        )

        fragmentScenario.onFragment {
            fragment = it
        }

        fragmentScenario.moveToState(Lifecycle.State.CREATED)
        fragmentScenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun mainButton_isDisplayed() {
        // GIVEN - A resumed MainFragment
        assert(fragment != null)
        assert(fragment!!.isResumed)

        // WHEN -

        // THEN
        Espresso.onView(withId(R.id.mainBtn)).check(matches(isDisplayed()))
        //Espresso.onView(withText("Click Me")).check(matches(isDisplayed()))
    }
}