package com.armhansa.mobilephonebuyerguide.fragment.favoritelist

import android.content.SharedPreferences
import com.armhansa.mobilephonebuyerguide.extension.SortingPhoneModelList
import com.armhansa.mobilephonebuyerguide.model.PhoneModel

class FavoriteListPresenter(
    private val pref: SharedPreferences?
) {
    companion object {
        fun getInstance(
            pref: SharedPreferences?
        ) = FavoriteListPresenter(pref)
    }

    private var favorites: ArrayList<PhoneModel> = arrayListOf()

    fun getFavorites(): ArrayList<PhoneModel> = favorites

    fun setFavorites(favorites: ArrayList<PhoneModel>) {
        this.favorites = SortingPhoneModelList.sorted(favorites)
    }

    fun removeFavoriteAt(index: Int): PhoneModel {
        val removeFavorite = favorites[index]
        favorites.removeAt(index)
        pref?.let {
            val prefEditor = it.edit()
            prefEditor.putBoolean("FAV_${removeFavorite.id}", false)
            prefEditor.apply()
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
