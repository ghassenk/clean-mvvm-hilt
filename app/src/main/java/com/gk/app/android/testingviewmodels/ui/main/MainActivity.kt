package com.gk.app.android.testingviewmodels.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentFactory
import com.gk.app.android.testingviewmodels.R
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.annotations.TestOnly

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var fragmentFactory: FragmentFactory? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.v(javaClass.simpleName, "onCreate()")

        // Fragment Factory must be prepared before super.onCreate()
        prepareFragmentFactory()

        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment::class.java, intent.extras)
                .commitNow()
        }
    }

    override fun onDestroy() {
        Log.v(javaClass.simpleName, "onDestroy()")
        super.onDestroy()
    }

    /**
     * Call before super.onCreate()
     */
    private fun prepareFragmentFactory() {
        if (fragmentFactory == null) {
            fragmentFactory = MainFragment.Factory()
        }
        supportFragmentManager.fragmentFactory = fragmentFactory!!
    }

    @TestOnly
    fun setFragmentFactory(factory: FragmentFactory) {
        fragmentFactory = factory
    }
}