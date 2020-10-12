package com.gk.app.testingviewmodels.domain.navigation

interface NavigationUseCase {

    object Factory {
        fun get(
            screensGateway: ScreensGateway
        ): NavigationUseCase {
            return NavigationUseCaseImpl(screensGateway)
        }
    }

    fun start()

    fun terminate()
}