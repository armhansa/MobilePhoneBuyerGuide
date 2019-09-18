package com.armhansa.mobilephonebuyerguide.fragment.favoritelist

class FavoriteListPresenter(
    val listener: FavoriteListInterface
) {
    companion object {
        fun getInstance(listener: FavoriteListInterface) = FavoriteListPresenter(listener)
    }


}