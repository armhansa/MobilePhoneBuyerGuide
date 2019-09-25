package com.armhansa.mobilephonebuyerguide.activity.phonedetail

import com.armhansa.mobilephonebuyerguide.entity.PhoneImageEntity
import com.armhansa.mobilephonebuyerguide.service.PhoneApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhoneDetailPresenter(private val view: PhoneDetailInterface, private val service: PhoneApiService) {
    
    fun getImageFromApi(phoneId: Int) {
        service.getMobileImgById(phoneId).enqueue(object : Callback<List<PhoneImageEntity>> {

            override fun onFailure(call: Call<List<PhoneImageEntity>>, t: Throwable) {
                view.toastError(t)
            }

            override fun onResponse(call: Call<List<PhoneImageEntity>>, response: Response<List<PhoneImageEntity>>) {
                response.body()?.apply {
                    if (isNotEmpty()) {
                        fixUrlImageLossHttp(this)
                        view.setPhoneImageList(this)
                    }
                }
            }
        })
    }

    fun fixUrlImageLossHttp(phoneImages: List<PhoneImageEntity>) {
        phoneImages.forEach {
            if (it.url.startsWith("www")) {
                it.url = "https://${it.url}"
            }
        }
    }

}