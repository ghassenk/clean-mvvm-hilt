package com.gk.app.testingviewmodels.domain.items

import com.gk.app.testingviewmodels.domain.navigation.ScreensGateway

internal class ItemsUseCaseImpl(
    private val screensGateway: ScreensGateway,
    private val itemsGateway: ItemsGateway
) : ItemsUseCase {

    override fun openItemDetails(itemId: String) {
        // Use a gateway for logs
        println("${javaClass.simpleName} openItemDetails() itemId=$itemId")
        screensGateway.showDetailScreen(itemId)
    }

    override suspend fun getItems(): List<Item> {
        return itemsGateway.getItems()
    }

    override fun terminate() {

    }

}