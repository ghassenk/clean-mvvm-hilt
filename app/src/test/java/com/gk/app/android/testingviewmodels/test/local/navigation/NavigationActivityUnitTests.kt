package com.gk.app.android.testingviewmodels.test.local.navigation

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.gk.app.android.testingviewmodels.R
import com.gk.app.android.testingviewmodels.ui.navigation.NavigationActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * MainActivity is only responsible for showing MainFragment
 */
@SmallTest
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class NavigationActivityUnitTests {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var activityScenario: ActivityScenario<NavigationActivity>

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(NavigationActivity::class.java)
    }

    @Test
    fun homeFragment_isDisplayed() {
        // GIVEN - A MainActivity

        // WHEN - Activity is created then resumed, correct app version is shown
        activityScenario.moveToState(Lifecycle.State.CREATED)
        activityScenario.moveToState(Lifecycle.State.RESUMED)

        // THEN
        Espresso.onView(withId(R.id.homeFragment)).check(matches(isDisplayed()))
    }
}