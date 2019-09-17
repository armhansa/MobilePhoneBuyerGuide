package com.armhansa.mobilephonebuyerguide.service

import com.armhansa.mobilephonebuyerguide.entity.PhoneEntity
import com.armhansa.mobilephonebuyerguide.entity.PhoneImageEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PhoneApiService {
    @GET("mobiles/")
    fun getMobiles(): Call<List<PhoneEntity>>

    @GET("mobiles/{id}/images/")
    fun getMobileImgById(@Path("id") phoneId: Int): Call<List<PhoneImageEntity>>
}
