package com.pandapp.preferenceapp.repository

interface PreferenceIRepository {
    fun getListUniversityName(universityNameList : ArrayList<String>)
    fun getListCityName(cityList : ArrayList<String>)
    fun getListDegreeName(degreeList : ArrayList<String>)
}