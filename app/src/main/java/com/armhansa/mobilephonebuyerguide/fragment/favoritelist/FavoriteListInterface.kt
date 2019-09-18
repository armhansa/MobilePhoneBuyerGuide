package com.armhansa.mobilephonebuyerguide.fragment.favoritelist

import com.armhansa.mobilephonebuyerguide.entity.PhoneEntity

interface FavoriteListInterface {
    fun toastError(t: Throwable)
    fun setFavoriteList(phones: List<PhoneEntity>)
}