package com.gk.app.android.webservice

import com.gk.app.android.webservice.detail.DetailsGatewayImpl
import com.gk.app.android.webservice.home.ItemsGatewayImpl
import com.gk.app.testingviewmodels.domain.detail.DetailsGateway
import com.gk.app.testingviewmodels.domain.home.ItemsGateway

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