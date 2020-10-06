package com.gk.app.android.testingviewmodels.main

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.gk.app.android.testingviewmodels.R
import com.gk.app.android.testingviewmodels.mainFragmentFactoryFake
import com.gk.app.android.testingviewmodels.ui.main.MainActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@SmallTest
@RunWith(AndroidJUnit4::class)
class MainActivityUnitTests {

    private lateinit var activityScenario : ActivityScenario<MainActivity>
    private lateinit var activity : MainActivity

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity {
            activity = it
        }
    }

    @Test
    fun mainFragment_isDisplayed() {
        // GIVEN - A MainActivity
        activity.setFragmentFactory(mainFragmentFactoryFake)

        // WHEN - Activity is created then resumed, correct app version is shown
        activityScenario.moveToState(Lifecycle.State.CREATED)
        activityScenario.moveToState(Lifecycle.State.RESUMED)

        // THEN
        Espresso.onView(withId(R.id.mainFragment)).check(matches(isDisplayed()))
    }
}