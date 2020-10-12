package com.gk.app.android.testingviewmodels.ui.navigation.gateway

import android.app.Activity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.gk.app.android.testingviewmodels.R
import com.gk.app.android.testingviewmodels.ui.navigation.MainActivity
import java.lang.ref.WeakReference

internal class DualPaneBehavior : MultiPaneBehavior {

    private var resumedActivity: WeakReference<Activity?> = WeakReference(null)
    private var navControllerLeft: WeakReference<NavController?> = WeakReference(null)
    private var navControllerRight: WeakReference<NavController?> = WeakReference(null)

    //region Lifecycle
    override fun onActivityResumed(activity: Activity) {
        resumedActivity = WeakReference(activity)
        if (activity is MainActivity) {
            navControllerLeft =
                WeakReference(activity.findNavController(R.id.navHostFragmentLeft))
            navControllerRight =
                WeakReference(activity.findNavController(R.id.navHostFragmentRight))
        }
    }

    override fun onActivityPaused(activity: Activity) {
        if (resumedActivity.get() == activity) {
            resumedActivity = WeakReference(null)
            if (activity is MainActivity) {
                navControllerLeft = WeakReference(null)
                navControllerRight = WeakReference(null)
            }
        }
    }
    //endregion

    override fun showHomeScreen() {
        navControllerLeft.get()?.navigate(
            R.id.navigation_home,
            Bundle().apply { this.putBoolean("autoSelect", true) }
        ) ?: throw IllegalStateException("No NavController Found!")

        navControllerRight.get()?.navigate(R.id.navigation_loading)
            ?: throw IllegalStateException("No NavController Found!")
    }

    override fun showDetailScreen(itemId: String) {
        navControllerRight.get()
            ?.navigate(
                R.id.navigation_detail,
                Bundle().apply { this.putString("itemId", itemId) })
            ?: throw IllegalStateException("No NavController Found!")
    }
}