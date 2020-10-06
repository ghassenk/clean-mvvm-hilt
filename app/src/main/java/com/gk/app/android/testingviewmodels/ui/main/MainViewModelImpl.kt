package com.gk.app.android.testingviewmodels.ui.main

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gk.app.testingviewmodels.domain.main.MainUseCase

class MainViewModelImpl @ViewModelInject constructor(
    private val mainUseCase: MainUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : MainViewModel, ViewModel() {

    //region Life Cycle

    init {
        Log.i(javaClass.simpleName, "init()")
    }

    override fun onCleared() {
        Log.i(javaClass.simpleName, "onCleared()")
        mainUseCase.terminate()
        super.onCleared()
    }
    //endregion


    //region API
    override fun onButtonClicked(itemId: String) {
        Log.v(javaClass.simpleName, "onButtonClicked() itemId=$itemId")

        mainUseCase.onButtonClicked(itemId)
    }
    //endregion
}