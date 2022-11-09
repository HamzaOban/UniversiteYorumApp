package com.pandapp.preferenceapp.ui.auth.login

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pandapp.preferenceapp.repository.AuthIRepository
import com.pandapp.preferenceapp.repository.AuthRepository

class LoginViewModel : ViewModel(), AuthIRepository {
    private val authRepository : AuthRepository = AuthRepository(this)
    var authTrigger = MutableLiveData<Boolean>()
    var authEmail = MutableLiveData<String>()
    var authPassword = MutableLiveData<String>()

    fun loginAuth(email: String,password: String, view: View){
        authTrigger.value = true
        authRepository.login(email,password,view)
    }

    override fun login(email: String, password: String) {
        authEmail.value = email
        authPassword.value = password
    }

    override fun register(userName: String, email: String, password: String) {

    }

    override fun registerIsSuccess(isSuccess: Boolean): Boolean {
        TODO("Not yet implemented")
    }
}