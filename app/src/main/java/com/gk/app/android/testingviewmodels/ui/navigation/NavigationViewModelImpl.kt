package com.gk.app.android.testingviewmodels.ui.navigation

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gk.app.testingviewmodels.domain.navigation.NavigationUseCase

class NavigationViewModelImpl @ViewModelInject constructor(
    private val navigationUseCase: NavigationUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : NavigationViewModel, ViewModel() {

    override fun bindToView(viewOwner: Any) {

    }

    override fun startNavigation() {
        navigationUseCase.startNavigation()
    }

    override fun onCleared() {
        navigationUseCase.terminate()
        super.onCleared()
    }
}