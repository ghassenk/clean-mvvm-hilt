package com.gk.app.android.testingviewmodels.ui.detail

interface DetailViewModel {
    fun bindView(viewOwner: Any, onDetailUpdate: (detail:String) -> Unit)
}
