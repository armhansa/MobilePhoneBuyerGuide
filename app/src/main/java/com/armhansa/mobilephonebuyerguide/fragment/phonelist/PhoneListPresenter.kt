package com.armhansa.mobilephonebuyerguide.fragment.phonelist

import android.content.SharedPreferences
import com.armhansa.mobilephonebuyerguide.MainActivity
import com.armhansa.mobilephonebuyerguide.constaint.SortType
import com.armhansa.mobilephonebuyerguide.entity.PhoneEntity
import com.armhansa.mobilephonebuyerguide.model.FavoriteListModel
import com.armhansa.mobilephonebuyerguide.model.PhoneModel
import com.armhansa.mobilephonebuyerguide.service.PhoneManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhoneListPresenter(
    private val listener: PhoneListInterface,
    private val phoneApiManager: PhoneManager,
    private val pref: SharedPreferences
) {
    companion object {
        fun getInstance(
            listener: PhoneListInterface,
            phoneApiManager: PhoneManager,
            pref: SharedPreferences
        ) = PhoneListPresenter(listener, phoneApiManager, pref)
    }

    fun getPhoneApi() {
        phoneApiManager.createService().getMobiles().enqueue(object : Callback<List<PhoneEntity>> {
            override fun onFailure(call: Call<List<PhoneEntity>>, t: Throwable) {
                listener.toastError(t)
            }

            override fun onResponse(call: Call<List<PhoneEntity>>, response: Response<List<PhoneEntity>>) {
                response.body()?.apply {
                    if (this.isNotEmpty()) {
                        listener.setPhoneList(this)
                    }
                }
            }
        })
    }

    fun getPhoneModelFrom(
        phonesEntity: List<PhoneEntity>,
        favoriteListModel: FavoriteListModel
    ): List<PhoneModel> {
        val phonesModel: ArrayList<PhoneModel> = arrayListOf()
        favoriteListModel.reset()
        for (i in 0 until phonesEntity.count()) {
            val isFavorite = pref.getBoolean("FAV_${phonesEntity[i].id}", false)
            val phoneModel = PhoneModel(
                phonesEntity[i].id,
                phonesEntity[i].name,
                phonesEntity[i].brand,
                phonesEntity[i].thumbImageURL,
                phonesEntity[i].description,
                phonesEntity[i].price,
                phonesEntity[i].rating,
                isFavorite
            )
            phonesModel.add(phoneModel)
            if (isFavorite)
                favoriteListModel.add(phoneModel)
        }
        favoriteListModel.sort(MainActivity.SORT_TYPE)
        favoriteListModel.callbackListener()
        return phonesModel
    }

    fun sort(phones: ArrayList<PhoneModel>, sortType: SortType): ArrayList<PhoneModel> {
        val newPhones: ArrayList<PhoneModel> = arrayListOf()
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