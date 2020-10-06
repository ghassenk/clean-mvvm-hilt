package com.gk.app.android.testingviewmodels.ui.navigation

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.gk.app.android.testingviewmodels.app.MyApp
import com.gk.app.testingviewmodels.domain.navigation.UiNavigationGateway
import com.gk.app.android.testingviewmodels.ui.detail.DetailActivity
import java.lang.ref.WeakReference

class UiNavigationGatewayImpl(
    appContext: Context
) : UiNavigationGateway {

    // TODO can we have the same interface for this gateway when we use Single Activity?

    companion object {
        var activityObserver: Application.ActivityLifecycleCallbacks? = null
        var resumedActivity: WeakReference<Activity?> = WeakReference(null)
    }

    init {
        if (activityObserver == null) {
            activityObserver = object : Application.ActivityLifecycleCallbacks {

                override fun onActivityCreated(activity: Activity, bundle: Bundle?) {}
                override fun onActivityStarted(activity: Activity) {}

                override fun onActivityResumed(activity: Activity) {
                    resumedActivity = WeakReference(activity)
                }

                override fun onActivityPaused(activity: Activity) {
                    if (resumedActivity.get() == activity) {
                        resumedActivity = WeakReference(null)
                    }
                }

                override fun onActivityStopped(activity: Activity) {}
                override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {}
                override fun onActivityDestroyed(activity: Activity) {}
            }
            if (appContext is Application){
                appContext.registerActivityLifecycleCallbacks(activityObserver)
            } else {
                throw IllegalStateException("No Application Object Found!")
            }
        }
    }

    override fun startDetailScreen(itemId: String) {
        Log.i(javaClass.simpleName, "startDetailScreen() itemId=$itemId")
        resumedActivity.get()?.let {
            it.startActivity(Intent(it, DetailActivity::class.java).apply {
                this.putExtra("itemId", itemId)
            })
        } ?: throw IllegalStateException("No Resumed Activity Found!")
    }
}