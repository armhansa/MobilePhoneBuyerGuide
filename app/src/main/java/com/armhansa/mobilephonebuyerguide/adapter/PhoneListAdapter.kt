package com.armhansa.mobilephonebuyerguide.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.armhansa.mobilephonebuyerguide.R
import com.armhansa.mobilephonebuyerguide.entity.PhoneEntity
import com.bumptech.glide.Glide

class PhoneListAdapter(private val context: Context?, private val listener: OnClickItemPhoneListener) :
    RecyclerView.Adapter<PhoneViewHolder>() {

    private var phonesEntity: List<PhoneEntity> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        return PhoneViewHolder(parent, context, listener)
    }

    override fun getItemCount(): Int = phonesEntity.count()

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        holder.bind(phonesEntity[position])
    }

    fun setPhonesEntity(phonesEntity: List<PhoneEntity>) {
        this.phonesEntity = phonesEntity
        notifyDataSetChanged()
    }
}

class PhoneViewHolder(
    parent: ViewGroup,
    private val context: Context?,
    private val listener: OnClickItemPhoneListener
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_phone, parent, false)
) {
    private val imgPhone: ImageView = itemView.findViewById(R.id.imgPhone)
    private val txtName: TextView = itemView.findViewById(R.id.txtName)
    private val btnFav: ImageButton = itemView.findViewById(R.id.btnFav)
    private val txtDesc: TextView = itemView.findViewById(R.id.txtDesc)
    private val txtPrice: TextView = itemView.findViewById(R.id.txtPrice)
    private val txtRating: TextView = itemView.findViewById(R.id.txtRating)

    fun bind(phoneEntity: PhoneEntity) {
        txtName.text = phoneEntity.name
        txtDesc.text = phoneEntity.description
        txtPrice.text = context!!.getString(R.string.price_style, phoneEntity.price)
        txtRating.text = context.getString(R.string.rating_style, phoneEntity.rating)
        context.let { context ->
            Glide.with(context)
                .load(phoneEntity.thumbImageURL)
                .into(imgPhone)
        }
        itemView.setOnClickListener {
            listener.sendToDetailPage(phoneEntity)
        }

    }
}
