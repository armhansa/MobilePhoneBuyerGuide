package com.armhansa.mobilephonebuyerguide.fragment.phonelist

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.armhansa.mobilephonebuyerguide.activity.phonedetail.PhoneDetailActivity
import com.armhansa.mobilephonebuyerguide.R
import com.armhansa.mobilephonebuyerguide.adapter.PhoneListAdapter
import com.armhansa.mobilephonebuyerguide.constant.ConstantValue
import com.armhansa.mobilephonebuyerguide.extension.SortingPhoneModelList
import com.armhansa.mobilephonebuyerguide.listener.OnClickItemPhoneListener
import com.armhansa.mobilephonebuyerguide.listener.OnFavoriteChangeListener
import com.armhansa.mobilephonebuyerguide.listener.OnFavoriteRemoveListener
import com.armhansa.mobilephonebuyerguide.model.PhoneModel
import com.armhansa.mobilephonebuyerguide.service.PhoneManager
import kotlinx.android.synthetic.main.fragment_phone_list.*

class PhoneListFragment : Fragment()
    , PhoneListInterface, OnClickItemPhoneListener, OnFavoriteRemoveListener {
    companion object {
        fun newInstance() = PhoneListFragment()
    }

    private var phones: ArrayList<PhoneModel> = arrayListOf()
    private val presenter by lazy { PhoneListPresenter.getInstance(this, favoriteListener, PhoneManager(), pref) }
    private var favoriteListener: OnFavoriteChangeListener? = null
    private lateinit var phoneListAdapter: PhoneListAdapter
    private val pref: SharedPreferences? by lazy {
        context?.getSharedPreferences(
            ConstantValue.PREFS_KEY,
            Context.MODE_PRIVATE
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter.getPhoneModelFromApi()
        return inflater.inflate(R.layout.fragment_phone_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
    }

    private fun setView() {
        phoneListAdapter = PhoneListAdapter(context, this, favoriteListener, pref)
        rvPhone.adapter = phoneListAdapter
        rvPhone.layoutManager = LinearLayoutManager(context)
        rvPhone.itemAnimator = DefaultItemAnimator()
    }

    fun setFavoriteListener(listener: OnFavoriteChangeListener) {
        favoriteListener = listener
    }

    override fun toastError(t: Throwable) {
        Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
    }

    override fun setPhones(phones: List<PhoneModel>) {
        this.phones = SortingPhoneModelList.sorted(ArrayList(phones))
        phoneListAdapter.setPhonesModel(this.phones)
        pbLoading.isVisible = false
    }

    override fun sendToDetailPage(phoneModel: PhoneModel) {
        PhoneDetailActivity.startActivity(context, phoneModel)
    }

    fun sortPhones() {
        phones = SortingPhoneModelList.sorted(phones)
        phoneListAdapter.setPhonesModel(phones)
    }

    override fun changeStarState(phoneModel: PhoneModel) {
        phoneListAdapter.changeStarAt(phoneModel)
        sortPhones()
    }

}
