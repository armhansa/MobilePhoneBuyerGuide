package com.armhansa.mobilephonebuyerguide.service

import com.armhansa.mobilephonebuyerguide.entity.PhoneEntity
import com.armhansa.mobilephonebuyerguide.entity.PhoneImageEntity
import retrofit2.Call
import retrofit2.http.GET

interface PhoneApiService {
    @GET("mobiles/")
    fun getMobiles(): Call<List<PhoneEntity>>

    @GET("mobiles/1/images/")
    fun getMobileImgById(): Call<PhoneImageEntity>
}
