package com.example.cryptocurrencyappmaddevs

class CurrencyRepository constructor(private val serviceAPI: ServiceAPI) {
    private val perPage = 50 // amount of items in request
    private val page = 1 // number of page
    private val sort = "rank" // sort by rank
    fun getCurrencyList() = serviceAPI.getCurrencyList(ConstValues.API_KEY, sort, perPage, page)
}