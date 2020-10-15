package com.gk.app.android.testingviewmodels.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.gk.app.testingviewmodels.domain.detail.DetailUseCase
import kotlinx.coroutines.launch

class DetailViewModelImpl @ViewModelInject constructor(
    private val detailUseCase: DetailUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : DetailViewModel, ViewModel() {

    private var args: Bundle? = null
    private val detail: MutableLiveData<String> by lazy { MutableLiveData() }

    //region Life Cycle
    init {
        Log.v(javaClass.simpleName, "init() savedStateHandle=${savedStateHandle}")
    }

    override fun onCleared() {
        Log.i(javaClass.simpleName, "onCleared()")
        detailUseCase.terminate()
        super.onCleared()
    }
    //endregion

    //region API

    override fun bindView(viewOwner: Any, onDetailUpdate: (detail: String) -> Unit) {
        if (viewOwner is Fragment) {
            this.args = viewOwner.arguments
        }

        Log.v(javaClass.simpleName, "bindView() args=$args " +
                "savedStateHandle=${savedStateHandle}")

        if (viewOwner is LifecycleOwner) {
            detail.removeObservers(viewOwner)
            detail.observe(viewOwner, Observer(onDetailUpdate))
            refresh()
        }


    }

    private fun refresh() {
        // If we have new arguments => we use them, and save them in the savedStateHandle for
        // potential restoration, otherwise we try to restore from current savedStateHandle!
        var itemId: String? = null
        args?.let {
            itemId = args?.getString("itemId")
            savedStateHandle["itemId"] = itemId
        } ?: run { itemId = savedStateHandle.get<String>("itemId") }

        Log.v(javaClass.simpleName, "refresh() itemId = $itemId")

        viewModelScope.launch {
            itemId?.let {
                val result = detailUseCase.getItemDetails(it)
                detail.value = result
            }
        }
    }

    //endregion
}
