package com.armhansa.mobilephonebuyerguide.activity.phonedetail

import com.armhansa.mobilephonebuyerguide.entity.PhoneImageEntity

interface PhoneDetailInterface {
    fun toastError(t: Throwable)
    fun setPhoneImageList(images: List<PhoneImageEntity>)
}