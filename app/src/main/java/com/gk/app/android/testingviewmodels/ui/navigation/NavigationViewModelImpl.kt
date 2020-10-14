package com.gk.app.android.testingviewmodels.ui.navigation

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gk.app.testingviewmodels.domain.navigation.NavigationUseCase

class NavigationViewModelImpl @ViewModelInject constructor(
    private val navigationUseCase: NavigationUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : NavigationViewModel, ViewModel() {

    init {
        Log.v(javaClass.simpleName, "init()")
    }

    override fun bindView(viewOwner: Any) {
        navigationUseCase.startNavigation()
    }

    override fun onBackClicked() {
        navigationUseCase.onNavigateBack()
    }

    override fun onUpClicked() {
        navigationUseCase.onNavigateUp()
    }


    override fun onCleared() {
        Log.v(javaClass.simpleName, "onCleared()")
        navigationUseCase.terminate()
        super.onCleared()
    }
}