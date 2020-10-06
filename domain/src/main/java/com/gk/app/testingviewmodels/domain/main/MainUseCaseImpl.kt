package com.gk.app.testingviewmodels.domain.main

import com.gk.app.testingviewmodels.domain.navigation.UiNavigationGateway

internal class  MainUseCaseImpl(private val uiNavigationGateway: UiNavigationGateway) : MainUseCase {

    override fun onButtonClicked(itemId: String) {
        // Solution 1 call a UI gateway to start the actual screen and pass it the itemId
        uiNavigationGateway.startDetailScreen(itemId)
    }

    override fun terminate() {

    }

}