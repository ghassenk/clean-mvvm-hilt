package com.gk.app.android.webservice.items

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

internal class ItemsWebServiceImpl : ItemsWebService {

    override suspend fun getItems(
        pageId: Long,//TODO use later in path
        limit: Int,
        offset: Int,
        sort: String?,
        maxAge: Int
    ): ItemsResponse {
        return endpoint.getItems(
            limit = limit,
            offset = offset,
            sort = sort
        )
    }

    private val endpoint: ItemsEndPoint by lazy {
        val url = "http://192.168.0.16:8080/"
        val okHttpClient = OkHttpClient.Builder().build()
        val builder = Retrofit.Builder()
        val retrofit = builder.baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ItemsEndPoint::class.java)
    }
}

private interface ItemsEndPoint {
    @GET("items")
    suspend fun getItems(
        @Header("Cache-Control") cacheControl: String? = null,
        @Header("User-Agent") userAgent: String = "android-app",
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("sort") sort: String?
    ): ItemsResponseImpl
}