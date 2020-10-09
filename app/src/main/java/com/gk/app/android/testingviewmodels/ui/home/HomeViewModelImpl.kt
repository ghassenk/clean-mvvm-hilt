package com.gk.app.android.testingviewmodels.ui.home

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gk.app.testingviewmodels.domain.home.HomeUseCase

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


    //region API
    override fun onButtonClicked(itemId: String) {
        Log.v(javaClass.simpleName, "onButtonClicked() itemId=$itemId")

        homeUseCase.onButtonClicked(itemId)
    }
    //endregion
}