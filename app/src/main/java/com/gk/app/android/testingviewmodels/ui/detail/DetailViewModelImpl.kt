package com.gk.app.android.testingviewmodels.ui.detail

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.gk.app.testingviewmodels.domain.detail.DetailUseCase
import com.gk.app.testingviewmodels.domain.home.Item
import kotlinx.coroutines.launch

class DetailViewModelImpl @ViewModelInject constructor(
    private val detailUseCase: DetailUseCase,
    @Assisted savedStateHandle: SavedStateHandle
) : DetailViewModel, ViewModel() {

    private lateinit var itemId: String
    private val detail: MutableLiveData<String> by lazy { MutableLiveData() }

    //region Life Cycle
    init {
        Log.i(javaClass.simpleName, "init()")
        savedStateHandle.get<String>("itemId")?.let { itemId = it }
            ?: throw IllegalArgumentException("No itemId argument found!")
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
            val result = detailUseCase.getItemDetails(itemId)
            detail.value = result
        }
    }

    //endregion
}
