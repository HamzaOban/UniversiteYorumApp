package com.pandapp.preferenceapp.ui.auth.login

import androidx.lifecycle.ViewModel
import com.pandapp.preferenceapp.repository.AuthIRepository

class LoginViewModel : ViewModel(), AuthIRepository {

    override fun login(email: String, password: String) {

    }

    override fun register(userName: String, email: String, password: String) {

    }

    override fun saveDatabase(userName: String, email: String, password: String) {

    }

}