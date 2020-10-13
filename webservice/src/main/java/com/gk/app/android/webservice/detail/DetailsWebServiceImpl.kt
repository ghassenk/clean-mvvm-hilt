package com.gk.app.android.webservice.detail

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

internal class DetailsWebServiceImpl : DetailsWebService {

    override suspend fun getItemDetail(itemId: String): String {
        return endpoint.getItemDetail(itemId = itemId).details
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
    @GET("detail")
    suspend fun getItemDetail(
        @Header("Cache-Control") cacheControl: String? = null,
        @Header("User-Agent") userAgent: String = "android-app",
        @Query("id") itemId: String
    ): DetailsResponseImpl
}