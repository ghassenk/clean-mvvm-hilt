package com.gk.app.android.webservice

import com.gk.app.android.webservice.detail.DetailsGatewayImpl
import com.gk.app.android.webservice.items.ItemsGatewayImpl
import com.gk.app.testingviewmodels.domain.detail.DetailsGateway
import com.gk.app.testingviewmodels.domain.items.ItemsGateway

object WebService {
    object Factory {
        fun getItemsGateway(): ItemsGateway {
            return ItemsGatewayImpl()
        }

        fun getDetailsGateway(): DetailsGateway {
            return DetailsGatewayImpl()
        }
    }
}