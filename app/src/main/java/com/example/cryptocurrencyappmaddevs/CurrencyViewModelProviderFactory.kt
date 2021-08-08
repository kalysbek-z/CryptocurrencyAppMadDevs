package com.example.cryptocurrencyappmaddevs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CurrencyViewModelProviderFactory constructor(private val repository: CurrencyRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CurrencyViewModel::class.java)) {
            CurrencyViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}