package com.pandapp.preferenceapp.ui.uni

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pandapp.preferenceapp.model.Preference
import com.pandapp.preferenceapp.repository.PreferenceIRepository
import com.pandapp.preferenceapp.repository.PreferenceRepository

class UniversityViewModel : ViewModel() ,PreferenceIRepository{

    private var preferenceRepository = PreferenceRepository(this)
    val preference = MutableLiveData<List<String>>()

    fun getAllUniversityName(){
        preferenceRepository.getAllUniversityName()
    }
    fun getPreference() : LiveData<List<String>>{

        return preference
    }
    override fun getListUniversityName(universityNameList: ArrayList<String>) {
        preference.value = universityNameList
    }

    override fun getListCityName(cityList: ArrayList<String>) {
        TODO("Not yet implemented")
    }


    override fun getListDegreeName(degreeList: ArrayList<String>) {
        TODO("Not yet implemented")
    }
}