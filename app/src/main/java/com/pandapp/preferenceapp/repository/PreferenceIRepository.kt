package com.pandapp.preferenceapp.repository

import com.pandapp.preferenceapp.model.User

interface PreferenceIRepository {
    fun getListUniversityName(universityNameList : ArrayList<String>)
    fun getListCityName(cityList : ArrayList<String>)
    fun getListDegreeName(degreeList : ArrayList<String>)
    fun getUserInfo(userInfo : User)
}