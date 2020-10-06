package com.gk.app.android.testingviewmodels.domain

interface MainUseCase{
    fun terminate()
    fun onButtonClicked(itemId: String)
}