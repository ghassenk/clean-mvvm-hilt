package com.gk.app.android.webservice.home

import com.gk.app.testingviewmodels.domain.home.Item


internal interface ItemsResponse {
    val items: List<Item>
}