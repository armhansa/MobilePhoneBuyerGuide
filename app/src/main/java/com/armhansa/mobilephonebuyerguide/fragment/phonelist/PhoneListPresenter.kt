package com.armhansa.mobilephonebuyerguide.fragment.phonelist

import com.armhansa.mobilephonebuyerguide.entity.PhoneEntity
import com.armhansa.mobilephonebuyerguide.service.PhoneManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhoneListPresenter(
    private val listener: PhoneListInterface,
    private val phoneApiManager: PhoneManager
) {

    companion object {
        fun getInstance(listener: PhoneListInterface, phoneApiManager: PhoneManager) =
            PhoneListPresenter(listener, phoneApiManager)
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

}