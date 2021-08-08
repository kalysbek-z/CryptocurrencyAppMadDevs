package com.example.cryptocurrencyappmaddevs

import com.google.gson.annotations.SerializedName

data class CurrencyItem(
    var name: String?,
    @SerializedName("logo_url")
    var logo: String?,
    @SerializedName("currency")
    var tag: String?,
    var price: Double?
)
