package com.gk.app.android.testingviewmodels

import com.gk.app.android.testingviewmodels.ui.main.MainViewModel

open class MainViewModelSpy : MainViewModel {

    var isButtonClicked = false
    var itemId: String? = null

    override fun onButtonClicked(itemId: String) {
        isButtonClicked = true
        this.itemId = itemId
    }
}