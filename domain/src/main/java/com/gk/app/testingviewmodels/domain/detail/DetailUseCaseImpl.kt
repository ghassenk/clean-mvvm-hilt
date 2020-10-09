package com.gk.app.testingviewmodels.domain.detail

import com.gk.app.testingviewmodels.domain.navigation.ScreensGateway

internal class DetailUseCaseImpl(
    private val screensGateway: ScreensGateway
) : DetailUseCase {

    override suspend fun getItemDetails(itemId: String): List<Any> {
        return emptyList()
    }

    override fun terminate() {

    }
}