package com.armhansa.mobilephonebuyerguide.fragment.phonelist

import android.content.SharedPreferences
import com.armhansa.mobilephonebuyerguide.entity.PhoneEntity
import com.armhansa.mobilephonebuyerguide.listener.OnFavoriteChangeListener
import com.armhansa.mobilephonebuyerguide.model.PhoneModel
import com.armhansa.mobilephonebuyerguide.service.PhoneManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhoneListPresenter(
    private val phoneListener: PhoneListInterface,
    private val favoriteListener: OnFavoriteChangeListener?,
    private val phoneApiManager: PhoneManager,
    private val pref: SharedPreferences?
) {
    companion object {
        fun getInstance(
            phoneListener: PhoneListInterface,
            favoriteListener: OnFavoriteChangeListener?,
            phoneApiManager: PhoneManager,
            pref: SharedPreferences?
        ) = PhoneListPresenter(phoneListener, favoriteListener, phoneApiManager, pref)
    }

    fun getPhoneModelFromApi() {
        phoneApiManager.createService().getMobiles().enqueue(object : Callback<List<PhoneEntity>> {
            override fun onFailure(call: Call<List<PhoneEntity>>, t: Throwable) {
                phoneListener.toastError(t)
            }

            override fun onResponse(call: Call<List<PhoneEntity>>, response: Response<List<PhoneEntity>>) {
                response.body()?.also { phonesEntity ->
                    if (phonesEntity.isNotEmpty()) {
                        val phonesModel = getPhoneModelFrom(phonesEntity)
                        phoneListener.setPhones(phonesModel)
                        val favoritesModel = getFavoriteModelFrom(phonesModel)
                        favoriteListener?.setFavorites(favoritesModel)
                    }
                }
            }
        })
    }

    fun getPhoneModelFrom(
        phonesEntity: List<PhoneEntity>
    ): List<PhoneModel> {
        val phonesModel: ArrayList<PhoneModel> = arrayListOf()
        for (i in 0 until phonesEntity.count()) {
            val isFavorite = pref?.getBoolean("FAV_${phonesEntity[i].id}", false) ?: false
            val phoneModel = PhoneModel(
                phonesEntity[i].id,
                phonesEntity[i].name,
                phonesEntity[i].brand,
                phonesEntity[i].thumbImageURL,
                phonesEntity[i].description,
                phonesEntity[i].price,
                phonesEntity[i].rating,
                isFavorite
            )
            phonesModel.add(phoneModel)
        }
        return phonesModel
    }

    fun getFavoriteModelFrom(phones: List<PhoneModel>): ArrayList<PhoneModel> {
        val favorites: ArrayList<PhoneModel> = arrayListOf()
        for (i in phones) {
            if (i.isFavorite) favorites.add(i)
        }
        return favorites
    }

}