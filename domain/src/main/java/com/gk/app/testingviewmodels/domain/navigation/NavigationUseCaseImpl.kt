package com.gk.app.testingviewmodels.domain.navigation

internal class NavigationUseCaseImpl(
    private val screensGateway: ScreensGateway
) : NavigationUseCase {

    override fun startNavigation() {
        screensGateway.showHomeScreen()
    }

    override fun onNavigateBack() {
        screensGateway.onNavigateBack()
    }

    override fun onNavigateUp() {
        screensGateway.onNavigateUp()
    }

    override fun terminate() {
        screensGateway.terminate()
    }
}