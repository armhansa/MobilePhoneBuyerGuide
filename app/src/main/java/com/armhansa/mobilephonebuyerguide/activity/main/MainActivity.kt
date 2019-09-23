package com.armhansa.mobilephonebuyerguide.activity.main

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.armhansa.mobilephonebuyerguide.R
import com.armhansa.mobilephonebuyerguide.constant.SortType
import com.armhansa.mobilephonebuyerguide.fragment.favoritelist.FavoriteListFragment
import com.armhansa.mobilephonebuyerguide.fragment.phonelist.PhoneListFragment
import com.armhansa.mobilephonebuyerguide.model.TabModel
import com.armhansa.mobilephonebuyerguide.adapter.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        var SORT_TYPE = SortType.LOW_PRICE
    }

    private val phoneFragment by lazy { PhoneListFragment.newInstance() }
    private val favoriteFragment by lazy { FavoriteListFragment.newInstance() }

    private var checkedItem = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        phoneFragment.setFavoriteListener(favoriteFragment)
        favoriteFragment.setPhoneListener(phoneFragment)

        setView()

    }

    private fun setView() {
        val tabModel = arrayListOf(
            TabModel("Mobile List", phoneFragment),
            TabModel("Favorite List", favoriteFragment)
        )

        val sectionsPagerAdapter =
            SectionsPagerAdapter(tabModel, supportFragmentManager)
        viewPager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(viewPager)
        btnSort.setOnClickListener {
            // setup the alert builder
            val builder = AlertDialog.Builder(this)

            // add a radio button list
            val sortType = arrayOf(SortType.LOW_PRICE.type, SortType.HIGH_PRICE.type, SortType.RATING.type)

            builder.setSingleChoiceItems(sortType, checkedItem) { dialog, which ->
                // user checked an item
                if (which != checkedItem) {
                    checkedItem = which
                    when (which) {
                        0 -> SORT_TYPE = SortType.LOW_PRICE
                        1 -> SORT_TYPE = SortType.HIGH_PRICE
                        2 -> SORT_TYPE = SortType.RATING
                    }
                    phoneFragment.sortPhones()
                    favoriteFragment.sortFavorites()
                }
                dialog.dismiss()
            }

            // create and show the alert dialog
            val dialog = builder.create()
            dialog.show()
        }
    }

}