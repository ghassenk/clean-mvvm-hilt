package com.gk.app.android.testingviewmodels.test.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import com.gk.app.android.testingviewmodels.ui.test.TestFragmentActivity

/**
 * An FragmentScenario used for testing fragment to work around FragmentScenario issue
 * (here: https://dagger.dev/hilt/testing)
 */
class CustomFragmentScenario private constructor() {

    private lateinit var activityScenario: ActivityScenario<TestFragmentActivity>
    private lateinit var activity: TestFragmentActivity
    private var fragment: Fragment? = null


    companion object {
        fun launch(
            fragmentFactory: FragmentFactory,
            fragmentClass: Class<out Fragment>
        ): CustomFragmentScenario {
            TestFragmentActivity.fragmentFactory = fragmentFactory
            TestFragmentActivity.fragmentClass = fragmentClass

            val fragmentScenario = CustomFragmentScenario()
            val activityScenario = ActivityScenario.launch(TestFragmentActivity::class.java)
            fragmentScenario.activityScenario = activityScenario
            activityScenario.onActivity {
                fragmentScenario.activity = it
                fragmentScenario.fragment = it.fragment
            }

            return fragmentScenario
        }
    }

    fun moveActivityToState(state: Lifecycle.State) {
        activityScenario = activityScenario.moveToState(state)
    }

    fun onFragment(lambda: (Fragment) -> Unit) {
        fragment?.let { lambda.invoke(it) }
    }
}