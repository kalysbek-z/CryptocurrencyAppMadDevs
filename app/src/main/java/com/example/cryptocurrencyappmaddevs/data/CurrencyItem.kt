package com.example.cryptocurrencyappmaddevs.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "currencies")
data class CurrencyItem(
    @PrimaryKey var name: String,
    @SerializedName("logo_url")
    var logo: String,
    @SerializedName("currency")
    var tag: String,
    var price: Double
)
