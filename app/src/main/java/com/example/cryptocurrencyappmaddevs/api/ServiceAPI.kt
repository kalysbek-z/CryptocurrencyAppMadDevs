package com.example.cryptocurrencyappmaddevs.api

import com.example.cryptocurrencyappmaddevs.data.CurrencyItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceAPI {

    @GET("currencies/ticker")
    suspend fun getCurrencyList(
        @Query("key") apiKey: String,
        @Query("per-page") perPage: Int,
        @Query("page") page: Int
    ): List<CurrencyItem>
}