package com.gk.app.testingviewmodels.domain.detail

import com.gk.app.testingviewmodels.domain.navigation.ScreensGateway

interface DetailUseCase {

    object Factory {
        fun get(
            screensGateway: ScreensGateway
        ): DetailUseCase {
            return DetailUseCaseImpl(screensGateway)
        }
    }

    suspend fun getItemDetails(itemId: String): List<Any>

    fun terminate()
}