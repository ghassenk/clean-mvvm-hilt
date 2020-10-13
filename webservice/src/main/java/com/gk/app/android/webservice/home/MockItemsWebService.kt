package com.gk.app.android.webservice.home

import com.gk.app.testingviewmodels.domain.home.Item

internal class MockItemsWebService : ItemsWebService {

    override suspend fun getItems(
        pageId: Long,
        limit: Int,
        offset: Int,
        sort: String?,
        maxAge: Int
    ): ItemsResponse {
        return object :ItemsResponse {
            override val items: List<Item>
                get() = generateMockItems()
        }
    }

    private fun generateMockItems() : List<Item> {
        return MutableList(20) {
            object : Item {
                override val id: String
                    get() = toString()
            }
        }
    }
}