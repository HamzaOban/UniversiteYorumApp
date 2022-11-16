package com.pandapp.preferenceapp.repository

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.pandapp.preferenceapp.MainActivity
import com.pandapp.preferenceapp.model.User
import com.pandapp.preferenceapp.ui.auth.login.LoginFragment
import com.pandapp.preferenceapp.ui.auth.login.LoginFragmentDirections
import com.pandapp.preferenceapp.util.appUtil
import java.time.Duration
import kotlin.coroutines.coroutineContext

class AuthRepository(authIRepo: AuthIRepository) {
    private var authIRepository: AuthIRepository = authIRepo


    fun login(email: String, password : String, view: View){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                val action = LoginFragmentDirections.actionLoginFragmentToNavHome()
                Navigation.findNavController(view).navigate(action)
            }
            .addOnFailureListener {
                it.printStackTrace()
                Toast.makeText(view.context,"Kullanıcı Adı veya Şifre hatalı! Tekrar Deneyiniz.",Toast.LENGTH_LONG).show()
            }
    }

    fun register(userName: String, email: String, password : String,view: View){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                saveUserToFirebaseDatabase(userName,email,password)
            }
            .addOnFailureListener {
                it.printStackTrace()
                Toast.makeText(view.context,"${it.message}! Tekrar Deneyiniz.",Toast.LENGTH_LONG).show()
            }
    }
    private fun saveUserToFirebaseDatabase(userName: String, email: String, password: String){

        val ref = FirebaseDatabase.getInstance().getReference("/users/${appUtil.getUID()}")
        val user = User(userName,email,password)
        ref.setValue(user)
            .addOnSuccessListener {
                Handler().postDelayed(Runnable {
                    Log.d("login","database kayıt başarılı")
                    // Code to start new activity and finish this one
                    authIRepository.registerIsSuccess(true)

                }, 1500L)

            }
            .addOnFailureListener {
                authIRepository.registerIsSuccess(false)
            }
    }

}