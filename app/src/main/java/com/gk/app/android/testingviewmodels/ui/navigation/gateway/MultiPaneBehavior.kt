package com.gk.app.android.testingviewmodels.ui.navigation.gateway

import android.app.Activity
import com.gk.app.testingviewmodels.domain.navigation.ScreensGateway
import java.lang.ref.WeakReference

internal interface MultiPaneBehavior : ScreensGateway {
    fun setActivity(activity: Activity?)
}

