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
        Log.i(javaClass.simpleName, "init()")
    }

    override fun onCleared() {
        Log.i(javaClass.simpleName, "onCleared()")
        detailUseCase.terminate()
        super.onCleared()
    }
    //endregion

    //region API

    override fun bindView(viewOwner: Any, onDetailUpdate: (detail: String) -> Unit) {
        if (viewOwner is LifecycleOwner) {
            detail.removeObservers(viewOwner)
            detail.observe(viewOwner, Observer(onDetailUpdate))
            refresh()
        }
    }

    private fun refresh() {
        viewModelScope.launch {
            savedStateHandle.get<String>("itemId")?.let {
                val result = detailUseCase.getItemDetails(it)
                detail.value = result
            }
        }
    }

    //endregion
}
