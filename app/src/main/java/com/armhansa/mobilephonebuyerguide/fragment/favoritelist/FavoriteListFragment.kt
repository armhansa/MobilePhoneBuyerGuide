package com.armhansa.mobilephonebuyerguide.fragment.favoritelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.armhansa.mobilephonebuyerguide.R

class FavoriteListFragment : Fragment() {

    companion object {
        fun newInstance() = FavoriteListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_list, container, false)
    }

}
