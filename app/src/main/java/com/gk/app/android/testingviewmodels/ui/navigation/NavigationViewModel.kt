package com.gk.app.android.testingviewmodels.ui.navigation

interface NavigationViewModel {
    fun bindView(viewOwner: Any)

    fun onBackClicked()
    fun onUpClicked()
}