package com.armhansa.mobilephonebuyerguide

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.armhansa.mobilephonebuyerguide.adapter.PhoneImagePagerAdapter
import com.armhansa.mobilephonebuyerguide.entity.PhoneEntity
import com.armhansa.mobilephonebuyerguide.entity.PhoneImageEntity
import com.armhansa.mobilephonebuyerguide.service.PhoneManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_phone_detail.*
import kotlinx.android.synthetic.main.item_phone.txtName

class PhoneDetailActivity : AppCompatActivity(), PhoneDetailInterface {
    private val presenter: PhoneDetailPresenter by lazy { PhoneDetailPresenter(this, PhoneManager()) }
    private lateinit var phoneImagePagerAdapter: PhoneImagePagerAdapter

    companion object {
        private const val PHONE_MODEL_KEY = "phoneModelKey"
        fun startActivity(context: Context?, phoneEntity: PhoneEntity) {
            context?.startActivity(Intent(context,PhoneDetailActivity::class.java).also {
                it.putExtra(PHONE_MODEL_KEY, phoneEntity)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_detail)

        setView()
    }

    private fun setView() {
        val phoneModel: PhoneEntity = intent.getParcelableExtra(PHONE_MODEL_KEY)
        presenter.getImageFromApi(phoneModel.id)
        txtName.text = phoneModel.name
        txtBrand.text = phoneModel.brand
        txtDesc.text = phoneModel.description
    }

    override fun toastError(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_LONG).show()
    }

    override fun setPhoneImageList(images: List<PhoneImageEntity>) {
        phoneImagePagerAdapter = PhoneImagePagerAdapter(this, images)
        pagerPhoneImg.adapter = phoneImagePagerAdapter
    }

}
