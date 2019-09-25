package com.armhansa.mobilephonebuyerguide.fragment.phonelist

import android.content.SharedPreferences
import com.armhansa.mobilephonebuyerguide.entity.PhoneEntity
import com.armhansa.mobilephonebuyerguide.extension.SortingPhoneModelList
import com.armhansa.mobilephonebuyerguide.listener.OnFavoriteChangeListener
import com.armhansa.mobilephonebuyerguide.model.PhoneModel
import com.armhansa.mobilephonebuyerguide.service.PhoneApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhoneListPresenter(
    private val view: PhoneListInterface,
    private val favoriteListener: OnFavoriteChangeListener?,
    private val service: PhoneApiService,
    private val pref: SharedPreferences?
) {

    private var phones: ArrayList<PhoneModel> = arrayListOf()

    fun setPhones(phones: ArrayList<PhoneModel>) {
        this.phones = phones
    }

    fun sortedPhones(): ArrayList<PhoneModel> {
        phones = SortingPhoneModelList.sorted(phones)
        return phones
    }

    fun getPhoneModelFromApi() {
        service.getMobiles().enqueue(object : Callback<List<PhoneEntity>> {
            override fun onFailure(call: Call<List<PhoneEntity>>, t: Throwable) {
                view.toastError(t)
            }

            override fun onResponse(call: Call<List<PhoneEntity>>, response: Response<List<PhoneEntity>>) {
                response.body()?.also { phonesEntity ->
                    if (phonesEntity.isNotEmpty()) {
                        val phonesModel = getPhoneModelFrom(phonesEntity)
                        view.setPhones(phonesModel)
                        val favoritesModel = getFavoriteModelFrom(phonesModel)
                        favoriteListener?.setFavorites(favoritesModel)
                    }
                }
            }
        })
    }

    fun getPhoneModelFrom(phonesEntity: List<PhoneEntity>): List<PhoneModel> {
        return phonesEntity.map {
            val isFavorite = pref?.getBoolean("FAV_${it.id}", false) ?: false
            PhoneModel(
                it.id,
                it.name,
                it.brand,
                it.thumbImageURL,
                it.description,
                it.price,
                it.rating,
                isFavorite
            )
        }
    }

    fun getFavoriteModelFrom(phones: List<PhoneModel>): ArrayList<PhoneModel> {
        return ArrayList(phones.filter { it.isFavorite })
    }

}