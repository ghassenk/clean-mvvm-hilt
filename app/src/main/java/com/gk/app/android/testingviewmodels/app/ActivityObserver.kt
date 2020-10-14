package com.gk.app.android.testingviewmodels.app

import android.app.Activity

interface ActivityObserver {

    fun getNavigationActivity() : Activity?
    fun startActivityObserver()
}