package com.gk.app.android.webservice.items

import com.gk.app.testingviewmodels.domain.items.Item


internal interface ItemsResponse {
    val items: List<Item>
}