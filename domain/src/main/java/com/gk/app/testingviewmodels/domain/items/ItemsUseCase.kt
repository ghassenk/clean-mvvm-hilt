package com.gk.app.testingviewmodels.domain.items

import com.gk.app.testingviewmodels.domain.navigation.ScreensGateway

interface ItemsUseCase {

    object Factory {
        fun get(
            screensGateway: ScreensGateway,
            itemsGateway: ItemsGateway
        ): ItemsUseCase {
            return ItemsUseCaseImpl(screensGateway, itemsGateway)
        }
    }

    fun openItemDetails(itemId: String)

    suspend fun getItems() : List<Item>

    fun terminate()
}