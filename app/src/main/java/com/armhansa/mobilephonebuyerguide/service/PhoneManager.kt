package com.armhansa.mobilephonebuyerguide.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PhoneManager {
    companion object {
        const val BASE_PHONE_API = "https://scb-test-mobile.herokuapp.com/api/"
    }

    fun createService(): PhoneApiService =
        Retrofit.Builder()
            .baseUrl(BASE_PHONE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .run {
                create(PhoneApiService::class.java)
            }
}