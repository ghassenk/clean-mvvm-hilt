package com.gk.app.android.webservice.home

import com.gk.app.testingviewmodels.domain.home.Item
import com.google.gson.annotations.SerializedName

internal class ItemImpl(
    @SerializedName("id")
    override val id: String
) : Item