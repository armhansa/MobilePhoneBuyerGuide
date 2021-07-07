package com.armhansa.mobilephonebuyerguide.adapter

import android.content.SharedPreferences
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.armhansa.mobilephonebuyerguide.adapter.holder.PhoneViewHolder
import com.armhansa.mobilephonebuyerguide.listener.OnClickItemPhoneListener
import com.armhansa.mobilephonebuyerguide.listener.OnFavoriteChangeListener
import com.armhansa.mobilephonebuyerguide.model.PhoneModel

class PhoneListAdapter(
    private val phoneListener: OnClickItemPhoneListener,
    private val favoriteListener: OnFavoriteChangeListener?,
    private val pref: SharedPreferences?
) : RecyclerView.Adapter<PhoneViewHolder>() {

    private var phonesModel: ArrayList<PhoneModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        return PhoneViewHolder(parent, phoneListener, favoriteListener, pref)
    }

    override fun getItemCount(): Int = phonesModel.count()

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        holder.bind(phonesModel[position])
    }

    fun setPhonesModel(phonesModel: List<PhoneModel>) {
        this.phonesModel = ArrayList(phonesModel)
        notifyDataSetChanged()
    }

    fun changeStarAt(phoneModel: PhoneModel) {
        phonesModel.remove(phoneModel)
        phoneModel.isFavorite = false
        phonesModel.add(phoneModel)
        notifyDataSetChanged()
    }
}
