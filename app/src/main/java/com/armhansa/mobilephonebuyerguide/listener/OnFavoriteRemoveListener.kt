package com.armhansa.mobilephonebuyerguide.listener

import com.armhansa.mobilephonebuyerguide.model.PhoneModel

interface OnFavoriteRemoveListener {
    fun changeStarState(phoneModel: PhoneModel)
}