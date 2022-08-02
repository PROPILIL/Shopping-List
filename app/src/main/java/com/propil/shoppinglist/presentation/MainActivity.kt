package com.propil.shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.propil.shoppinglist.R
import com.propil.shoppinglist.domain.ShopItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var linearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayout = findViewById(R.id.linearLayout)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            showList(it)
        }
    }

    private fun showList(list : List<ShopItem>) {
        linearLayout.removeAllViews()
        for (shopItem in list) {
            val layoutId = if (shopItem.enabled) {
                R.layout.item_shop_enabled
            } else {
                R.layout.item_shop_disabled
            }
            //преобразовываем макет во вью элемент через Layout inflater
            val view = LayoutInflater.from(this).inflate(layoutId, linearLayout, false)
            // ищем тайтл и количество
            val textViewTitle = view.findViewById<TextView>(R.id.card_title)
            val textViewCount = view.findViewById<TextView>(R.id.card_count)
            // устанавливаем значения
            textViewTitle.text = shopItem.name
            textViewCount.text = shopItem.count.toString()
            view.setOnLongClickListener {
                viewModel.switchEnableState(shopItem)
                true
            }
            linearLayout.addView(view)
        }
    }
}