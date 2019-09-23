package com.armhansa.mobilephonebuyerguide.activity.phonedetail

import com.armhansa.mobilephonebuyerguide.entity.PhoneImageEntity
import com.armhansa.mobilephonebuyerguide.service.PhoneManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhoneDetailPresenter(
    private val listener: PhoneDetailInterface,
    private val phoneApiManager: PhoneManager
) {
    companion object {
        fun getInstance(listener: PhoneDetailInterface, phoneApiManager: PhoneManager) =
            PhoneDetailPresenter(listener, phoneApiManager)
    }

    fun getImageFromApi(phoneId: Int) {
        phoneApiManager.createService().getMobileImgById(phoneId).enqueue(object : Callback<List<PhoneImageEntity>> {
            override fun onFailure(call: Call<List<PhoneImageEntity>>, t: Throwable) {
                listener.toastError(t)
            }

            override fun onResponse(call: Call<List<PhoneImageEntity>>, response: Response<List<PhoneImageEntity>>) {
                response.body()?.apply {
                    if (isNotEmpty()) {
                        listener.setPhoneImageList(this)
                    }
                }
            }

        })
    }

}