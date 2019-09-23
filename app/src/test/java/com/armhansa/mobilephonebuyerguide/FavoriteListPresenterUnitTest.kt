package com.armhansa.mobilephonebuyerguide

import android.content.SharedPreferences
import com.armhansa.mobilephonebuyerguide.activity.main.MainActivity
import com.armhansa.mobilephonebuyerguide.constant.SortType
import com.armhansa.mobilephonebuyerguide.fragment.favoritelist.FavoriteListPresenter
import com.armhansa.mobilephonebuyerguide.supporttest.SupportModel
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteListPresenterUnitTest {
    @Mock
    lateinit var pref: SharedPreferences

    lateinit var presenter: FavoriteListPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = FavoriteListPresenter.getInstance(pref)
    }

    @Test
    fun testRemoveFavoriteAtIndexSuccess() {
        //given
        val favorites = SupportModel.getSupportPhonesModel()
        presenter.setFavorites(favorites)
        val editor: SharedPreferences.Editor = mock()
        whenever(pref.edit()).thenReturn(editor)
        val firstCount = presenter.getFavorites().count()
        //when
        presenter.removeFavoriteAt(0)
        //then
        verify(editor).putBoolean(anyString(), anyBoolean())
        assertEquals(firstCount - 1, presenter.getFavorites().count())
    }

    @Test
    fun testRemoveFavoriteAtIndexPrefNull() {
        //given
        presenter = FavoriteListPresenter.getInstance(null)
        val favorites = SupportModel.getSupportPhonesModel()
        presenter.setFavorites(favorites)
        val firstCount = presenter.getFavorites().count()
        //when
        presenter.removeFavoriteAt(0)
        //then
        assertEquals(firstCount - 1, presenter.getFavorites().count())
    }

    @Test
    fun testAddFavorite() {
        //given
        val favorite = SupportModel.getSupportPhonesModel()[0]
        val firstCount = presenter.getFavorites().count()
        //when
        presenter.addFavorite(favorite)
        //then
        assertEquals(firstCount + 1, presenter.getFavorites().count())
    }

    @Test
    fun testRemoveFavorite() {
        //given
        val favorites = SupportModel.getSupportPhonesModel()
        val removeFavorite = favorites[0]
        presenter.setFavorites(favorites)
        val firstCount = presenter.getFavorites().count()
        //when
        presenter.removeFavorite(removeFavorite)
        //then
        assertEquals(firstCount - 1, presenter.getFavorites().count())
    }

    @Test
    fun testSortFavorites() {
        //given
        val favorites = SupportModel.getSupportPhonesModel()
        presenter.setFavorites(favorites)
        val count = presenter.getFavorites().count()
        MainActivity.SORT_TYPE = SortType.RATING
        //when
        val newFavorites = presenter.sortFavorites()
        //then
        assertEquals(count, presenter.getFavorites().count())
        assertEquals(count, newFavorites.count())
        for (i in 1 until count) {
            assert(newFavorites[i-1].rating >= newFavorites[i].rating)
        }
    }
}