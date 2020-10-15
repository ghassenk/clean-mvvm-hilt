package com.gk.app.testingviewmodels.domain.navigation

/**
 * Interface for navigating between the application's screens
 */
interface ScreensGateway {

    fun refreshNavigation()

    fun showItemsScreen(autoSelectPosition: Int? = null)

    fun showDetailScreen(itemId:String? = null)


    fun onNavigateBack()

    fun onNavigateUp()


    fun terminate()
}