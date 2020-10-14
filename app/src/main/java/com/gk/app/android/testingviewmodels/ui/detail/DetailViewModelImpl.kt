package com.gk.app.android.testingviewmodels.ui.detail

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.gk.app.testingviewmodels.domain.detail.DetailUseCase
import kotlinx.coroutines.launch

class DetailViewModelImpl @ViewModelInject constructor(
    private val detailUseCase: DetailUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : DetailViewModel, ViewModel() {

    private val detail: MutableLiveData<String> by lazy { MutableLiveData() }

    //region Life Cycle
    init {
        val itemId=  savedStateHandle.get<String>("itemId")
        Log.i(javaClass.simpleName, "init() itemId = $itemId")
    }

    override fun onCleared() {
        Log.i(javaClass.simpleName, "onCleared()")
        detailUseCase.terminate()
        super.onCleared()
    }
    //endregion

    //region API

    override fun bindView(viewOwner: Any, onDetailUpdate: (detail: String) -> Unit) {
        Log.v(javaClass.simpleName, "bindView() savedStateHandle=${savedStateHandle}")

        if (viewOwner is LifecycleOwner) {
            detail.removeObservers(viewOwner)
            detail.observe(viewOwner, Observer(onDetailUpdate))
            refresh()
        }
    }

    private fun refresh() {
        val itemId=  savedStateHandle.get<String>("itemId")
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
