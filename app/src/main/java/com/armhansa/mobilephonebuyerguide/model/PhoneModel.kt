package com.armhansa.mobilephonebuyerguide.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhoneModel(
    val id: Int,
    val name: String,
    val brand: String,
    val thumbImageURL: String,
    val description: String,
    val price: Float,
    val rating: Float,
    val isFavorite: Boolean
) : Parcelable