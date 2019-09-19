package com.armhansa.mobilephonebuyerguide.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.armhansa.mobilephonebuyerguide.PhoneDetailActivity
import com.armhansa.mobilephonebuyerguide.adapter.FavoriteListAdapter
import com.armhansa.mobilephonebuyerguide.constant.ConstantValue
import com.armhansa.mobilephonebuyerguide.extension.SwipeToDeleteCallback
import com.armhansa.mobilephonebuyerguide.listener.OnClickItemPhoneListener
import com.armhansa.mobilephonebuyerguide.listener.OnFavoriteRemoveListener
import com.armhansa.mobilephonebuyerguide.listener.OnPhoneModelsChangeListener
import com.armhansa.mobilephonebuyerguide.model.FavoriteListModel
import com.armhansa.mobilephonebuyerguide.model.PhoneModel
import kotlinx.android.synthetic.main.fragment_favorite_list.*

class FavoriteListFragment : Fragment()
    , OnClickItemPhoneListener, OnPhoneModelsChangeListener {
    companion object {
        fun newInstance(listener: OnFavoriteRemoveListener) =
            FavoriteListFragment().apply { setListener(listener) }
    }

    private val favoriteListModel: FavoriteListModel = FavoriteListModel.getInstance()
    private lateinit var favoriteListAdapter: FavoriteListAdapter
    private lateinit var listener: OnFavoriteRemoveListener
    private val pref: SharedPreferences by lazy {
        context!!.getSharedPreferences(
            ConstantValue.PREFS_KEY,
            Context.MODE_PRIVATE
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoriteListModel.setListener(this)
        return inflater.inflate(com.armhansa.mobilephonebuyerguide.R.layout.fragment_favorite_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
    }

    private fun setView() {
        favoriteListAdapter = FavoriteListAdapter(context, this)
        rvFavorite.adapter = favoriteListAdapter
        rvFavorite.layoutManager = LinearLayoutManager(context)
        rvFavorite.itemAnimator = DefaultItemAnimator()
        val swipeHandler = object : SwipeToDeleteCallback(context!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = rvFavorite.adapter as FavoriteListAdapter
                val removePhone = adapter.removeAt(viewHolder.adapterPosition)
                val prefEditor = pref.edit()
                prefEditor.putBoolean("FAV_${removePhone.id}", false)
                prefEditor.apply()
                listener.changeStarState(removePhone)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(rvFavorite)
    }

    fun setListener(listener: OnFavoriteRemoveListener) {
        this.listener = listener
    }

    override fun sendToDetailPage(phoneModel: PhoneModel) {
        PhoneDetailActivity.startActivity(context, phoneModel)
    }

    override fun refreshPhoneModels() {
        favoriteListAdapter.setFavoriteModel(favoriteListModel.getFavorites())
    }

}
