package com.armhansa.mobilephonebuyerguide

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.armhansa.mobilephonebuyerguide.constant.ConstantValue
import com.armhansa.mobilephonebuyerguide.constant.SortType
import com.armhansa.mobilephonebuyerguide.fragment.FavoriteListFragment
import com.armhansa.mobilephonebuyerguide.fragment.phonelist.PhoneListFragment
import com.armhansa.mobilephonebuyerguide.model.FavoriteListModel
import com.armhansa.mobilephonebuyerguide.model.TabModel
import com.armhansa.mobilephonebuyerguide.ui.main.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val phoneFragment by lazy { PhoneListFragment.newInstance() }
    private val favoriteFragment by lazy { FavoriteListFragment.newInstance(phoneFragment) }

    private val favoriteListModel by lazy { FavoriteListModel.getInstance() }
    private var checkedItem = 0

    companion object {
        var SORT_TYPE = SortType.LOW_PRICE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabModel = arrayListOf(
            TabModel("Mobile List", phoneFragment),
            TabModel("Favorite List", favoriteFragment)
        )

        val sectionsPagerAdapter = SectionsPagerAdapter(tabModel, supportFragmentManager)
        viewPager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(viewPager)
        btnSort.setOnClickListener {
            // setup the alert builder
            val builder = AlertDialog.Builder(this)

            // add a radio button list
            val sortType = arrayOf(SortType.LOW_PRICE.type, SortType.HIGH_PRICE.type, SortType.RATING.type)

            builder.setSingleChoiceItems(sortType, checkedItem) { dialog, which ->
                // user checked an item
                when (which) {
                    0 -> {
                        if (checkedItem != 0) {
                            checkedItem = 0
                            SORT_TYPE = SortType.LOW_PRICE
                            phoneFragment.sort(SORT_TYPE)
                            favoriteListModel.sort(SORT_TYPE)
                            favoriteListModel.callbackListener()
                        }
                    }
                    1 -> {
                        if (checkedItem != 1) {
                            checkedItem = 1
                            SORT_TYPE = SortType.HIGH_PRICE
                            phoneFragment.sort(SORT_TYPE)
                            favoriteListModel.sort(SORT_TYPE)
                            favoriteListModel.callbackListener()
                        }
                    }
                    2 -> {
                        if (checkedItem != 2) {
                            checkedItem = 2
                            SORT_TYPE = SortType.RATING
                            phoneFragment.sort(SORT_TYPE)
                            favoriteListModel.sort(SORT_TYPE)
                            favoriteListModel.callbackListener()
                        }
                    }
                }
                dialog.dismiss()
            }

            // create and show the alert dialog
            val dialog = builder.create()
            dialog.show()
        }
    }

}