package com.armhansa.mobilephonebuyerguide.adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.armhansa.mobilephonebuyerguide.R
import com.armhansa.mobilephonebuyerguide.listener.OnClickItemPhoneListener
import com.armhansa.mobilephonebuyerguide.model.PhoneModel
import com.bumptech.glide.Glide

class FavoriteListAdapter(
    private val context: Context?,
    private val listener: OnClickItemPhoneListener,
    private val pref: SharedPreferences? = null
) : RecyclerView.Adapter<FavoriteHolder>() {
    private var phonesModel: ArrayList<PhoneModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder {
        return FavoriteHolder(parent, context, listener)
    }

    override fun getItemCount(): Int = phonesModel.count()

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.bind(phonesModel[position])
    }

    fun setFavoriteModel(phonesModel: ArrayList<PhoneModel>) {
        this.phonesModel = phonesModel
        notifyDataSetChanged()
    }

    fun removeAt(index: Int): PhoneModel {
        val editor = pref?.edit()
        editor?.putBoolean("FAV_${phonesModel[index].id}", false)
        editor?.apply()
        val removePhone = phonesModel[index]
        phonesModel.removeAt(index)
        notifyDataSetChanged()
        return removePhone
    }

}

class FavoriteHolder(
    parent: ViewGroup,
    private val context: Context?,
    private val listener: OnClickItemPhoneListener
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
) {
    private val imgPhone: ImageView = itemView.findViewById(R.id.imgPhone)
    private val txtName: TextView = itemView.findViewById(R.id.txtName)
    private val txtPrice: TextView = itemView.findViewById(R.id.txtPrice)
    private val txtRating: TextView = itemView.findViewById(R.id.txtRating)

    fun bind(phoneModel: PhoneModel) {
        txtName.text = phoneModel.name
        txtPrice.text = context!!.getString(R.string.short_price_style, phoneModel.price)
        txtRating.text = context.getString(R.string.rating_style, phoneModel.rating)
        context.let { context ->
            Glide.with(context)
                .load(phoneModel.thumbImageURL)
                .into(imgPhone)
        }
        itemView.setOnClickListener {
            listener.sendToDetailPage(phoneModel)
        }

    }
}
