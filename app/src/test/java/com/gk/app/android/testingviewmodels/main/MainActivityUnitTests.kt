package com.gk.app.android.testingviewmodels.main

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gk.app.android.testingviewmodels.R
import com.gk.app.android.testingviewmodels.ui.main.MainActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityUnitTests {

    @Before
    fun setUp() {

    }

    @Test
    fun someTest() {
        // GIVEN
        val activityScenario = ActivityScenario.launch(
            MainActivity::class.java
        )

        // WHEN - Activity is created then resumed, correct app version is shown
        activityScenario.moveToState(Lifecycle.State.CREATED)
        activityScenario.moveToState(Lifecycle.State.RESUMED)

        // THEN
        Espresso.onView(withId(R.id.mainBtn)).check(matches(isDisplayed()))
    }
}