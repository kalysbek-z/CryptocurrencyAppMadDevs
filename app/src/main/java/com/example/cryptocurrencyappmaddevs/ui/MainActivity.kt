package com.example.cryptocurrencyappmaddevs.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrencyappmaddevs.*
import com.example.cryptocurrencyappmaddevs.adapter.CryptoCurrenciesAdapter
import com.example.cryptocurrencyappmaddevs.data.CurrencyItem
import com.example.cryptocurrencyappmaddevs.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var cryptoCurrenciesAdapter: CryptoCurrenciesAdapter
    private val viewModel: CurrencyViewModel by viewModels()
    var list: List<CurrencyItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()

        viewModel.currencies.observe(this) {
            cryptoCurrenciesAdapter.setData(it.data)
            list = it.data

            progress_bar.isVisible = it is Resource.Loading && it.data.isNullOrEmpty()
        }

        search_et.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                filterData(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
        })
    }

    private fun filterData(query: String) {
        var filteredList: MutableList<CurrencyItem> = ArrayList()
        for (item: CurrencyItem in list!!) {
            if (item.name.toLowerCase().contains(query)) {
                filteredList.add(item)
            }
        }
        cryptoCurrenciesAdapter.setData(filteredList)
    }

    private fun initRecyclerView() {
        cryptoCurrenciesAdapter = CryptoCurrenciesAdapter(this)
        recyclerview.adapter = cryptoCurrenciesAdapter
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.setHasFixedSize(true)
    }
}