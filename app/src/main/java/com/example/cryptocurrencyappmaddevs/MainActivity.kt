package com.example.cryptocurrencyappmaddevs

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var cryptoCurrenciesAdapter: CryptoCurrenciesAdapter
    lateinit var viewModel: CurrencyViewModel
    lateinit var list: MutableList<CurrencyItem>

    private val serviceAPI = ServiceAPI.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showProgressBar()

        viewModel = ViewModelProvider(
            this,
            CurrencyViewModelProviderFactory(CurrencyRepository(serviceAPI))
        ).get(CurrencyViewModel::class.java)

        initRecyclerView()

        viewModel.currencyList.observe(this) {
            cryptoCurrenciesAdapter.setData(it)
            list = it
            hideProgressBar()
        }

        viewModel.getCurrencyList()



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
        for (item: CurrencyItem in list) {
            if (item.name?.toLowerCase()?.contains(query) == true) {
                filteredList.add(item)
            }
        }
        cryptoCurrenciesAdapter.setData(filteredList)
    }

    private fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }

    private fun initRecyclerView() {
        cryptoCurrenciesAdapter = CryptoCurrenciesAdapter(this)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = cryptoCurrenciesAdapter
        recyclerview.setHasFixedSize(true)
    }
}