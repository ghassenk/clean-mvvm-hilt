package com.gk.app.android.webservice.home

import com.gk.app.testingviewmodels.domain.home.Item
import com.gk.app.testingviewmodels.domain.home.ItemsGateway

class ItemsGatewayImpl : ItemsGateway {

    override suspend fun getItems(): List<Item> {
        return MutableList(20) {
            object : Item {
                override val id: String
                    get() = toString()
            }
        }
    }

    override suspend fun getItemDetail(itemId: String): List<Any> {
        return listOf("detail1", "detail2", "detail3", "detail4")
    }
}