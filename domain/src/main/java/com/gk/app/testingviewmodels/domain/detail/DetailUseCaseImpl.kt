package com.gk.app.testingviewmodels.domain.detail

import com.gk.app.testingviewmodels.domain.home.ItemsGateway
import com.gk.app.testingviewmodels.domain.navigation.ScreensGateway

internal class DetailUseCaseImpl(
    private val screensGateway: ScreensGateway,
    private val itemsGateway: ItemsGateway
) : DetailUseCase {

    override suspend fun getItemDetails(itemId: String): List<Any> {
        return itemsGateway.getItemDetail(itemId)
    }

    override fun selectDetail(detailId: String) {
        TODO("Not yet implemented")
    }

    override fun terminate() {

    }
}