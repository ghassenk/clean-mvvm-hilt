package com.gk.app.testingviewmodels.domain.navigation

/**
 * Interface for navigating between the application's screens
 */
interface ScreensGateway {

    fun showHomeScreen()

    fun showDetailScreen(itemId:String)


    fun onNavigateBack()

    fun onNavigateUp()


    fun terminate()
}