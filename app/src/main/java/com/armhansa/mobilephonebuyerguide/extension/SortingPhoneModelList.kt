package com.armhansa.mobilephonebuyerguide.extension

import com.armhansa.mobilephonebuyerguide.activity.main.MainActivity
import com.armhansa.mobilephonebuyerguide.constant.SortType
import com.armhansa.mobilephonebuyerguide.model.PhoneModel

class SortingPhoneModelList {

    companion object {
        fun sorted(phones: ArrayList<PhoneModel>): ArrayList<PhoneModel> {
            return when (MainActivity.SORT_TYPE) {
                SortType.LOW_PRICE -> ArrayList(phones.sortedBy { it.price })
                SortType.HIGH_PRICE -> ArrayList(phones.sortedByDescending { it.price })
                SortType.RATING -> ArrayList(phones.sortedByDescending { it.rating })
            }
        }
    }
    
}