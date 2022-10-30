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
            .addOnCompleteListener {
                Log.d("login","Başarılı")
                val action = LoginFragmentDirections.actionLoginFragmentToNavUni()
                Navigation.findNavController(view).navigate(action)

            }
            .addOnFailureListener {
                it.printStackTrace()
            }
    }

    fun register(userName: String, email: String, password : String){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                saveUserToFirebaseDatabase(userName,email,password)
            }
            .addOnFailureListener {
                it.printStackTrace()
            }
    }
    private fun saveUserToFirebaseDatabase(userName: String, email: String, password: String,){

        val ref = FirebaseDatabase.getInstance().getReference("/users/${appUtil.getUID()}")
        val user = User(userName,email,password)
        ref.setValue(user)
            .addOnSuccessListener {
                Handler().postDelayed(Runnable {
                    Log.d("login","database kayıt başarılı")
                    // Code to start new activity and finish this one


                }, 1500L)

            }
    }

}