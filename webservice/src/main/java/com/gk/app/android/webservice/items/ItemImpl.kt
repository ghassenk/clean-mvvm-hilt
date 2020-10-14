package com.gk.app.android.webservice.items

import com.gk.app.testingviewmodels.domain.items.Item
import com.google.gson.annotations.SerializedName

internal class ItemImpl(
    @SerializedName("id")
    override val id: String
) : Item