package com.example.cryptocurrencyappmaddevs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.currency_item.view.*
import java.util.*

class CryptoCurrenciesAdapter(
    val context: Context
) :
    RecyclerView.Adapter<CryptoCurrenciesAdapter.CryptoCurrenciesViewHolder>() {

    private var list: MutableList<CurrencyItem> = ArrayList()

    inner class CryptoCurrenciesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logo: ImageView = itemView.currency_logo_iv
        val name: TextView = itemView.currency_name_tv
        val tag: TextView = itemView.currency_tag_tv
        val price: TextView = itemView.currency_price_tv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoCurrenciesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.currency_item, parent, false)
        return CryptoCurrenciesViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CryptoCurrenciesViewHolder, position: Int) {
        val currentItem = list[position]

        Glide.with(context)
            .load(currentItem.logo)
            .centerCrop()
            .dontTransform()
            .into(holder.logo) 
        // Glide throws exceptions because some images from server are .svg extension
        // I can implement some svg decoder library but i will not do it

        holder.name.text = currentItem.name
        holder.tag.text = currentItem.tag
        holder.price.text =
            java.lang.String.format("%.2f", currentItem.price) // format Double to String
    }

    fun setData(data: MutableList<CurrencyItem>) {
        this.list = data
        notifyDataSetChanged()
    }
}