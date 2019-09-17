package com.armhansa.mobilephonebuyerguide.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.armhansa.mobilephonebuyerguide.R
import com.armhansa.mobilephonebuyerguide.entity.PhoneImageEntity
import com.bumptech.glide.Glide

class PhoneImagePagerAdapter(
    private val context: Context,
    private val imagesEntity: List<PhoneImageEntity>
) : PagerAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int = imagesEntity.count()

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(R.layout.slide_phone_image, view, false)!!
        val imgFullPhone: ImageView = imageLayout.findViewById(R.id.imgFullPhone)
        if (imagesEntity[position].url.startsWith("www"))
            imagesEntity[position].url = "https://${imagesEntity[position].url}"
        Glide.with(context)
            .load(imagesEntity[position].url)
            .into(imgFullPhone)
        view.addView(imageLayout, 0)
        return imageLayout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}
