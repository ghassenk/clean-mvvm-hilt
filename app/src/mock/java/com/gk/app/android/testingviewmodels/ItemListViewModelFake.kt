package com.gk.app.android.testingviewmodels

import com.gk.app.android.testingviewmodels.ui.items.ItemListViewModel
import com.gk.app.testingviewmodels.domain.items.Item

open class ItemListViewModelFake : ItemListViewModel {
    override fun onItemClicked(itemId: String) {
        TODO("Not yet implemented")
    }

    override fun bindToView(
        viewOwner: Any,
        onItemsUpdate: (items: List<Item>, selectedPosition: Int?) -> Unit
    ) {
        TODO("Not yet implemented")
    }
}