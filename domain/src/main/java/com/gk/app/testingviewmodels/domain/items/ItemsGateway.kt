package com.gk.app.testingviewmodels.domain.items

interface ItemsGateway {
    suspend fun getItems(): List<Item>
}