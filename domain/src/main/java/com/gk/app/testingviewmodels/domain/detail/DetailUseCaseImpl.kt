package com.gk.app.testingviewmodels.domain.detail

import com.gk.app.testingviewmodels.domain.navigation.UiNavigationGateway

internal class DetailUseCaseImpl(
    private val uiNavigationGateway: UiNavigationGateway
) : DetailUseCase {



    override suspend fun getItemDetails(itemId: String): List<Any> {
        return emptyList()
    }

    override fun terminate() {

    }
}