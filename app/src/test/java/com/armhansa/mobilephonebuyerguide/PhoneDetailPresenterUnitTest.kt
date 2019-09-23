package com.armhansa.mobilephonebuyerguide

import com.armhansa.mobilephonebuyerguide.activity.phonedetail.PhoneDetailInterface
import com.armhansa.mobilephonebuyerguide.activity.phonedetail.PhoneDetailPresenter
import com.armhansa.mobilephonebuyerguide.entity.PhoneImageEntity
import com.armhansa.mobilephonebuyerguide.service.PhoneApiService
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.verify
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class PhoneDetailPresenterUnitTest {
    @Mock
    lateinit var view: PhoneDetailInterface
    @Mock
    lateinit var service: PhoneApiService

    lateinit var presenter: PhoneDetailPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = PhoneDetailPresenter.getInstance(view, service)
    }

    @Test
    fun testGetMobileDetailImageListApiSuccess() {
        //given
        val phoneId = 5
        whenever(service.getMobileImgById(phoneId)).thenReturn(mock())
        //when
        presenter.getImageFromApi(phoneId)
        //then
        verify(service).getMobileImgById(phoneId)
//        verify(view).setPhoneImageList(any())
    }

    @Test
    fun testGetMobileDetailImageListApiFail() {
        //given
        val phoneId = 5
        val call = mock<Call<List<PhoneImageEntity>>>()
        whenever(service.getMobileImgById(phoneId)).thenReturn(call)
        whenever(call.enqueue(any())).thenAnswer {
            it.getArgument<Callback<List<PhoneImageEntity>>>(0).onFailure(mock(), mock())
        }
        //when
        presenter.getImageFromApi(phoneId)
        //then
        verify(view).toastError(any())
        verifyNoMoreInteractions(view)
    }

    @Test
    fun testGetMobileDetailImageListApiResponseBodyNull() {
        //given
        val phoneId = 5
        val call = mock<Call<List<PhoneImageEntity>>>()
        whenever(service.getMobileImgById(phoneId)).thenReturn(call)
        whenever(call.enqueue(any())).thenAnswer {
            it.getArgument<Callback<List<PhoneImageEntity>>>(0).onResponse(mock(), Response.success(null))
        }
        //when
        presenter.getImageFromApi(phoneId)
        //then
        verifyNoMoreInteractions(view)
    }

    @Test
    fun testGetMobileDetailImageListApiResponseBodyEmpty() {
        //given
        val phoneId = 5
        val call = mock<Call<List<PhoneImageEntity>>>()
        whenever(service.getMobileImgById(phoneId)).thenReturn(call)
        whenever(call.enqueue(any())).thenAnswer {
            it.getArgument<Callback<List<PhoneImageEntity>>>(0).onResponse(mock(), Response.success(listOf()))
        }
        //when
        presenter.getImageFromApi(phoneId)
        //then
        verifyNoMoreInteractions(view)
    }
}