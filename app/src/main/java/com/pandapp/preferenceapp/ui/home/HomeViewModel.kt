package com.pandapp.preferenceapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pandapp.preferenceapp.model.Preference
import com.pandapp.preferenceapp.model.Preferences
import com.pandapp.preferenceapp.repository.PreferenceIRepository
import com.pandapp.preferenceapp.repository.PreferenceRepository

class HomeViewModel : ViewModel() ,PreferenceIRepository{

    private var preferenceRepository = PreferenceRepository(this)
    val preference = MutableLiveData<List<String>>()

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun getAllUniversityName(){
        preferenceRepository.getAllUniversityName()
    }
    fun getPreference() : LiveData<List<String>>{

        return preference
    }
    override fun getListUniversityName(universityNameList: ArrayList<String>) {
        preference.value = universityNameList
    }

    override fun getListCityName(preferenceList: ArrayList<Preference>) {
        TODO("Not yet implemented")
    }
}