package com.gk.app.android.webservice.items

import com.google.gson.annotations.SerializedName

internal class ItemsResponseImpl(
    @SerializedName("items")
    override val items: List<ItemImpl>
) : ItemsResponse