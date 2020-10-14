package com.gk.app.android.testingviewmodels

import com.gk.app.android.testingviewmodels.ui.items.ItemListViewModel
import com.gk.app.testingviewmodels.domain.items.Item

open class ItemListViewModelSpy : ItemListViewModel {

    var isButtonClicked = false
    var itemId: String? = null

    override fun onItemClicked(itemId: String) {
        isButtonClicked = true
        this.itemId = itemId
    }

    override fun bindToView(
        viewOwner: Any,
        onItemsUpdate: (items: List<Item>, selectedPosition: Int?) -> Unit
    ) {
        TODO("Not yet implemented")
    }
}