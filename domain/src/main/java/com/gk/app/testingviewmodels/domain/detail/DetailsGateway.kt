package com.gk.app.testingviewmodels.domain.detail

interface DetailsGateway {
    suspend fun getItemDetail(itemId:String): String
}