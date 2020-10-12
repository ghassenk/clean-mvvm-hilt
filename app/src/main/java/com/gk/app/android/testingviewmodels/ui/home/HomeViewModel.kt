package com.gk.app.android.testingviewmodels.ui.home

import com.gk.app.testingviewmodels.domain.home.Item

interface HomeViewModel {
    fun onItemClicked(itemId: String)
    fun bindToView(
        viewOwner: Any,
        onItemsUpdate: (items: List<Item>, selectedPosition: Int?) -> Unit
    )
}