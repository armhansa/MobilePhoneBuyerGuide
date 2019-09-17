package com.armhansa.mobilephonebuyerguide.fragment.phonelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.armhansa.mobilephonebuyerguide.PhoneDetailActivity
import com.armhansa.mobilephonebuyerguide.R
import com.armhansa.mobilephonebuyerguide.adapter.OnClickItemPhoneListener
import com.armhansa.mobilephonebuyerguide.adapter.PhoneListAdapter
import com.armhansa.mobilephonebuyerguide.entity.PhoneEntity
import com.armhansa.mobilephonebuyerguide.service.PhoneManager
import kotlinx.android.synthetic.main.fragment_phone_list.*

class PhoneListFragment : Fragment(), PhoneListInterface, OnClickItemPhoneListener {
    private val presenter by lazy { PhoneListPresenter.getInstance(this, PhoneManager()) }
    private lateinit var phoneListAdapter: PhoneListAdapter

    companion object {
        fun newInstance() = PhoneListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter.getPhoneApi()
        return inflater.inflate(R.layout.fragment_phone_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
    }

    private fun setView() {
        phoneListAdapter = PhoneListAdapter(context, this)
        rvPhone.adapter = phoneListAdapter
        rvPhone.layoutManager = LinearLayoutManager(context)
        rvPhone.itemAnimator = DefaultItemAnimator()
    }

    override fun toastError(t: Throwable) {
        Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
    }

    override fun setPhoneList(phones: List<PhoneEntity>) {
        phoneListAdapter.setPhonesEntity(phones)
    }

    override fun sendToDetailPage(phoneEntity: PhoneEntity) {
        PhoneDetailActivity.startActivity(context, phoneEntity)
    }

}
