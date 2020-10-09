package com.gk.app.testingviewmodels.domain.home

import com.gk.app.testingviewmodels.domain.navigation.ScreensGateway

interface HomeUseCase {

    object Factory {
        fun get(
            screensGateway: ScreensGateway
        ): HomeUseCase {
            return HomeUseCaseImpl(screensGateway)
        }
    }

    fun onButtonClicked(itemId: String)

    fun terminate()
}