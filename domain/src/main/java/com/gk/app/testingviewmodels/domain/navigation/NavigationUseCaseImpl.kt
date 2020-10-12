package com.gk.app.testingviewmodels.domain.navigation

internal class NavigationUseCaseImpl(
    private val screensGateway: ScreensGateway
) : NavigationUseCase {

    override fun startNavigation() {
        screensGateway.showHomeScreen()
    }

    override fun terminate() {
        screensGateway.terminate()
    }
}