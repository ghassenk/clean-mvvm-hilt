package com.gk.app.android.testingviewmodels.ui.navigation.gateway

import android.app.Activity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.gk.app.android.testingviewmodels.R
import java.lang.ref.WeakReference

internal class SinglePaneBehavior(
    activity: Activity?
) : MultiPaneBehavior {

    private var _activity: WeakReference<Activity?> = WeakReference(activity)
    private val singleNavController: NavController?
        get() {
            return try {
                _activity.get()?.findNavController(R.id.navHostFragment)
            } catch (e: Exception) {
                null
            }
        }
    private var _currentScreen: Screen? = null

    //region Life Cycle
    override fun setActivity(activity: Activity?) {
        _activity = WeakReference(activity)
    }

    override fun terminate() {

    }
    //endregion

    //region Screens
    fun getCurrentScreen(): Screen? {
        return _currentScreen
    }

    override fun refreshNavigation() {
        // Nothing to do
    }

    override fun showItemsScreen(autoSelectPosition: Int?) {
        singleNavController?.let {  navController ->
            navController.navigate(R.id.navigation_item_list)
            updateCurrentScreenFromNavController()
        }
    }

    override fun showDetailScreen(itemId: String?) {
        singleNavController?.let {
            it.navigate(
                R.id.navigation_detail,
                Bundle().apply { this.putString("itemId", itemId) }
            )
            updateCurrentScreenFromNavController()
        }
    }

    override fun onNavigateBack() {
        singleNavController?.let {
            // We try to pop un entry from the back stack, otherwise back will finish
            // the activity
            if (it.popBackStack()) {
                updateCurrentScreenFromNavController()
            } else {
                _activity.get()?.finish()
            }
        }
    }

    override fun onNavigateUp() {

    }

    private fun updateCurrentScreenFromNavController() {
        singleNavController?.let {
            if (it.currentDestination == null) {
                _currentScreen = null
            } else {
                when (it.currentDestination?.id) {
                    R.id.navigation_loading -> _currentScreen = Screen.Loading
                    R.id.navigation_error -> _currentScreen = Screen.Error
                    R.id.navigation_item_list -> _currentScreen = Screen.ItemList
                    R.id.navigation_detail -> _currentScreen = Screen.ItemDetail
                }
            }
        }
    }

    //endregion

}