package com.gk.app.android.testingviewmodels.ui.navigation.gateway

import android.app.Activity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.gk.app.android.testingviewmodels.R
import com.gk.app.android.testingviewmodels.ui.navigation.MainActivity
import java.lang.ref.WeakReference

internal class SinglePaneBehavior : MultiPaneBehavior {
    private var resumedActivity: WeakReference<Activity?> = WeakReference(null)
    private var singleNavController: WeakReference<NavController?> = WeakReference(null)

    //region Lifecycle
    override fun onActivityResumed(activity: Activity) {
        if (activity is MainActivity) {
            singleNavController =
                WeakReference(activity.findNavController(R.id.navHostFragment))
        }
    }

    override fun onActivityPaused(activity: Activity) {
        if (resumedActivity.get() == activity) {
            resumedActivity = WeakReference(null)
            if (activity is MainActivity) {
                singleNavController = WeakReference(null)
            }
        }
    }
    //endregion


    override fun showHomeScreen() {
        singleNavController.get()?.navigate(R.id.navigation_home)
            ?: throw IllegalStateException("No NavController Found!")
    }

    override fun showDetailScreen(itemId: String) {
        singleNavController.get()
            ?.navigate(
                R.id.navigation_detail,
                Bundle().apply { this.putString("itemId", itemId) })
            ?: throw IllegalStateException("No NavController Found!")
    }
}