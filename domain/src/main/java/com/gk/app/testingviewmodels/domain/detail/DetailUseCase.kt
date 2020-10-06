package com.gk.app.testingviewmodels.domain.detail

import com.gk.app.testingviewmodels.domain.navigation.UiNavigationGateway

interface DetailUseCase {
    object Factory {
        private var instance: DetailUseCase? = null

        fun get(
            uiNavigationGateway: UiNavigationGateway
        ): DetailUseCase {
            return instance ?: create(uiNavigationGateway)
        }

        private fun create(
            uiNavigationGateway: UiNavigationGateway
        ): DetailUseCase {
            // Create a new use case object and save it
            return DetailUseCaseImpl(uiNavigationGateway).apply {
                instance = this
            }
        }

        fun terminate() {
            instance = null
        }
    }

    fun terminate() {
        Factory.terminate()
    }

    suspend fun getItemDetails(itemId: String): List<Any>

}