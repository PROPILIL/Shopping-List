package com.propil.shoppinglist.domain

interface ShopListRepository {

    fun getShopList(): List<ShopItem>

    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun getShopItem(shopItemId: Int): ShopItem

    fun editShopItem(shopItem: ShopItem)
}