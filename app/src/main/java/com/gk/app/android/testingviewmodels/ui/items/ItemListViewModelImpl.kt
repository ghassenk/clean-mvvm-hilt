package com.gk.app.android.testingviewmodels.ui.items

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.gk.app.testingviewmodels.domain.items.Item
import com.gk.app.testingviewmodels.domain.items.ItemsUseCase
import kotlinx.coroutines.launch

class ItemListViewModelImpl @ViewModelInject constructor(
    private val itemsUseCase: ItemsUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ItemListViewModel, ViewModel() {

    //region Life Cycle
    init {
        Log.v(javaClass.simpleName, "init() savedStateHandle=${savedStateHandle}")
    }

    override fun onCleared() {
        Log.i(javaClass.simpleName, "onCleared()")
        itemsUseCase.terminate()
        super.onCleared()
    }
    //endregion

    //region Bindings
    private val items: MutableLiveData<List<Item>> by lazy { MutableLiveData() }

    override fun bindView(
        viewOwner: Any,
        onItemsUpdate: (items: List<Item>, selectedPosition: Int?) -> Unit
    ) {
        Log.v(javaClass.simpleName, "bindToView() savedStateHandle=${savedStateHandle}")

        if (viewOwner is LifecycleOwner) {
            items.removeObservers(viewOwner)
            items.observe(viewOwner, Observer {
                onItemsUpdate(it, savedStateHandle.get("selectedPosition"))
            })
            refresh()
        }
    }

    private fun refresh() {
        Log.v(javaClass.simpleName, "refresh()")

        viewModelScope.launch {
            val result = itemsUseCase.getItems()
            items.value = result
        }
    }
    //endregion

    //region User Inputs
    override fun onItemClicked(position: Int) {
        Log.v(javaClass.simpleName, "onButtonClicked() position=$position")

        items.value?.let {
            savedStateHandle["selectedPosition"] = position
            itemsUseCase.openItemDetails(it[position].id)
        }
    }
    //endregion

}