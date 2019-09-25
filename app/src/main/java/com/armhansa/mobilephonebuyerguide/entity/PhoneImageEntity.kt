package com.armhansa.mobilephonebuyerguide.entity

import com.google.gson.annotations.SerializedName

data class PhoneImageEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("url")
    var url: String,
    @SerializedName("mobile_id")
    val mobileId: Int
)