package com.gk.app.testingviewmodels.domain.detail

import com.gk.app.testingviewmodels.domain.navigation.UiNavigationGateway

interface DetailUseCase {
    object Factory {
        fun get(
            uiNavigationGateway: UiNavigationGateway
        ): DetailUseCase {
            return DetailUseCaseImpl(uiNavigationGateway)
        }
    }

    suspend fun getItemDetails(itemId: String): List<Any>

}