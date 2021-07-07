package com.armhansa.mobilephonebuyerguide.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.armhansa.mobilephonebuyerguide.model.TabModel

class SectionsPagerAdapter(private val tabsModel: List<TabModel>, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = tabsModel[position].fragment

    override fun getPageTitle(position: Int): CharSequence? = tabsModel[position].titleName

    override fun getCount(): Int = tabsModel.count()
    
}