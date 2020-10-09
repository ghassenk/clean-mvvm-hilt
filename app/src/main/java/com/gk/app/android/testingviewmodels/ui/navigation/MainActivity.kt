package com.gk.app.android.testingviewmodels.ui.navigation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.gk.app.android.testingviewmodels.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.v(javaClass.simpleName, "onCreate()")

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        //TODO temp
        // if smart phone, the home fragment containing a list of items
    }

    override fun onDestroy() {
        Log.v(javaClass.simpleName, "onDestroy()")
        super.onDestroy()
    }

}