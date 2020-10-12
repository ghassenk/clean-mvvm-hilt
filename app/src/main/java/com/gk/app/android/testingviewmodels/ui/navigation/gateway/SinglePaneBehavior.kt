package com.gk.app.android.testingviewmodels.ui.navigation.gateway

import android.app.Activity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.gk.app.android.testingviewmodels.R
import com.gk.app.android.testingviewmodels.ui.navigation.NavigationActivity
import java.lang.ref.WeakReference

internal class SinglePaneBehavior(
    activity: Activity?
) : MultiPaneBehavior {

    private var activity: WeakReference<Activity?> = WeakReference(activity)
    private val singleNavController: NavController?
        get() {
            return try {
                activity.get()?.findNavController(R.id.navHostFragment)
            } catch (e:Exception) {
                null
            }
        }

    override fun setResumedActivity(resumedActivity: WeakReference<Activity?>) {
        activity = resumedActivity
    }

    //region Screens
    override fun showHomeScreen() {
        singleNavController?.navigate(R.id.navigation_home)
            //?: throw IllegalStateException("No NavController Found!")
    }

    override fun showDetailScreen(itemId: String) {
        singleNavController?.navigate(
                R.id.navigation_detail,
                Bundle().apply { this.putString("itemId", itemId) })
            //?: throw IllegalStateException("No NavController Found!")
    }

    override fun terminate() {

    }
    //endregion

}