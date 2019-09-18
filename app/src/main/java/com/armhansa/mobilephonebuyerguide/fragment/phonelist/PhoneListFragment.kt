package com.armhansa.mobilephonebuyerguide.fragment.phonelist

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.armhansa.mobilephonebuyerguide.MainActivity
import com.armhansa.mobilephonebuyerguide.PhoneDetailActivity
import com.armhansa.mobilephonebuyerguide.R
import com.armhansa.mobilephonebuyerguide.adapter.PhoneListAdapter
import com.armhansa.mobilephonebuyerguide.constant.SortType
import com.armhansa.mobilephonebuyerguide.entity.PhoneEntity
import com.armhansa.mobilephonebuyerguide.listener.OnClickItemPhoneListener
import com.armhansa.mobilephonebuyerguide.listener.OnFavoriteRemoveListener
import com.armhansa.mobilephonebuyerguide.model.FavoriteListModel
import com.armhansa.mobilephonebuyerguide.model.PhoneModel
import com.armhansa.mobilephonebuyerguide.service.PhoneManager
import kotlinx.android.synthetic.main.fragment_phone_list.*

class PhoneListFragment(private val pref: SharedPreferences) : Fragment()
    , PhoneListInterface, OnClickItemPhoneListener, OnFavoriteRemoveListener {

    private val presenter by lazy {
        PhoneListPresenter.getInstance(this, PhoneManager(), pref)
    }
    private val favoriteListModel: FavoriteListModel = FavoriteListModel.getInstance()
    private lateinit var phoneListAdapter: PhoneListAdapter

    companion object {
        fun newInstance(pref: SharedPreferences) = PhoneListFragment(pref)
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
        phoneListAdapter = PhoneListAdapter(context, this, pref)
        rvPhone.adapter = phoneListAdapter
        rvPhone.layoutManager = LinearLayoutManager(context)
        rvPhone.itemAnimator = DefaultItemAnimator()
    }

    override fun toastError(t: Throwable) {
        Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
    }

    override fun setPhoneList(phones: List<PhoneEntity>) {
        val phonesModel = presenter.getPhoneModelFrom(phones, favoriteListModel)
        val sortedPhones = presenter.sort(ArrayList(phonesModel), MainActivity.SORT_TYPE)
        phoneListAdapter.setPhonesModel(sortedPhones)
    }

    override fun sendToDetailPage(phoneModel: PhoneModel) {
        PhoneDetailActivity.startActivity(context, phoneModel)
    }

    fun sort(sortType: SortType) {
        val sortedPhones = presenter.sort(phoneListAdapter.getPhones(), sortType)
        phoneListAdapter.setPhonesModel(sortedPhones)
    }

    override fun changeStarState(phoneModel: PhoneModel) {
        phoneListAdapter.changeStarAt(phoneModel)
        sort(MainActivity.SORT_TYPE)
    }

}
