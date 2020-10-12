package com.gk.app.testingviewmodels.domain.navigation

class NavigationUseCaseImpl(
    private val screensGateway: ScreensGateway
) : NavigationUseCase {

    override fun startNavigation() {
        screensGateway.showHomeScreen()
    }

    override fun terminate() {

    }
}