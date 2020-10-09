package com.gk.app.android.testingviewmodels.ui.navigation

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.gk.app.android.testingviewmodels.R
import com.gk.app.android.testingviewmodels.ui.detail.DetailActivity
import com.gk.app.testingviewmodels.domain.navigation.ScreensGateway
import java.lang.ref.WeakReference

/**
 * Implementation of ScreensGateway interface using NavGraph to navigate between Screens.
 */
class ScreensGatewayNavGraphImpl(
    appContext: Context
) : ScreensGateway {

    // TODO can we have the same interface for this gateway when we use Single Activity?

    companion object {
        var activityObserver: Application.ActivityLifecycleCallbacks? = null
        var resumedActivity: WeakReference<Activity?> = WeakReference(null)
        var navController: WeakReference<NavController?> = WeakReference(null)
    }

    init {
        if (activityObserver == null) {
            activityObserver = object : Application.ActivityLifecycleCallbacks {

                override fun onActivityCreated(activity: Activity, bundle: Bundle?) {}
                override fun onActivityStarted(activity: Activity) {}

                override fun onActivityResumed(activity: Activity) {
                    resumedActivity = WeakReference(activity)
                    if (activity is MainActivity) {
                        navController =
                            WeakReference(activity.findNavController(R.id.navHostFragment))
                    }
                }

                override fun onActivityPaused(activity: Activity) {
                    if (resumedActivity.get() == activity) {
                        resumedActivity = WeakReference(null)
                        if (activity is MainActivity) {
                            navController = WeakReference(null)
                        }
                    }
                }

                override fun onActivityStopped(activity: Activity) {}
                override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {}
                override fun onActivityDestroyed(activity: Activity) {}
            }

            if (appContext is Application) {
                appContext.registerActivityLifecycleCallbacks(activityObserver)
            } else {
                throw IllegalStateException("No Application Object Found!")
            }
        }
    }

    override fun showHomeScreen() {
        Log.i(javaClass.simpleName, "showHomeScreen()")

        navController.get()?.navigate(R.id.navigation_home)
            ?: throw IllegalStateException("No NavController Found!")
    }

    override fun showDetailScreen(itemId: String) {
        Log.i(javaClass.simpleName, "startDetailScreen() itemId=$itemId")

        navController.get()
            ?.navigate(R.id.navigation_detail, Bundle().apply { this.putString("itemId", itemId) })
            ?: throw IllegalStateException("No NavController Found!")
    }
}