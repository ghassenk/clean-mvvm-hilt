package com.gk.app.android.testingviewmodels.ui.items

import com.gk.app.testingviewmodels.domain.items.Item

interface ItemListViewModel {
    fun onItemClicked(position: Int)
    fun bindToView(
        viewOwner: Any,
        onItemsUpdate: (items: List<Item>, selectedPosition: Int?) -> Unit
    )
}