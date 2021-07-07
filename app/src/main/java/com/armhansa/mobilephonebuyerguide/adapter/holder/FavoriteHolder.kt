package com.armhansa.mobilephonebuyerguide.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.armhansa.mobilephonebuyerguide.R
import com.armhansa.mobilephonebuyerguide.listener.OnClickItemPhoneListener
import com.armhansa.mobilephonebuyerguide.model.PhoneModel
import com.bumptech.glide.Glide

class FavoriteHolder(parent: ViewGroup, private val listener: OnClickItemPhoneListener) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)) {

    private val imgPhone: ImageView = itemView.findViewById(R.id.imgPhone)
    private val txtName: TextView = itemView.findViewById(R.id.txtName)
    private val txtPrice: TextView = itemView.findViewById(R.id.txtPrice)
    private val txtRating: TextView = itemView.findViewById(R.id.txtRating)

    fun bind(phoneModel: PhoneModel) {
        txtName.text = phoneModel.name
        txtPrice.text = itemView.context.getString(R.string.short_price_style, phoneModel.price)
        txtRating.text = itemView.context.getString(R.string.rating_style, phoneModel.rating)
        Glide.with(itemView.context)
            .load(phoneModel.thumbImageURL)
            .fitCenter()
            .into(imgPhone)
        itemView.setOnClickListener {
            listener.sendToDetailPage(phoneModel)
        }
    }
}