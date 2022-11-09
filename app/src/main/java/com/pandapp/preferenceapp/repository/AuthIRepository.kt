package com.pandapp.preferenceapp.repository

interface AuthIRepository {
    fun login(email : String, password : String)
    fun register(userName : String, email : String, password: String)
    fun registerIsSuccess(isSuccess : Boolean) : Boolean
}