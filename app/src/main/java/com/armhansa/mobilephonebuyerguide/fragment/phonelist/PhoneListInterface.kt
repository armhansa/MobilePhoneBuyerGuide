package com.armhansa.mobilephonebuyerguide.fragment.phonelist

import com.armhansa.mobilephonebuyerguide.entity.PhoneEntity

interface PhoneListInterface {
    fun toastError(t: Throwable)
    fun setPhoneList(phones: List<PhoneEntity>)
}