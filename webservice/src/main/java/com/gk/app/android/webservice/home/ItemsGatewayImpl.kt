package com.gk.app.android.webservice.home

import com.gk.app.testingviewmodels.domain.home.Item
import com.gk.app.testingviewmodels.domain.home.ItemsGateway

internal class ItemsGatewayImpl : ItemsGateway {

    override suspend fun getItems(): List<Item> {
        return ItemsWebServiceImpl().getItems(1234L, 100, 0, null, 0).items
//        return MockItemsWebService().getItems(1234L, 100, 0, null, 0).items
    }
}