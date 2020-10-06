package com.gk.app.android.testingviewmodels.domain

internal class DetailUseCaseImpl(
    private val uiNavigationGateway: UiNavigationGateway
) : DetailUseCase {

    object Factory {
        internal var instance: DetailUseCase? = null

        fun get(
            uiNavigationGateway: UiNavigationGateway
        ): DetailUseCase {
            return instance ?: create(uiNavigationGateway)
        }

        private fun create(
            uiNavigationGateway: UiNavigationGateway
        ): DetailUseCase {
            // Create a new use case object and save it
            return DetailUseCaseImpl(uiNavigationGateway).apply {
                instance = this
            }
        }
    }

    override fun terminate() {
        Factory.instance = null
    }

    override suspend fun getItemDetails(itemId: String): List<Any> {
        return emptyList()
    }
}