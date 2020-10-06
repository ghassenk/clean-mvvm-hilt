package com.gk.app.android.testingviewmodels.ui.detail

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gk.app.testingviewmodels.domain.detail.DetailUseCase
import kotlinx.coroutines.launch

class DetailViewModelImpl @ViewModelInject constructor(
    private val detailUseCase: DetailUseCase,
    @Assisted savedStateHandle: SavedStateHandle
) : DetailViewModel, ViewModel() {

    private lateinit var itemId: String

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
    override fun getItemDetails() {
        viewModelScope.launch {
            detailUseCase.getItemDetails(itemId)
        }
    }
    //endregion
}
