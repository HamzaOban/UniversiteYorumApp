package com.pandapp.preferenceapp.util

import com.google.firebase.auth.FirebaseAuth

class appUtil {
    fun getUID() : String?{
        val firebaseAuth = FirebaseAuth.getInstance()
        return firebaseAuth.uid

    }
}