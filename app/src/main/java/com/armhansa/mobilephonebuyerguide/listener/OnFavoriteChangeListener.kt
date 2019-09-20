package com.armhansa.mobilephonebuyerguide.listener

import com.armhansa.mobilephonebuyerguide.model.PhoneModel

interface OnFavoriteChangeListener {
    fun setFavorites(favorites: ArrayList<PhoneModel>)
    fun sortFavorites()
    fun add(newFavorite: PhoneModel)
    fun remove(removeFavorite: PhoneModel)
}