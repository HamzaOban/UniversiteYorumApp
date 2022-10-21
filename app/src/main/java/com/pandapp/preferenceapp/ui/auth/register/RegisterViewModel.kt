package com.pandapp.preferenceapp.ui.auth.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pandapp.preferenceapp.repository.AuthIRepository
import com.pandapp.preferenceapp.repository.AuthRepository

class RegisterViewModel : ViewModel() ,AuthIRepository {

    private val authRepository : AuthRepository = AuthRepository(this)
    var authTrigger = MutableLiveData<Boolean>()
    var authEmail = MutableLiveData<String>()
    var authUserName = MutableLiveData<String>()
    var authPassword = MutableLiveData<String>()

    fun registerAuth(userName: String,email: String,password: String){
        authTrigger.value = true
        authRepository.register(userName,email,password)
    }
    override fun login(email: String, password: String) {

    }

    override fun register(userName: String, email: String, password: String) {
        authEmail.value = email
        authUserName.value = userName
        authPassword.value = password
    }
}