package com.gk.app.android.testingviewmodels.domain

class MainUseCaseImpl(private val uiNavigationGateway: UiNavigationGateway) : MainUseCase {

    //region Life Cycle
    object Factory {
        internal var instance: MainUseCaseImpl? = null

        fun get(
            uiNavigationGateway: UiNavigationGateway
        ): MainUseCase {
            return instance ?: create(uiNavigationGateway)
        }

        private fun create(
            uiNavigationGateway: UiNavigationGateway
        ): MainUseCase {
            // Create a new use case object and save it
            return MainUseCaseImpl(uiNavigationGateway).apply {
                instance = this
            }
        }
    }

    override fun terminate() {
        Factory.instance = null
    }
    //endregion


    override fun onButtonClicked(itemId: String) {
        // Solution 1 call a UI gateway to start the actual screen and pass it the itemId
        uiNavigationGateway.startDetailScreen(itemId)
    }

}