package com.gk.app.android.testingviewmodels

import com.gk.app.android.testingviewmodels.ui.home.HomeViewModel

open class HomeViewModelSpy : HomeViewModel {

    var isButtonClicked = false
    var itemId: String? = null

    override fun onButtonClicked(itemId: String) {
        isButtonClicked = true
        this.itemId = itemId
    }
}