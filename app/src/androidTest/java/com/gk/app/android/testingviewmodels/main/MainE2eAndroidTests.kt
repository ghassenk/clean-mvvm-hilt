package com.gk.app.android.testingviewmodels.main

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.gk.app.android.testingviewmodels.R
import com.gk.app.android.testingviewmodels.ui.main.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * MainActivity is only responsible for showing MainFragment
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MainE2eAndroidTests {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)
//
//    private lateinit var activityScenario: ActivityScenario<MainActivity>
//    private lateinit var activity: MainActivity

    @Before
    fun setUp() {
//        activityScenario = ActivityScenario.launch(MainActivity::class.java)
//        activityScenario.onActivity {
//            activity = it
//        }
    }

    @Test
    fun mainFragment_isDisplayed() {
//        // GIVEN - A MainActivity
//
//
//        // WHEN - Activity is created then resumed, correct app version is shown
//        activityScenario.moveToState(Lifecycle.State.CREATED)
//        activityScenario.moveToState(Lifecycle.State.RESUMED)
//
//        // THEN
//        Espresso.onView(withId(R.id.mainFragment)).check(matches(isDisplayed()))
    }
}