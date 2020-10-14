package com.gk.app.android.testingviewmodels.ui.navigation.gateway

import android.app.Activity
import android.content.Context
import android.util.Log
import com.gk.app.android.testingviewmodels.R
import com.gk.app.android.testingviewmodels.app.ActivityObserver
import com.gk.app.testingviewmodels.domain.navigation.ScreensGateway
import java.lang.ref.WeakReference

/**
 * Implementation of ScreensGateway interface using NavGraph to navigate between Screens.
 */
class ScreensGatewaySingleActivityNavGraphImpl(
    appContext: Context,
    activityObserver: ActivityObserver
) : ScreensGateway {

    private enum class ScreenMode {
        SinglePane, DualPane
    }

    // "Last" instead of "current" as they will be kept after navigation stops
    private val _weakApp = WeakReference(appContext)
    private val _weakActivityObserver = WeakReference(activityObserver)
    private var _lastScreenMode: ScreenMode? = null

    //region Life Cycle
    init {
        Log.i(javaClass.simpleName, "init()")
    }

    override fun terminate() {

    }
    //endregion

    //region Screens
    override fun refreshNavigation() {
        Log.v(javaClass.simpleName, "startNavigation()")
        _weakActivityObserver.get()?.let {
            it.getNavigationActivity()?.let { activity -> doStart(activity) }
        }
    }

    // Possible start cases:
    // 1- start_single_pane
    // 1.1- from scratch -> tell SinglePaneBehavior to start from scratch
    // 1.2- after switching from dual pane -> tell SinglePaneBehavior to start a specific
    //                                        screen and give it the right data
    //                                        (e.g: show detail in single pane coming from a master-detail
    //                                         given the id of the item)

    // 2- start_dual_pane
    // 2.1- from scratch -> tell DualPaneBehavior to start from scratch
    // 2.2- after switching from single pane -> tell DualPaneBehavior to start a specific
    //                                        screen and give it the right data
    private fun doStart(activity: Activity) {
        Log.v(javaClass.simpleName, "doStart()")

        val newScreenMode = if (isDualPane()) {
            ScreenMode.DualPane
        } else {
            ScreenMode.SinglePane
        }

        if (_lastScreenMode == null) {
            if (newScreenMode == ScreenMode.DualPane) {
                dualPaneBehavior = DualPaneBehavior(activity).apply { behavior = this }
                cleanStartDualPane()
            } else {
                singlePaneBehavior = SinglePaneBehavior(activity).apply { behavior = this }
                cleanStartSinglePane()
            }

        } else {
            if (newScreenMode == ScreenMode.DualPane) {
                dualPaneBehavior?.let {
                    it.setActivity(activity)
                    behavior = it
                } ?: run { dualPaneBehavior = DualPaneBehavior(activity).apply { behavior = this } }

                switchToDualPane()

            } else {
                singlePaneBehavior?.let {
                    it.setActivity(activity)
                    behavior = it
                } ?: run {
                    singlePaneBehavior = SinglePaneBehavior(activity).apply { behavior = this }
                }

                switchToSinglePane()
            }
        }

        _lastScreenMode = newScreenMode

        Log.d(javaClass.simpleName, "_lastScreenMode = $_lastScreenMode")
        Log.d(javaClass.simpleName, "behavior = $behavior")
    }

    private fun cleanStartSinglePane() {
        Log.i(javaClass.simpleName, "cleanStartSinglePane()")
        // e.g show home screen's items' list!
        singlePaneBehavior?.showItemsScreen(null)
    }

    private fun cleanStartDualPane() {
        Log.i(javaClass.simpleName, "cleanStartDualPane()")
        // e.g show home screen's dual mode:
        // item list on the left, detail of the first item on the right
        // (Will force the view to select its first item)
        singlePaneBehavior?.showItemsScreen(0)
    }

    private fun switchToDualPane() {
        Log.i(javaClass.simpleName, "switchToDualPane()")
        // e.g we're on the item list screen:
        // show item list on the left, detail of first item on the right
        when (singlePaneBehavior?.getCurrentScreen()) {
            Screen.ItemList -> {
                // No item was selected: so we set selected position to 0 (to force the view
                // to load the first item)
                behavior.showItemsScreen(0)
            }
            Screen.ItemDetail -> {
                // An item was selected with its details shown : set the same selected position
                // and same item details on the left
                // (-> restore the DetailViewModel and the ItemListViewModel)
                behavior.showItemsScreen(null)
            }
        }
    }

    private fun switchToSinglePane() {
        Log.i(javaClass.simpleName, "switchToSinglePane()")
        // e.g we were on the master detail screen with a selected item in the list on the left
        // and its detail on the right and show the last selected item detail
        // TODO KEEP the item detail fragment's view model
        when (dualPaneBehavior?.getCurrentLeftScreen()) {
            Screen.ItemList -> {
                if (dualPaneBehavior?.getCurrentRightScreen() == Screen.ItemDetail) {
                    // Keep only the detail screen for the same item
                    // (-> restore the DetailViewModel)
                    behavior.showDetailScreen(null)
                } else {
                    behavior.showItemsScreen(null)
                }
            }
            Screen.Loading -> {
                behavior.showItemsScreen(null)
            }
            Screen.Error -> {
                behavior.showItemsScreen(null)
            }
        }
    }

    private fun stopNavigation() {
        // TODO check whether to call it through business etc
        behavior.setActivity(null)
    }


    override fun showItemsScreen(autoSelectPosition: Int?) {
        Log.i(javaClass.simpleName, "showHomeScreen()")

        // Dispatch to current behavior
        behavior.showItemsScreen(autoSelectPosition)
    }

    override fun showDetailScreen(itemId: String?) {
        Log.i(javaClass.simpleName, "startDetailScreen() itemId=$itemId")

        // Dispatch to current behavior
        behavior.showDetailScreen(itemId)
    }

    override fun onNavigateBack() {
        // Dispatch to current behavior
        behavior.onNavigateBack()
    }

    override fun onNavigateUp() {
        // Dispatch to current behavior
        behavior.onNavigateUp()
    }
    //endregion

    //region Dual/Single Pane Behavior
    private fun isDualPane(): Boolean {
        _weakApp.get()?.let {
            return it.resources.getBoolean(R.bool.useDualPane)
        } ?: throw IllegalStateException("No Application Object Found!")
    }

    private lateinit var behavior: MultiPaneBehavior
    private var dualPaneBehavior: DualPaneBehavior? = null
    private var singlePaneBehavior: SinglePaneBehavior? = null

    //endregion

}