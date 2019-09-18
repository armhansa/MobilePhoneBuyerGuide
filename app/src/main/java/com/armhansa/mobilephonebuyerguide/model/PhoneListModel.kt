package com.armhansa.mobilephonebuyerguide.model

import com.armhansa.mobilephonebuyerguide.listener.OnPhoneModelsChangeListener

class PhoneListModel private constructor(private var phones: List<PhoneModel>) {

    private var listener: OnPhoneModelsChangeListener? = null

    companion object {
        private var phoneInstance: PhoneListModel? = null

        fun getInstance(): PhoneListModel {
            if (phoneInstance == null) {
                phoneInstance = PhoneListModel(arrayListOf())
            }
            return phoneInstance!!
        }
    }

    fun getPhones(): List<PhoneModel> = phones

    fun setListener(listener: OnPhoneModelsChangeListener) {
        this.listener = listener
    }

    private fun callbackListener() {
        listener?.refreshPhoneModels()
    }

    fun set(newPhones: List<PhoneModel>) {
        phones = newPhones
        callbackListener()
    }


}