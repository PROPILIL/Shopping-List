package com.propil.shoppinglist.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.propil.shoppinglist.R
import com.propil.shoppinglist.domain.ShopItem

class ShopListAdapter: RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    val list = listOf<ShopItem>()
    //как создать вью
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shop_enabled, parent, false)
        return ShopItemViewHolder(view)
    }

    // как присвоить вью значения
    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = list[position]
        holder.cardTitle.text = shopItem.name
        holder.cardCount.text = shopItem.count.toString()
        holder.view.setOnLongClickListener {
            Log.d("RECYCLERVIEW", "ShopItem with id $shopItem.id is longClicked")
            true
        }
    }
    // количество вью
    override fun getItemCount(): Int {
        return list.size
    }

    // класс для хранения данных о вью
    class ShopItemViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val cardTitle: TextView = view.findViewById(R.id.card_title)
        val cardCount: TextView = view.findViewById(R.id.card_count)
    }

}