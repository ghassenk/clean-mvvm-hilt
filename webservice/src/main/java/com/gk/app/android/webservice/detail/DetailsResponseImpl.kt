package com.gk.app.android.webservice.detail

import com.google.gson.annotations.SerializedName

internal class DetailsResponseImpl(
    @SerializedName("details")
    override val details: String
) : DetailsResponse