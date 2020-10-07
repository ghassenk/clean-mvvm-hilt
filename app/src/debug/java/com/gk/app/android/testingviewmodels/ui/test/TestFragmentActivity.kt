package com.gk.app.android.testingviewmodels.ui.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import com.gk.app.android.testingviewmodels.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * An Activity used for testing fragment to work around FragmentScenario issue
 * (here: https://dagger.dev/hilt/testing)
 * This Activity is accessible only in debug source set.
 */

@AndroidEntryPoint
class TestFragmentActivity : FragmentActivity() {

    companion object {
        lateinit var fragmentFactory: FragmentFactory
        lateinit var fragmentClass: Class<out Fragment>
    }

    val fragment: Fragment? by lazy {
        supportFragmentManager.findFragmentById(R.id.container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Call before super
        supportFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_fragment)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragmentClass, intent.extras)
                .commitNow()
        }
    }
}