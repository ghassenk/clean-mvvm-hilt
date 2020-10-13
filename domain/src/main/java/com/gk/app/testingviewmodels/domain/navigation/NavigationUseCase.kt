package com.gk.app.testingviewmodels.domain.navigation

interface NavigationUseCase {

    object Factory {
        fun get(
            screensGateway: ScreensGateway
        ): NavigationUseCase {
            return NavigationUseCaseImpl(screensGateway)
        }
    }

    fun startNavigation()

    fun onNavigateBack()

    fun onNavigateUp()

    fun terminate()
}