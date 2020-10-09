package com.gk.app.testingviewmodels.domain.home

interface ItemsGateway {
    suspend fun getItems(): List<Item>
    suspend fun getItemDetail(itemId:String): List<Any>
}