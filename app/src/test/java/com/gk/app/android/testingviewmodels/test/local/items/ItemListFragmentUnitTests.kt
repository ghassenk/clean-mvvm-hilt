package com.gk.app.android.testingviewmodels.test.local.items

import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.gk.app.android.testingviewmodels.ItemListViewModelFake
import com.gk.app.android.testingviewmodels.test.common.CustomFragmentScenario
import com.gk.app.android.testingviewmodels.ui.items.ItemListFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@SmallTest
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class ItemListFragmentUnitTests {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    // TODO fragment scenario not working with Hilt for now, using an CustomFragmentScenario with a special
    // activity: use FragmentScenario when fixed

    private lateinit var customFragmentScenario: CustomFragmentScenario
    private var fragment: ItemListFragment? = null

    @Before
    fun setUp() {
        customFragmentScenario = CustomFragmentScenario.launch(
            fragmentFactory = ItemListFragment.Factory(ItemListViewModelFake()),
            fragmentClass = ItemListFragment::class.java
        )
        customFragmentScenario.onFragment { fragment = it as ItemListFragment }
    }

    @Test
    fun mainButton_isDisplayed() {
        // GIVEN - A resumed MainFragment
        customFragmentScenario.moveActivityToState(Lifecycle.State.CREATED)
        customFragmentScenario.moveActivityToState(Lifecycle.State.RESUMED)
        assert(fragment != null)
        assert(fragment!!.isResumed)

        // WHEN -

        // THEN
        //Espresso.onView(withId(R.id.mainBtn)).check(matches(isDisplayed()))
    }
}