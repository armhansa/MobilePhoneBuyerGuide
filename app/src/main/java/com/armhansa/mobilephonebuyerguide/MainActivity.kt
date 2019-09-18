package com.armhansa.mobilephonebuyerguide

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.armhansa.mobilephonebuyerguide.constaint.ConstantValue
import com.armhansa.mobilephonebuyerguide.fragment.favoritelist.FavoriteListFragment
import com.armhansa.mobilephonebuyerguide.fragment.phonelist.PhoneListFragment
import com.armhansa.mobilephonebuyerguide.model.TabModel
import com.armhansa.mobilephonebuyerguide.ui.main.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences(ConstantValue.PREFS_KEY, Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabModel = arrayListOf(
            TabModel("Mobile List", PhoneListFragment.newInstance(sharedPreferences)),
            TabModel("Favorite List", FavoriteListFragment.newInstance(sharedPreferences)))

        val sectionsPagerAdapter = SectionsPagerAdapter(tabModel, supportFragmentManager)
        viewPager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(viewPager)
    }

}