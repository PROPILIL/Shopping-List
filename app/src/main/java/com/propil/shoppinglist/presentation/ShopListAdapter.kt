package com.propil.shoppinglist.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.propil.shoppinglist.R
import com.propil.shoppinglist.domain.ShopItem
import java.lang.RuntimeException

class ShopListAdapter: RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var count = 0

    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()

        }
    //LongClickListener without interface
    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    //как создать вью
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        Log.d("ShopListAdapter", "onCreateViewHolder created ${++count}")

        val layout = when (viewType) {
            0 -> R.layout.item_shop_disabled
            1 -> R.layout.item_shop_enabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    // как присвоить вью значения
    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = shopList[position]
        holder.cardTitle.text = shopItem.name
        holder.cardCount.text = shopItem.count.toString()
        holder.view.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        holder.view.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
    }
    // количество вью
    override fun getItemCount(): Int {
        return shopList.size
    }

     override fun getItemViewType(position: Int): Int {
         val shopItem = shopList[position]

         return if (shopItem.enabled){
             VIEW_TYPE_ENABLED
         }
            else {
             VIEW_TYPE_DISABLED
            }
    }

    // класс для хранения данных о вью
    class ShopItemViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val cardTitle: TextView = view.findViewById(R.id.card_title)
        val cardCount: TextView = view.findViewById(R.id.card_count)
    }

    companion object {

        const val VIEW_TYPE_DISABLED = 0
        const val VIEW_TYPE_ENABLED = 1
        const val MAX_VIEW_HOLDER_POOL = 20

    }

}