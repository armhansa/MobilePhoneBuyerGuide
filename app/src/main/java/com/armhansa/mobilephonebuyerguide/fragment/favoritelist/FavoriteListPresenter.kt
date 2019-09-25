package com.armhansa.mobilephonebuyerguide.fragment.favoritelist

import android.content.SharedPreferences
import com.armhansa.mobilephonebuyerguide.extension.SortingPhoneModelList
import com.armhansa.mobilephonebuyerguide.model.PhoneModel

class FavoriteListPresenter(private val pref: SharedPreferences?) {

    private var favorites: ArrayList<PhoneModel> = arrayListOf()

    fun getFavorites(): ArrayList<PhoneModel> = favorites

    fun setFavorites(favorites: ArrayList<PhoneModel>) {
        this.favorites = favorites
        sortFavorites()
    }

    fun removeFavoriteAt(index: Int): PhoneModel {
        val removeFavorite = favorites[index]
        favorites.removeAt(index)
        pref?.edit()?.run {
            putBoolean("FAV_${removeFavorite.id}", false)
            apply()
        }
        return removeFavorite
    }

    fun addFavorite(newFavorite: PhoneModel) {
        favorites.add(newFavorite)
    }

    fun removeFavorite(removeFavorite: PhoneModel) {
        favorites.remove(removeFavorite)
    }

    fun sortFavorites(): ArrayList<PhoneModel> {
        favorites = SortingPhoneModelList.sorted(favorites)
        return favorites
    }

}
