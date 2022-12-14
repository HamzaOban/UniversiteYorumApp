package com.pandapp.preferenceapp.ui.degree

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pandapp.preferenceapp.model.Preference
import com.pandapp.preferenceapp.model.User
import com.pandapp.preferenceapp.repository.PreferenceIRepository
import com.pandapp.preferenceapp.repository.PreferenceRepository

class DegreeViewModel : ViewModel(),PreferenceIRepository {

    private var preferenceRepository = PreferenceRepository(this)
    val degreeLists = MutableLiveData<List<String>>()
    val isLoaded = MutableLiveData<Boolean>()


    fun getAllDegreeList(){
        preferenceRepository.getAllDegreeList()
    }
    fun getAllDegreeList(uniName : String){
        preferenceRepository.getAllDegreeList(uniName)
    }
    override fun getListUniversityName(universityNameList: ArrayList<String>) {
    }
    override fun getListCityName(cityList: ArrayList<String>) {
    }
    override fun getListDegreeName(degreeList: ArrayList<String>) {
        isLoaded.value = degreeList.isEmpty()
        degreeLists.value = degreeList
    }

    override fun getUserInfo(userInfo: User) {
    }
}