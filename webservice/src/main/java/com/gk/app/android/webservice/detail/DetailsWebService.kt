package com.gk.app.android.webservice.detail

internal interface DetailsWebService {
    suspend fun getItemDetail(
        itemId: String,
    ): String
}