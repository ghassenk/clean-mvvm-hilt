package com.gk.app.android.testingviewmodels.ui.navigation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gk.app.android.testingviewmodels.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationActivity : AppCompatActivity() {

    private var viewModel: NavigationViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.v(javaClass.simpleName, "onCreate()")

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (viewModel == null) {
            val vm : NavigationViewModelImpl by viewModels()
            viewModel = vm
        }

        viewModel?.bindToView(this)
    }

    override fun onBackPressed() {
        viewModel?.onBackClicked()
    }

    override fun onResume() {
        Log.v(javaClass.simpleName, "onResume()")
        super.onResume()

        viewModel?.startNavigation()
    }

    override fun onPause() {
        Log.v(javaClass.simpleName, "onPause()")
        super.onPause()
    }

    override fun onDestroy() {
        Log.v(javaClass.simpleName, "onDestroy()")
        super.onDestroy()
    }

}