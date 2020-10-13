package com.gk.app.android.webservice.detail

import com.gk.app.testingviewmodels.domain.detail.DetailsGateway

class DetailsGatewayImpl : DetailsGateway {

    override suspend fun getItemDetail(itemId: String): String {
        return DetailsWebServiceImpl().getItemDetail(itemId)
    }

}