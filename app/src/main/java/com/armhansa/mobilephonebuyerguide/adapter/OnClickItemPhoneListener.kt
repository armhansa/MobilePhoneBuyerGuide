package com.armhansa.mobilephonebuyerguide.adapter

import com.armhansa.mobilephonebuyerguide.entity.PhoneEntity

interface OnClickItemPhoneListener {
    fun sendToDetailPage(phoneEntity: PhoneEntity)
}