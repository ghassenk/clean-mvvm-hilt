package com.gk.app.android.testingviewmodels.ui.navigation.gateway

import android.app.Activity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.gk.app.android.testingviewmodels.R
import java.lang.ref.WeakReference

internal class DualPaneBehavior(
    activity: Activity?
) : MultiPaneBehavior {

    private var activity: WeakReference<Activity?> = WeakReference(activity)
    private val navControllerLeft: NavController?
        get() {
            return try {
                activity.get()?.findNavController(R.id.navHostFragmentLeft)
            } catch (e: Exception) {
                null
            }
        }
    private val navControllerRight: NavController?
        get() {
            return try {
                activity.get()?.findNavController(R.id.navHostFragmentRight)
            } catch (e: Exception) {
                null
            }
        }

    override fun setResumedActivity(resumedActivity: WeakReference<Activity?>) {
        activity = resumedActivity
    }

    //region Screens
    override fun showHomeScreen() {
        navControllerLeft?.navigate(
            R.id.navigation_home,
            Bundle().apply { this.putBoolean("autoSelect", true) }
        ) //?: throw IllegalStateException("No NavController Found!")

        navControllerRight?.navigate(R.id.navigation_loading)
        //?: throw IllegalStateException("No NavController Found!")
    }

    override fun showDetailScreen(itemId: String) {
        navControllerRight?.navigate(
            R.id.navigation_detail,
            Bundle().apply { this.putString("itemId", itemId) })
        //?: throw IllegalStateException("No NavController Found!")
    }

    override fun terminate() {
        TODO("Not yet implemented")
    }
    //endregion
}