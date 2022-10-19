package com.pandapp.preferenceapp.ui.city

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pandapp.preferenceapp.model.Preference
import com.pandapp.preferenceapp.repository.PreferenceIRepository
import com.pandapp.preferenceapp.repository.PreferenceRepository

class CityViewModel : ViewModel() , PreferenceIRepository {

    private var preferenceRepository = PreferenceRepository(this)
    val cityLists = MutableLiveData<List<String>>()

    fun getAllCityNameList(){
        preferenceRepository.getAllCityList()
    }
    override fun getListUniversityName(universityNameList: ArrayList<String>) {

    }

    override fun getListCityName(cityList: ArrayList<String>) {
        cityLists.value = cityList
    }

    override fun getListDegreeName(degreeList: ArrayList<String>) {

    }
}