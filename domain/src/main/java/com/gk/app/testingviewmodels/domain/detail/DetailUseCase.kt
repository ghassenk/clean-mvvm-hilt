package com.gk.app.testingviewmodels.domain.detail

import com.gk.app.testingviewmodels.domain.navigation.ScreensGateway

interface DetailUseCase {

    object Factory {
        fun get(
            screensGateway: ScreensGateway,
            detailsGateway: DetailsGateway
        ): DetailUseCase {
            return DetailUseCaseImpl(screensGateway, detailsGateway)
        }
    }

    suspend fun getItemDetails(itemId: String): String

    fun selectDetail(detailId: String)

    fun terminate()
}