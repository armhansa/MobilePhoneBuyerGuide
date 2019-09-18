package com.armhansa.mobilephonebuyerguide.fragment.favoritelist

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.armhansa.mobilephonebuyerguide.PhoneDetailActivity
import com.armhansa.mobilephonebuyerguide.R
import com.armhansa.mobilephonebuyerguide.adapter.FavoriteListAdapter
import com.armhansa.mobilephonebuyerguide.listener.OnClickItemPhoneListener
import com.armhansa.mobilephonebuyerguide.listener.OnFavoriteChangeListener
import com.armhansa.mobilephonebuyerguide.model.FavoriteListModel
import com.armhansa.mobilephonebuyerguide.model.PhoneModel
import kotlinx.android.synthetic.main.fragment_favorite_list.*

class FavoriteListFragment(private val pref: SharedPreferences) : Fragment()
    , OnClickItemPhoneListener, OnFavoriteChangeListener {

//    private val presenter by lazy {
//        FavoriteListPresenter.getInstance(this)
//    }
    private val favoriteListModel: FavoriteListModel = FavoriteListModel.getInstance()
    private lateinit var favoriteListAdapter: FavoriteListAdapter

    companion object {
        fun newInstance(sharedPreferences: SharedPreferences) = FavoriteListFragment(sharedPreferences)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoriteListModel.setListener(this)
        return inflater.inflate(R.layout.fragment_favorite_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
    }

    private fun setView() {
        favoriteListAdapter = FavoriteListAdapter(context, this, pref)
        rvFavorite.adapter = favoriteListAdapter
        rvFavorite.layoutManager = LinearLayoutManager(context)
        rvFavorite.itemAnimator = DefaultItemAnimator()
    }

    override fun sendToDetailPage(phoneModel: PhoneModel) {
        PhoneDetailActivity.startActivity(context, phoneModel)
    }

    override fun refreshFavorites() {
        favoriteListAdapter.setFavoriteModel(favoriteListModel.getFavorites())
    }

}
