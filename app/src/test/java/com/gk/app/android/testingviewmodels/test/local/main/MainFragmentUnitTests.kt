package com.gk.app.android.testingviewmodels.test.local.main

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.gk.app.android.testingviewmodels.MainViewModelFake
import com.gk.app.android.testingviewmodels.R
import com.gk.app.android.testingviewmodels.ui.main.MainFragment
import com.gk.app.android.testingviewmodels.ui.test.TestFragmentActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@SmallTest
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MainFragmentUnitTests {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    // TODO fragment scenario not working with Hilt for now, using an ActivityScenario with a special
    // activity: use FragmentScenario when fixed
    //private lateinit var fragmentScenario: FragmentScenario<MainFragment>
    private lateinit var activityScenario: ActivityScenario<TestFragmentActivity>
    private var fragment: MainFragment? = null
    private lateinit var activity: TestFragmentActivity

    @Before
    fun setUp() {
        TestFragmentActivity.fragmentFactory = MainFragment.Factory(MainViewModelFake())
        TestFragmentActivity.fragmentClass = MainFragment::class.java
        activityScenario = ActivityScenario.launch(TestFragmentActivity::class.java).onActivity {
            activity = it
            fragment = (activity.fragment as MainFragment?)
        }
    }

    @Test
    fun mainButton_isDisplayed() {
        // GIVEN - A resumed MainFragment
        activityScenario.moveToState(Lifecycle.State.CREATED)
        activityScenario.moveToState(Lifecycle.State.RESUMED)

        assert(fragment != null)
        assert(fragment!!.isResumed)

        // WHEN -

        // THEN
        Espresso.onView(withId(R.id.mainBtn)).check(matches(isDisplayed()))
    }
}