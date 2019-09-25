package com.armhansa.mobilephonebuyerguide.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.armhansa.mobilephonebuyerguide.adapter.holder.FavoriteHolder
import com.armhansa.mobilephonebuyerguide.listener.OnClickItemPhoneListener
import com.armhansa.mobilephonebuyerguide.model.PhoneModel

class FavoriteListAdapter(private val listener: OnClickItemPhoneListener) : RecyclerView.Adapter<FavoriteHolder>() {

    private var phonesModel: ArrayList<PhoneModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder {
        return FavoriteHolder(parent, listener)
    }

    override fun getItemCount(): Int = phonesModel.count()

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.bind(phonesModel[position])
    }

    fun setFavoritesModel(phonesModel: ArrayList<PhoneModel>) {
        this.phonesModel = phonesModel
        notifyDataSetChanged()
    }

}


