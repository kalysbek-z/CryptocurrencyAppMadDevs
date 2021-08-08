package com.example.cryptocurrencyappmaddevs

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrencyViewModel(
    private val repository: CurrencyRepository
) : ViewModel() {
    var currencyList: MutableLiveData<MutableList<CurrencyItem>> = MutableLiveData()

    fun getCurrencyList() {
        val response = repository.getCurrencyList()

        response.enqueue(object : Callback<MutableList<CurrencyItem>> {
            override fun onResponse(
                call: Call<MutableList<CurrencyItem>>,
                response: Response<MutableList<CurrencyItem>>
            ) {
                currencyList.postValue(response.body())
            }

            override fun onFailure(call: Call<MutableList<CurrencyItem>>, t: Throwable) {
                Log.d("viewmodel", "failed")
            }
        })
    }
}