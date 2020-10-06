package com.gk.app.android.testingviewmodels.domain

interface DetailUseCase {
    fun terminate()
    suspend fun getItemDetails(itemId: String): List<Any>

}