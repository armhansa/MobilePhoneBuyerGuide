package com.armhansa.mobilephonebuyerguide

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.armhansa.mobilephonebuyerguide.fragment.favoritelist.FavoriteListFragment
import com.armhansa.mobilephonebuyerguide.fragment.phonelist.PhoneListFragment
import com.armhansa.mobilephonebuyerguide.model.TabModel
import com.armhansa.mobilephonebuyerguide.ui.main.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabModel = arrayListOf(
            TabModel("Mobile List", PhoneListFragment.newInstance()),
            TabModel("Favorite List", FavoriteListFragment.newInstance()))

        val sectionsPagerAdapter = SectionsPagerAdapter(tabModel, supportFragmentManager)
        viewPager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(viewPager)
    }

}