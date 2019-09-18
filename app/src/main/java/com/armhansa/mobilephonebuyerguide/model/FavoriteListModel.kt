package com.armhansa.mobilephonebuyerguide.model

import com.armhansa.mobilephonebuyerguide.listener.OnPhoneModelsChangeListener

class FavoriteListModel private constructor(private var favorites: ArrayList<PhoneModel>) {

    private var listener: OnPhoneModelsChangeListener? = null

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

    fun setListener(listener: OnPhoneModelsChangeListener) {
        this.listener = listener
    }

    fun callbackListener() {
        listener?.refreshPhoneModels()
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