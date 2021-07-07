package com.armhansa.mobilephonebuyerguide

import com.armhansa.mobilephonebuyerguide.activity.main.MainActivity
import com.armhansa.mobilephonebuyerguide.constant.SortType
import com.armhansa.mobilephonebuyerguide.extension.SortingPhoneModelList
import com.armhansa.mobilephonebuyerguide.supporttest.SupportModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SortingPhoneModelTest {

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testSortByLowPrice() {
        //given
        val phones = SupportModel.getSupportPhonesModel()
        MainActivity.SORT_TYPE = SortType.LOW_PRICE
        //when
        val sortedPhones = SortingPhoneModelList.sorted(phones)
        //then
        for (i in 1 until sortedPhones.count()) {
            assert(sortedPhones[i-1].price <= sortedPhones[i].price)
        }
    }

    @Test
    fun testSortByHighPrice() {
        //given
        val phones = SupportModel.getSupportPhonesModel()
        MainActivity.SORT_TYPE = SortType.HIGH_PRICE
        //when
        val sortedPhones = SortingPhoneModelList.sorted(phones)
        //then
        for (i in 1 until sortedPhones.count()) {
            assert(sortedPhones[i-1].price >= sortedPhones[i].price)
        }
    }

    @Test
    fun testSortByHighRating() {
        //given
        val phones = SupportModel.getSupportPhonesModel()
        MainActivity.SORT_TYPE = SortType.RATING
        //when
        val sortedPhones = SortingPhoneModelList.sorted(phones)
        //then
        for (i in 1 until sortedPhones.count()) {
            assert(sortedPhones[i-1].rating >= sortedPhones[i].rating)
        }
    }
}