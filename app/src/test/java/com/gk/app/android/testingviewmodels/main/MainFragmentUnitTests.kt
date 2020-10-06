package com.gk.app.android.testingviewmodels.main

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.gk.app.android.testingviewmodels.R
import com.gk.app.android.testingviewmodels.mainFragmentFactoryFake
import com.gk.app.android.testingviewmodels.ui.main.MainFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@SmallTest
@RunWith(AndroidJUnit4::class)
class MainFragmentUnitTests {

    private lateinit var fragmentScenario : FragmentScenario<MainFragment>
    private lateinit var fragment : MainFragment

    @Before
    fun setUp() {
        fragmentScenario = FragmentScenario.launch(
            MainFragment::class.java,
            Bundle(),
            mainFragmentFactoryFake
        )
        fragmentScenario.onFragment {
            fragment = it
        }
    }

    @Test
    fun mainButton_isDisplayed() {
        // GIVEN - A MainFragment

        // WHEN - Activity is created then resumed, correct app version is shown
        fragmentScenario.moveToState(Lifecycle.State.CREATED)
        fragmentScenario.moveToState(Lifecycle.State.RESUMED)

        // THEN
        Espresso.onView(withId(R.id.mainBtn)).check(matches(isDisplayed()))
    }
}