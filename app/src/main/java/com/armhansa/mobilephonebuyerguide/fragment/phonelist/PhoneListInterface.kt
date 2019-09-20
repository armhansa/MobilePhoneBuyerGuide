package com.armhansa.mobilephonebuyerguide.fragment.phonelist

import com.armhansa.mobilephonebuyerguide.model.PhoneModel

interface PhoneListInterface {
    fun toastError(t: Throwable)
    fun setPhones(phones: List<PhoneModel>)
}