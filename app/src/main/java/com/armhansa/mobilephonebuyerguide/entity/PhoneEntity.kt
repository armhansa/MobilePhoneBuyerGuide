package com.armhansa.mobilephonebuyerguide.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhoneEntity(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("brand")
    val brand: String,

    @SerializedName("thumbImageURL")
    val thumbImageURL: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("price")
    val price: Float,

    @SerializedName("rating")
    val rating: Float
) : Parcelable