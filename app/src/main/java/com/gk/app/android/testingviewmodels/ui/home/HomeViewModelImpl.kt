package com.gk.app.android.testingviewmodels.ui.home

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.gk.app.testingviewmodels.domain.home.HomeUseCase
import com.gk.app.testingviewmodels.domain.home.Item
import kotlinx.coroutines.launch

class HomeViewModelImpl @ViewModelInject constructor(
    private val homeUseCase: HomeUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : HomeViewModel, ViewModel() {

    //private var autoSelect: Boolean? = null //TODO also add save/restore current selection

    //region Life Cycle
    init {
        //autoSelect = savedStateHandle.get<Boolean>("autoSelect")
        Log.v(javaClass.simpleName, "init()")
    }

    override fun onCleared() {
        Log.i(javaClass.simpleName, "onCleared()")
        homeUseCase.terminate()
        super.onCleared()
    }
    //endregion

    //region Bindings
    private val items: MutableLiveData<List<Item>> by lazy { MutableLiveData() }
    private var selectedPosition: Int? = null

    override fun bindToView(
        viewOwner: Any,
        onItemsUpdate: (items: List<Item>, selectedPosition: Int?) -> Unit
    ) {
        Log.v(javaClass.simpleName, "bindToView()")

        if (viewOwner is LifecycleOwner) {
            items.removeObservers(viewOwner)
            items.observe(viewOwner, Observer {
                onItemsUpdate(it, selectedPosition)
            })
            refresh()
        }
    }

    private fun refresh() {
//        Log.v(javaClass.simpleName, "refresh()")
        Log.v(javaClass.simpleName, "refresh() autoSelect=${savedStateHandle.get<Boolean>("autoSelect")}")
        viewModelScope.launch {
            val result = homeUseCase.getHomeItems()
            Log.i(javaClass.simpleName, "received ${result.size} items!")
            val autoSelect = savedStateHandle.get<Boolean>("autoSelect")
            if (autoSelect == true) {
                if (result.isNotEmpty()) {
                    selectedPosition = 0
                    savedStateHandle["autoSelect"] = false
                    homeUseCase.openItemDetails(result[0].id)
                }
            } else {
                selectedPosition = null
            }

            items.value = result
        }
    }
    //endregion

    //region User Inputs
    override fun onItemClicked(itemId: String) {
        Log.v(javaClass.simpleName, "onButtonClicked() itemId=$itemId")

        homeUseCase.openItemDetails(itemId)
    }

    //endregion

}