package com.armhansa.mobilephonebuyerguide

import android.content.SharedPreferences
import com.armhansa.mobilephonebuyerguide.activity.main.MainActivity
import com.armhansa.mobilephonebuyerguide.constant.SortType
import com.armhansa.mobilephonebuyerguide.entity.PhoneEntity
import com.armhansa.mobilephonebuyerguide.fragment.phonelist.PhoneListInterface
import com.armhansa.mobilephonebuyerguide.fragment.phonelist.PhoneListPresenter
import com.armhansa.mobilephonebuyerguide.listener.OnFavoriteChangeListener
import com.armhansa.mobilephonebuyerguide.service.PhoneApiService
import com.armhansa.mobilephonebuyerguide.supporttest.SupportModel.Companion.getSupportPhonesEntity
import com.armhansa.mobilephonebuyerguide.supporttest.SupportModel.Companion.getSupportPhonesModel
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class PhoneListPresenterTest {

    @Mock
    lateinit var view: PhoneListInterface
    @Mock
    lateinit var favoriteListener: OnFavoriteChangeListener
    @Mock
    lateinit var service: PhoneApiService
    @Mock
    lateinit var pref: SharedPreferences

    lateinit var presenter: PhoneListPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = PhoneListPresenter(
            view,
            favoriteListener,
            service,
            pref
        )
    }

    @Test
    fun testSortedPhone() {
        //given
        presenter.setPhones(getSupportPhonesModel())
        MainActivity.SORT_TYPE = SortType.HIGH_PRICE
        //when
        val sortedPhones = presenter.sortedPhones()
        //then
        assertEquals(2, sortedPhones[0].id)
        assertEquals(1, sortedPhones[1].id)
    }

    @Test
    fun testGetPhoneModelFromApi() {
        //given
        whenever(service.getMobiles()).thenReturn(mock())
        //when
        presenter.getPhoneModelFromApi()
        //then
        verify(service).getMobiles()
    }

    @Test
    fun testGetPhoneModelFromApiSuccess() {
        //given
        val call = mock<Call<List<PhoneEntity>>>()
        whenever(service.getMobiles()).thenReturn(call)
        whenever(call.enqueue(any())).thenAnswer {
            it.getArgument<Callback<List<PhoneEntity>>>(0).onResponse(mock(), Response.success(getSupportPhonesEntity()))
        }
        //when
        presenter.getPhoneModelFromApi()
        //then
        verify(view).setPhones(any())
        verify(view).invisibleProgressBar()
    }

    @Test
    fun testGetPhoneModelFromApiFail() {
        //given
        val call: Call<List<PhoneEntity>> = mock()
        whenever(service.getMobiles()).thenReturn(call)
        whenever(call.enqueue(any())).thenAnswer {
            it.getArgument<Callback<List<PhoneEntity>>>(0).onFailure(mock(), mock())
        }
        //when
        presenter.getPhoneModelFromApi()
        //then
        verify(view).toastError(any())
        verify(view).invisibleProgressBar()
    }

    @Test
    fun testGetPhoneModelFromApiResponseBodyNull() {
        //given
        val call: Call<List<PhoneEntity>> = mock()
        whenever(service.getMobiles()).thenReturn(call)
        whenever(call.enqueue(any())).thenAnswer {
            it.getArgument<Callback<List<PhoneEntity>>>(0).onResponse(mock(), Response.success(null))
        }
        //when
        presenter.getPhoneModelFromApi()
        //then
        verify(view).invisibleProgressBar()
    }

    @Test
    fun testGetPhoneModelFromApiResponseEmpty() {
        //given
        val call: Call<List<PhoneEntity>> = mock()
        whenever(service.getMobiles()).thenReturn(call)
        whenever(call.enqueue(any())).thenAnswer {
            it.getArgument<Callback<List<PhoneEntity>>>(0).onResponse(mock(), Response.success(listOf()))
        }
        //when
        presenter.getPhoneModelFromApi()
        //then
        verify(view).invisibleProgressBar()
    }

    @Test
    fun testGetPhoneModelFromEntity() {
        //given
        whenever(pref.getBoolean("FAV_3", false)).thenReturn(true)
        //when
        val phones = presenter.getPhoneModelFrom(getSupportPhonesEntity())
        //then
        assertEquals(5, phones.count())
        assertFalse(phones[0].isFavorite)
        assertFalse(phones[1].isFavorite)
        assertTrue(phones[2].isFavorite)
        assertFalse(phones[3].isFavorite)
        assertFalse(phones[4].isFavorite)
        assertEquals(1, phones[0].id)
        assertEquals(2, phones[1].id)
        assertEquals(3, phones[2].id)
        assertEquals(4, phones[3].id)
        assertEquals(5, phones[4].id)
    }

    @Test
    fun testGetFavoriteModelFromPhoneModel() {
        //given
        val phones = getSupportPhonesModel()
        //when
        val favorites = presenter.getFavoriteModelFrom(phones)
        //then
        assertEquals(2, favorites.count())
        assertEquals(1, favorites[0].id)
        assertEquals("Moto E4 Plus", favorites[0].name)
    }

}