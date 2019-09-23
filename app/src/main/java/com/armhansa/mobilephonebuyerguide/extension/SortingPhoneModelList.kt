package com.armhansa.mobilephonebuyerguide.extension

import com.armhansa.mobilephonebuyerguide.activity.main.MainActivity
import com.armhansa.mobilephonebuyerguide.constant.SortType
import com.armhansa.mobilephonebuyerguide.model.PhoneModel

class SortingPhoneModelList {
    companion object {
        fun sorted(phones: ArrayList<PhoneModel>): ArrayList<PhoneModel> {
            val newPhones: ArrayList<PhoneModel> = arrayListOf()
            val sortType = MainActivity.SORT_TYPE
            for (i in 0 until phones.count()) {
                var startValue: Float = if (sortType != SortType.RATING) phones[0].price else phones[0].rating
                var targetIndex = 0
                for (j in 0 until phones.count()) {
                    if ((sortType == SortType.LOW_PRICE && phones[j].price < startValue)
                        || (sortType == SortType.HIGH_PRICE && phones[j].price > startValue)
                    ) {
                        targetIndex = j
                        startValue = phones[j].price
                    } else if (sortType == SortType.RATING && phones[j].rating > startValue) {
                        targetIndex = j
                        startValue = phones[j].rating
                    }
                }
                newPhones.add(phones[targetIndex])
                phones.removeAt(targetIndex)
            }
            return newPhones
        }
    }
}