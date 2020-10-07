package com.gk.app.android.testingviewmodels.test.local.main

import androidx.fragment.app.testing.FragmentScenario
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
import com.gk.app.android.testingviewmodels.test.common.CustomFragmentScenario
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

    // TODO fragment scenario not working with Hilt for now, using an CustomFragmentScenario with a special
    // activity: use FragmentScenario when fixed

    private lateinit var customFragmentScenario: CustomFragmentScenario
    private var fragment: MainFragment? = null

    @Before
    fun setUp() {
        customFragmentScenario = CustomFragmentScenario.launch(
            fragmentFactory = MainFragment.Factory(MainViewModelFake()),
            fragmentClass = MainFragment::class.java
        )
        customFragmentScenario.onFragment { fragment = it as MainFragment}
    }

    @Test
    fun mainButton_isDisplayed() {
        // GIVEN - A resumed MainFragment
        customFragmentScenario.moveToState(Lifecycle.State.CREATED)
        customFragmentScenario.moveToState(Lifecycle.State.RESUMED)

        assert(fragment != null)
        assert(fragment!!.isResumed)

        // WHEN -

        // THEN
        Espresso.onView(withId(R.id.mainBtn)).check(matches(isDisplayed()))
    }
}