package com.gk.app.android.webservice

import com.gk.app.android.webservice.home.ItemsGatewayImpl
import com.gk.app.testingviewmodels.domain.home.ItemsGateway

object WebService {
    object Factory {
        fun getItemsGateway(): ItemsGateway {
            return ItemsGatewayImpl()
        }
    }
}