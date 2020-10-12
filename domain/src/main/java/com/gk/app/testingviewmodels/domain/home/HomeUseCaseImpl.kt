package com.gk.app.testingviewmodels.domain.home

import com.gk.app.testingviewmodels.domain.navigation.ScreensGateway

internal class HomeUseCaseImpl(
    private val screensGateway: ScreensGateway,
    private val itemsGateway: ItemsGateway
) : HomeUseCase {

    override fun openItemDetails(itemId: String) {
        // Solution 1 call a UI gateway to start the actual screen and pass it the itemId
        screensGateway.showDetailScreen(itemId)
    }

    override suspend fun getHomeItems(): List<Item> {
        return itemsGateway.getItems()
    }

    override fun terminate() {

    }

}