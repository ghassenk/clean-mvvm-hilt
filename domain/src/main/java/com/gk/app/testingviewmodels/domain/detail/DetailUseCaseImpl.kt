package com.gk.app.testingviewmodels.domain.detail

import com.gk.app.testingviewmodels.domain.navigation.ScreensGateway

internal class DetailUseCaseImpl(
    private val screensGateway: ScreensGateway,
    private val detailsGateway: DetailsGateway
) : DetailUseCase {

    override suspend fun getItemDetails(itemId: String): String {
        return detailsGateway.getItemDetail(itemId)
    }

    override fun selectDetail(detailId: String) {
        TODO("Not yet implemented")
    }

    override fun terminate() {

    }
}