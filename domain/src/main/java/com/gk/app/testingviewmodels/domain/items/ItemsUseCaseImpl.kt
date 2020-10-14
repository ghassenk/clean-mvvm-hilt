package com.gk.app.testingviewmodels.domain.items

import com.gk.app.testingviewmodels.domain.navigation.ScreensGateway

internal class ItemsUseCaseImpl(
    private val screensGateway: ScreensGateway,
    private val itemsGateway: ItemsGateway
) : ItemsUseCase {

    override fun openItemDetails(itemId: String) {
        // Solution 1 call a UI gateway to start the actual screen and pass it the itemId
        screensGateway.showDetailScreen(itemId)
    }

    override suspend fun getItems(): List<Item> {
        return itemsGateway.getItems()
    }

    override fun terminate() {

    }

}