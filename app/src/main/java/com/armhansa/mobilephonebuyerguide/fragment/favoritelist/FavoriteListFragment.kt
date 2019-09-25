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
import com.armhansa.mobilephonebuyerguide.R
import com.armhansa.mobilephonebuyerguide.activity.phonedetail.PhoneDetailActivity
import com.armhansa.mobilephonebuyerguide.adapter.FavoriteListAdapter
import com.armhansa.mobilephonebuyerguide.constant.ConstantValue
import com.armhansa.mobilephonebuyerguide.extension.SwipeToDeleteCallback
import com.armhansa.mobilephonebuyerguide.listener.OnClickItemPhoneListener
import com.armhansa.mobilephonebuyerguide.listener.OnFavoriteChangeListener
import com.armhansa.mobilephonebuyerguide.listener.OnFavoriteRemoveListener
import com.armhansa.mobilephonebuyerguide.model.PhoneModel
import kotlinx.android.synthetic.main.fragment_favorite_list.*

class FavoriteListFragment : Fragment(), OnClickItemPhoneListener, OnFavoriteChangeListener {

    companion object {
        fun newInstance() = FavoriteListFragment()
    }

    private val presenter: FavoriteListPresenter by lazy { FavoriteListPresenter(pref) }
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
        return inflater.inflate(R.layout.fragment_favorite_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
    }

    private fun setView() {
        favoriteListAdapter = FavoriteListAdapter(this)
        rvFavorite.adapter = favoriteListAdapter
        rvFavorite.layoutManager = LinearLayoutManager(context)
        rvFavorite.itemAnimator = DefaultItemAnimator()
        val swipeHandler = object : SwipeToDeleteCallback(context!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val removePhone = presenter.removeFavoriteAt(viewHolder.adapterPosition)
                favoriteListAdapter.setFavoritesModel(presenter.getFavorites())
                phoneListener.changeStarState(removePhone)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(rvFavorite)
    }

    fun setPhoneListener(listener: OnFavoriteRemoveListener) {
        this.phoneListener = listener
    }

    override fun sendToDetailPage(phoneModel: PhoneModel) {
        PhoneDetailActivity.startActivity(context, phoneModel)
    }

    override fun setFavorites(favorites: ArrayList<PhoneModel>) {
        presenter.setFavorites(favorites)
        favoriteListAdapter.setFavoritesModel(presenter.getFavorites())
    }

    override fun add(newFavorite: PhoneModel) {
        presenter.addFavorite(newFavorite)
    }

    override fun remove(removeFavorite: PhoneModel) {
        presenter.removeFavorite(removeFavorite)
    }

    override fun sortFavorites() {
        favoriteListAdapter.setFavoritesModel(presenter.sortFavorites())
    }

}
