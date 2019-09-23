package com.armhansa.mobilephonebuyerguide.activity.phonedetail

import com.armhansa.mobilephonebuyerguide.entity.PhoneImageEntity
import com.armhansa.mobilephonebuyerguide.service.PhoneApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhoneDetailPresenter(
    private val view: PhoneDetailInterface,
    private val service: PhoneApiService
) : Callback<List<PhoneImageEntity>> {

    companion object {
        fun getInstance(listener: PhoneDetailInterface, service: PhoneApiService) =
            PhoneDetailPresenter(listener, service)
    }

    fun getImageFromApi(phoneId: Int) {
        service.getMobileImgById(phoneId).enqueue(this)
    }

    override fun onFailure(call: Call<List<PhoneImageEntity>>, t: Throwable) {
        view.toastError(t)
    }

    override fun onResponse(call: Call<List<PhoneImageEntity>>, response: Response<List<PhoneImageEntity>>) {
        response.body()?.apply {
            if (isNotEmpty()) {
                view.setPhoneImageList(this)
            }
        }
    }

}