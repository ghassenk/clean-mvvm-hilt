package com.gk.app.android.webservice.items

internal interface ItemsWebService {
    suspend fun getItems(
        pageId: Long,
        limit: Int = 100,
        offset: Int = 0,
        sort: String? = null,
        maxAge: Int = 0
    ): ItemsResponse
}