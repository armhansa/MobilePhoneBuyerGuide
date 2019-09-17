package com.armhansa.mobilephonebuyerguide

import com.armhansa.mobilephonebuyerguide.entity.PhoneImageEntity

interface PhoneDetailInterface {
    fun toastError(t: Throwable)
    fun setPhoneImageList(images: List<PhoneImageEntity>)
}