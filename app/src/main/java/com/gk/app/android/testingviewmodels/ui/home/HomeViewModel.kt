package com.gk.app.android.testingviewmodels.ui.home

import com.gk.app.testingviewmodels.domain.home.Item

interface HomeViewModel {
    fun onItemClick(itemId: String)
    fun bindItems(viewOwner: Any, updateLambda: (List<Item>) -> Unit)
}