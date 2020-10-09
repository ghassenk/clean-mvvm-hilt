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

    //region Life Cycle
    init {
        Log.i(javaClass.simpleName, "init()")
    }

    override fun onCleared() {
        Log.i(javaClass.simpleName, "onCleared()")
        homeUseCase.terminate()
        super.onCleared()
    }
    //endregion


    //region Bindings
    private val items: MutableLiveData<List<Item>> by lazy { MutableLiveData() }

    override fun bindItems(viewOwner: Any, updateLambda: (List<Item>) -> Unit) {
        if (viewOwner is LifecycleOwner) {
            items.removeObservers(viewOwner)
            items.observe(viewOwner, Observer {
                updateLambda(it)
            })
            refresh()
        }
    }

    private fun refresh() {
        Log.v(javaClass.simpleName, "refresh()")
        viewModelScope.launch {
            val result = homeUseCase.getHomeItems()
            Log.i(javaClass.simpleName, "received ${result.size} items!")
            items.value = result
        }
    }
    //endregion

    //region User Inputs
    override fun onItemClick(itemId: String) {
        Log.v(javaClass.simpleName, "onButtonClicked() itemId=$itemId")

        homeUseCase.onItemClick(itemId)
    }
    //endregion
}