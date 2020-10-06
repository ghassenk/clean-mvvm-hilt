package com.gk.app.testingviewmodels.domain.main

import com.gk.app.testingviewmodels.domain.navigation.UiNavigationGateway

interface MainUseCase {

    object Factory {
        fun get(
            uiNavigationGateway: UiNavigationGateway
        ): MainUseCase {
            return MainUseCaseImpl(uiNavigationGateway)
        }
    }

    fun onButtonClicked(itemId: String)

    fun terminate()
}