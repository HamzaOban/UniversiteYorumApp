package com.pandapp.preferenceapp.repository

import com.pandapp.preferenceapp.model.Preference
import com.pandapp.preferenceapp.model.Preferences

interface PreferenceIRepository {
    fun getListUniversityName(universityNameList : ArrayList<String>)
    fun getListCityName(preferenceList : ArrayList<Preference>)
}