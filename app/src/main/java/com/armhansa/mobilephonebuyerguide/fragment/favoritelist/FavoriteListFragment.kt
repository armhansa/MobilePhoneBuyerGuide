package com.armhansa.mobilephonebuyerguide.fragment.favoritelist

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
import com.armhansa.mobilephonebuyerguide.extension.SortingPhoneModelList
import com.armhansa.mobilephonebuyerguide.extension.SwipeToDeleteCallback
import com.armhansa.mobilephonebuyerguide.listener.OnClickItemPhoneListener
import com.armhansa.mobilephonebuyerguide.listener.OnFavoriteChangeListener
import com.armhansa.mobilephonebuyerguide.listener.OnFavoriteRemoveListener
import com.armhansa.mobilephonebuyerguide.model.PhoneModel
import kotlinx.android.synthetic.main.fragment_favorite_list.*

class FavoriteListFragment : Fragment()
    , OnClickItemPhoneListener, OnFavoriteChangeListener {
    companion object {
        fun newInstance() = FavoriteListFragment()
    }

    private var favorites: ArrayList<PhoneModel> = arrayListOf()
    private lateinit var favoriteListAdapter: FavoriteListAdapter
    private lateinit var phoneListener: OnFavoriteRemoveListener
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
                val removePhone = removeFavoriteAt(viewHolder.adapterPosition)
                favoriteListAdapter.setFavoritesModel(favorites)
                phoneListener.changeStarState(removePhone)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(rvFavorite)
    }

    fun setPhoneListener(listener: OnFavoriteRemoveListener) {
        this.phoneListener = listener
    }

    fun removeFavoriteAt(index: Int): PhoneModel {
        val removeFavorite = favorites[index]
        favorites.removeAt(index)
        pref?.let {
            val prefEditor = it.edit()
            prefEditor.putBoolean("FAV_${removeFavorite.id}", false)
            prefEditor.apply()
        }
        return removeFavorite
    }

    override fun sendToDetailPage(phoneModel: PhoneModel) {
        PhoneDetailActivity.startActivity(context, phoneModel)
    }

    override fun setFavorites(favorites: ArrayList<PhoneModel>) {
        this.favorites.addAll(SortingPhoneModelList.sorted(favorites))
        favoriteListAdapter.setFavoritesModel(this.favorites)
    }

    override fun add(newFavorite: PhoneModel) {
        favorites.add(newFavorite)
    }

    override fun remove(removeFavorite: PhoneModel) {
        favorites.remove(removeFavorite)
    }

    override fun sortFavorites() {
        favorites = SortingPhoneModelList.sorted(favorites)
        favoriteListAdapter.setFavoritesModel(favorites)
    }

}
