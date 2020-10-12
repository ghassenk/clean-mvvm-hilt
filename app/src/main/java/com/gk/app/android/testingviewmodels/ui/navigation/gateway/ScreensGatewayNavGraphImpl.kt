package com.gk.app.android.testingviewmodels.ui.navigation.gateway

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import com.gk.app.android.testingviewmodels.R
import com.gk.app.testingviewmodels.domain.navigation.ScreensGateway
import java.lang.ref.WeakReference

/**
 * Implementation of ScreensGateway interface using NavGraph to navigate between Screens.
 */
class ScreensGatewayNavGraphImpl(
    appContext: Context
) : ScreensGateway {

    companion object {
        private var activityObserver: Application.ActivityLifecycleCallbacks? = null
        private var application: WeakReference<Application?> = WeakReference(null)
    }

    init {
        if (activityObserver == null) {
            activityObserver = object : EmptyActivityLifecycleCallbacks {
                override fun onActivityResumed(activity: Activity) {
                    behavior.onActivityResumed(activity)
                }

                override fun onActivityPaused(activity: Activity) {
                    behavior.onActivityPaused(activity)
                }
            }

            if (appContext is Application) {
                application = WeakReference(appContext)
                appContext.registerActivityLifecycleCallbacks(activityObserver)
            } else {
                throw IllegalStateException("No Application Object Found!")
            }
        }
    }

//    override fun terminate() {
//        application.get()?.unregisterActivityLifecycleCallbacks(activityObserver)
//        activityObserver = null
//        application = WeakReference(null)
//    }

    override fun showHomeScreen() {
        Log.i(javaClass.simpleName, "showHomeScreen()")

        // Dispatch to current behavior
        behavior.showHomeScreen()
    }

    override fun showDetailScreen(itemId: String) {
        Log.i(javaClass.simpleName, "startDetailScreen() itemId=$itemId")

        // Dispatch to current behavior
        behavior.showDetailScreen(itemId)
    }

    //region Dual/Single Pane Behavior
    private fun isDualPane(): Boolean {
        application.get()?.let {
            return it.resources.getBoolean(R.bool.useDualPane)
        } ?: throw IllegalStateException("No Application Object Found!")
    }

    private val behavior: MultiPaneBehavior
        get() {
            return if (isDualPane()) {
                dualPaneBehavior
            } else {
                singlePaneBehavior
            }
        }

    private val dualPaneBehavior: DualPaneBehavior by lazy { DualPaneBehavior() }

    private val singlePaneBehavior: SinglePaneBehavior by lazy { SinglePaneBehavior() }
    //endregion

}