package com.armhansa.mobilephonebuyerguide.adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.armhansa.mobilephonebuyerguide.MainActivity
import com.armhansa.mobilephonebuyerguide.R
import com.armhansa.mobilephonebuyerguide.listener.OnClickItemPhoneListener
import com.armhansa.mobilephonebuyerguide.model.FavoriteListModel
import com.armhansa.mobilephonebuyerguide.model.PhoneModel
import com.bumptech.glide.Glide

class PhoneListAdapter(
    private val context: Context?,
    private val listener: OnClickItemPhoneListener,
    private val pref: SharedPreferences
) : RecyclerView.Adapter<PhoneViewHolder>() {
    private var phonesModel: ArrayList<PhoneModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        return PhoneViewHolder(parent, context, listener, pref)
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
    }

    fun getPhones() = ArrayList(phonesModel)
}

class PhoneViewHolder(
    parent: ViewGroup,
    private val context: Context?,
    private val listener: OnClickItemPhoneListener,
    private val pref: SharedPreferences
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_phone, parent, false)
) {
    private val imgPhone: ImageView = itemView.findViewById(R.id.imgPhone)
    private val txtName: TextView = itemView.findViewById(R.id.txtName)
    private val btnFav: ImageButton = itemView.findViewById(R.id.btnFav)
    private val txtDesc: TextView = itemView.findViewById(R.id.txtDesc)
    private val txtPrice: TextView = itemView.findViewById(R.id.txtPrice)
    private val txtRating: TextView = itemView.findViewById(R.id.txtRating)

    fun bind(phoneModel: PhoneModel) {
        txtName.text = phoneModel.name
        txtDesc.text = phoneModel.description
        txtPrice.text = context!!.getString(R.string.price_style, phoneModel.price)
        txtRating.text = context.getString(R.string.rating_style, phoneModel.rating)
        context.let { context ->
            Glide.with(context)
                .load(phoneModel.thumbImageURL)
                .into(imgPhone)
        }
        btnFav.setImageResource(
            if (phoneModel.isFavorite) R.drawable.fav
            else R.drawable.unfav
        )
        btnFav.setOnClickListener {
            if (phoneModel.isFavorite) {
                FavoriteListModel.getInstance().remove(phoneModel)
            } else {
                FavoriteListModel.getInstance().add(phoneModel)
            }
            FavoriteListModel.getInstance().sort(MainActivity.SORT_TYPE)
            FavoriteListModel.getInstance().callbackListener()

            phoneModel.isFavorite = !phoneModel.isFavorite
            val prefEditor = pref.edit()
            prefEditor.putBoolean("FAV_${phoneModel.id}", phoneModel.isFavorite)
            prefEditor.apply()
            bind(phoneModel)
        }
        itemView.setOnClickListener {
            listener.sendToDetailPage(phoneModel)
        }

    }
}
