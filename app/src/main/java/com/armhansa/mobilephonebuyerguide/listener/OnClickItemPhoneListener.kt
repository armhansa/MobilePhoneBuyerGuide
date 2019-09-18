package com.armhansa.mobilephonebuyerguide.listener

import com.armhansa.mobilephonebuyerguide.model.PhoneModel

interface OnClickItemPhoneListener {
    fun sendToDetailPage(phoneModel: PhoneModel)
}
