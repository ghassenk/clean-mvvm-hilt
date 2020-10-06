package com.gk.app.testingviewmodels.domain.main

import com.gk.app.testingviewmodels.domain.navigation.UiNavigationGateway

interface MainUseCase{

    object Factory {
        private var instance: MainUseCaseImpl? = null

        fun get(
            uiNavigationGateway: UiNavigationGateway
        ): MainUseCase {
            return instance ?: create(uiNavigationGateway)
        }

        fun terminate() {
            Factory.instance = null
        }

        private fun create(
            uiNavigationGateway: UiNavigationGateway
        ): MainUseCase {
            // Create a new use case object and save it
            return MainUseCaseImpl(uiNavigationGateway).apply {
                instance = this
            }
        }
    }

    fun terminate() {
        Factory.terminate()
    }

    fun onButtonClicked(itemId: String)
}