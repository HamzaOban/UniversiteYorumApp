package com.pandapp.preferenceapp.util

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class appUtil {
    companion object{
        var userName : String = ""

        fun getUserName(){
            FirebaseDatabase.getInstance()
                .getReference("users/${getUID()}").addListenerForSingleValueEvent(object  : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {

                        val userNameValue = snapshot.child("userName").value
                        if (userNameValue != null){
                            userName = userNameValue.toString()
                        }

                        Log.d("UserName", userName.toString())
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }

                })


        }
        fun getUID() : String?{
            val firebaseAuth = FirebaseAuth.getInstance()
            return firebaseAuth.uid
        }
    }



}