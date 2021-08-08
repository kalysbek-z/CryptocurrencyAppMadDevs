package com.example.cryptocurrencyappmaddevs

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceAPI {

    @GET("currencies/ticker")
    fun getCurrencyList(
        @Query("key") apiKey: String,
        @Query("sort") sort: String,
        @Query("per-page") perPage: Int,
        @Query("page") page: Int
    ): Call<MutableList<CurrencyItem>>

    companion object {
        var serviceAPI: ServiceAPI? = null

        fun getInstance(): ServiceAPI {
            if (serviceAPI == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(ConstValues.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                serviceAPI = retrofit.create(ServiceAPI::class.java)
            }
            return serviceAPI!!
        }
    }
}