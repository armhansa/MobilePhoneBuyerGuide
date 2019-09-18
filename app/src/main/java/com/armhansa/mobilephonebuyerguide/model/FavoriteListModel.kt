package com.armhansa.mobilephonebuyerguide.model

import com.armhansa.mobilephonebuyerguide.listener.OnFavoriteChangeListener

class FavoriteListModel private constructor(private var favorites: ArrayList<PhoneModel>) {

    private var listener: OnFavoriteChangeListener? = null

    companion object {
        private var favoriteInstance: FavoriteListModel? = null

        fun getInstance(): FavoriteListModel {
            if (favoriteInstance == null) {
                favoriteInstance = FavoriteListModel(arrayListOf())
            }
            return favoriteInstance!!
        }
    }

    fun getFavorites(): ArrayList<PhoneModel> = favorites

    fun setListener(listener: OnFavoriteChangeListener) {
        this.listener = listener
    }

    fun callbackListener() {
        listener?.refreshFavorites()
    }

    fun reset() {
        favorites = arrayListOf()
    }

    fun add(newFavorite: PhoneModel) {
        favorites.add(newFavorite)
    }

    fun remove(removeFavorite: PhoneModel) {
        favorites.remove(removeFavorite)
    }

}