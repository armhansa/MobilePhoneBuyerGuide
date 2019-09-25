package com.armhansa.mobilephonebuyerguide.adapter.holder

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.armhansa.mobilephonebuyerguide.R
import com.armhansa.mobilephonebuyerguide.listener.OnClickItemPhoneListener
import com.armhansa.mobilephonebuyerguide.listener.OnFavoriteChangeListener
import com.armhansa.mobilephonebuyerguide.model.PhoneModel
import com.bumptech.glide.Glide

class PhoneViewHolder(
    parent: ViewGroup,
    private val phoneListener: OnClickItemPhoneListener,
    private val favoriteListener: OnFavoriteChangeListener?,
    private val pref: SharedPreferences?
) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_phone, parent, false)) {

    private val imgPhone: ImageView = itemView.findViewById(R.id.imgPhone)
    private val txtName: TextView = itemView.findViewById(R.id.txtName)
    private val btnFav: ImageButton = itemView.findViewById(R.id.btnFav)
    private val txtDesc: TextView = itemView.findViewById(R.id.txtDesc)
    private val txtPrice: TextView = itemView.findViewById(R.id.txtPrice)
    private val txtRating: TextView = itemView.findViewById(R.id.txtRating)

    fun bind(phoneModel: PhoneModel) {
        txtName.text = phoneModel.name
        txtDesc.text = phoneModel.description
        txtPrice.text = itemView.context.getString(R.string.price_style, phoneModel.price)
        txtRating.text = itemView.context.getString(R.string.rating_style, phoneModel.rating)
        Glide.with(itemView.context)
            .load(phoneModel.thumbImageURL)
            .fitCenter()
            .into(imgPhone)
        btnFav.setImageResource(
            if (phoneModel.isFavorite) {
                R.drawable.ic_favorite_black_24dp
            } else {
                R.drawable.ic_favorite_border_black_24dp
            }
        )
        btnFav.setOnClickListener {
            if (phoneModel.isFavorite) {
                favoriteListener?.remove(phoneModel)
            } else {
                favoriteListener?.add(phoneModel)
            }
            favoriteListener?.sortFavorites()

            phoneModel.isFavorite = phoneModel.isFavorite.not()
            pref?.edit()?.run {
                putBoolean("FAV_${phoneModel.id}", phoneModel.isFavorite)
                apply()
            }
            bind(phoneModel)
        }
        itemView.setOnClickListener {
            phoneListener.sendToDetailPage(phoneModel)
        }
    }
}