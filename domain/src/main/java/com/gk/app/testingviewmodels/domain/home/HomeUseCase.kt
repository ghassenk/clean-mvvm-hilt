package com.gk.app.testingviewmodels.domain.home

import com.gk.app.testingviewmodels.domain.navigation.ScreensGateway

interface HomeUseCase {

    object Factory {
        fun get(
            screensGateway: ScreensGateway,
            itemsGateway: ItemsGateway
        ): HomeUseCase {
            return HomeUseCaseImpl(screensGateway, itemsGateway)
        }
    }

    fun onItemClick(itemId: String)

    suspend fun getHomeItems() : List<Item>

    fun terminate()
}