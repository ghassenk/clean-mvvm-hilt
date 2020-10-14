package com.gk.app.android.testingviewmodels.app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import com.gk.app.android.testingviewmodels.ui.navigation.NavigationActivity
import com.gk.app.android.testingviewmodels.ui.navigation.gateway.EmptyActivityLifecycleCallbacks
import dagger.hilt.android.HiltAndroidApp
import java.lang.ref.WeakReference

/**
 * Used mainly for auto dependency injection, avoid using directly.
 */
@HiltAndroidApp
class MyApp : Application(), ActivityObserver {

    override fun onCreate() {
        Log.i(javaClass.simpleName, "onCreate()")
        super.onCreate()

        startActivityObserver()
    }

    override fun startActivityObserver() {
        this.registerActivityLifecycleCallbacks(
            object : EmptyActivityLifecycleCallbacks {

                override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
                    Log.i(javaClass.simpleName, "onActivityCreated() $activity")
                    if (activity is NavigationActivity) {
                        _navigationActivity = WeakReference(activity)
                    }
                }

                override fun onActivityDestroyed(activity: Activity) {
                    Log.i(javaClass.simpleName, "onActivityDestroyed() $activity")
                    if (_navigationActivity.get() == activity) {
                        _navigationActivity = WeakReference(null)
                    }
                }
            }
        )
    }

    override fun getNavigationActivity(): Activity? {
        return _navigationActivity.get()
    }

    private var _navigationActivity: WeakReference<Activity?> = WeakReference(null)

}