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

    private var _activity: WeakReference<Activity?> = WeakReference(activity)
    private val navControllerLeft: NavController?
        get() {
            return try {
                _activity.get()?.findNavController(R.id.navHostFragmentLeft)
            } catch (e: Exception) {
                null
            }
        }
    private val navControllerRight: NavController?
        get() {
            return try {
                _activity.get()?.findNavController(R.id.navHostFragmentRight)
            } catch (e: Exception) {
                null
            }
        }
    private var _currentLeftScreen: Screen? = null
    private var _currentRightScreen: Screen? = null

    override fun setActivity(activity: Activity?) {
        _activity = WeakReference(activity)
    }

    //region Screens
    fun getCurrentLeftScreen(): Screen? {
        return _currentLeftScreen
    }

    fun getCurrentRightScreen(): Screen? {
        return _currentRightScreen
    }

    override fun refreshNavigation() {
        // Nothing to do
    }

    override fun showItemsScreen(autoSelectPosition: Int?) {
        navControllerLeft?.navigate(
            R.id.navigation_item_list,
            Bundle().apply {
                if (autoSelectPosition != null) {
                    this.putInt("autoSelectPosition", autoSelectPosition)
                }
            }
        )

        // If we have an autoSelectPosition we need to wait until items are loaded in order to
        // select that position => we show loading fragment on the right, and detail will be loaded
        // automatically with auto-select
        if (autoSelectPosition != null) {
            navControllerRight?.navigate(R.id.navigation_loading)
        } else {
            navControllerRight?.navigate(R.id.navigation_detail)
        }

        updateCurrentScreenFromNavControllers()
    }

    override fun showDetailScreen(itemId: String?) {
        // Nothing to do
    }

    override fun onNavigateBack() {
        // No back navigation in dual pane for now
        _activity.get()?.finish()
    }

    override fun onNavigateUp() {

    }

    private fun updateCurrentScreenFromNavControllers() {
        navControllerRight?.let {
            if (it.currentDestination == null) {
                _currentLeftScreen = null
            } else {
                when (it.currentDestination?.id) {
                    R.id.navigation_loading -> _currentLeftScreen = Screen.Loading
                    R.id.navigation_error -> _currentLeftScreen = Screen.Error
                    R.id.navigation_item_list -> _currentLeftScreen = Screen.ItemList
                }
            }
        }

        navControllerRight?.let {
            if (it.currentDestination == null) {
                _currentRightScreen = null
            } else {
                when (it.currentDestination?.id) {
                    R.id.navigation_loading -> _currentRightScreen = Screen.Loading
                    R.id.navigation_error -> _currentRightScreen = Screen.Error
                    R.id.navigation_item_list -> _currentRightScreen = Screen.ItemDetail
                }
            }
        }
    }

    override fun terminate() {

    }
    //endregion
}