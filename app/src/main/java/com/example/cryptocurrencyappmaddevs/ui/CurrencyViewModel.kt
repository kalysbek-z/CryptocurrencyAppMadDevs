package com.example.cryptocurrencyappmaddevs.ui

import androidx.lifecycle.*
import com.example.cryptocurrencyappmaddevs.repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    repository: CurrencyRepository
) : ViewModel() {

    val currencies =
        repository.getCurrencies().asLiveData() // asLiveData will made all things commented below

//    private val perPage = 50 // amount of items
//    private val page = 1 // page number
//
//    private val currencyListLiveData = MutableLiveData<List<CurrencyItem>>()
//    val currencies: LiveData<List<CurrencyItem>> = currencyListLiveData
//
//    init {
//        viewModelScope.launch {
//            val currencyList = api.getCurrencyList(ConstValues.API_KEY, perPage, page)
//            currencyListLiveData.value = currencyList
//        }
//    }
}