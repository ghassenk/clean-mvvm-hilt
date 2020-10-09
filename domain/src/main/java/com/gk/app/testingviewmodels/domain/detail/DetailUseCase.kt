package com.gk.app.testingviewmodels.domain.detail

import com.gk.app.testingviewmodels.domain.home.ItemsGateway
import com.gk.app.testingviewmodels.domain.navigation.ScreensGateway

interface DetailUseCase {

    object Factory {
        fun get(
            screensGateway: ScreensGateway,
            itemsGateway: ItemsGateway
        ): DetailUseCase {
            return DetailUseCaseImpl(screensGateway, itemsGateway)
        }
    }

    suspend fun getItemDetails(itemId: String): List<Any>

    fun selectDetail(detailId: String)

    fun terminate()
}