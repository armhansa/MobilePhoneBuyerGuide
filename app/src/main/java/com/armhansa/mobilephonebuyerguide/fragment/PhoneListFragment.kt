package com.armhansa.mobilephonebuyerguide.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.armhansa.mobilephonebuyerguide.R

class PhoneListFragment : Fragment() {

    companion object {
        fun newInstance() = PhoneListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phone_list, container, false)
    }

}
