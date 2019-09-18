package com.armhansa.mobilephonebuyerguide.model

import com.armhansa.mobilephonebuyerguide.constaint.SortType
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

    fun sort(sortType: SortType) {
        val newFavorites: ArrayList<PhoneModel> = arrayListOf()
        for (i in 0 until favorites.count()) {
            var startValue: Float = if (sortType != SortType.RATING) favorites[0].price else favorites[0].rating
            var targetIndex = 0
            for (j in 0 until favorites.count()) {
                if ((sortType == SortType.LOW_PRICE && favorites[j].price < startValue)
                    || (sortType == SortType.HIGH_PRICE && favorites[j].price > startValue)
                ) {
                    targetIndex = j
                    startValue = favorites[j].price
                } else if (sortType == SortType.RATING && favorites[j].rating > startValue) {
                    targetIndex = j
                    startValue = favorites[j].rating
                }
            }
            newFavorites.add(favorites[targetIndex])
            favorites.removeAt(targetIndex)
        }

        favorites = newFavorites
    }

}
