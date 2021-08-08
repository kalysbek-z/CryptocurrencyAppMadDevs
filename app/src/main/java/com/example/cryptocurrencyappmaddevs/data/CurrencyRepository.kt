package com.example.cryptocurrencyappmaddevs.data

import androidx.room.withTransaction
import com.example.cryptocurrencyappmaddevs.api.ServiceAPI
import com.example.cryptocurrencyappmaddevs.utils.ConstValues
import com.example.cryptocurrencyappmaddevs.utils.networkBoundResource
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val api: ServiceAPI,
    private val db: CurrencyDatabase
) {
    private val currencyDao = db.currencyDao()

    fun getCurrencies() = networkBoundResource(
        query = {
            currencyDao.getAllCurrencies()
        },
        fetch = {
            api.getCurrencyList(ConstValues.API_KEY, ConstValues.PER_PAGE, ConstValues.PAGE)
        },
        saveFetchResult = {
            db.withTransaction {
                currencyDao.deleteAllData()
                currencyDao.insertCurrencies(it)
            }
        }
    )
}