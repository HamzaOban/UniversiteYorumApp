package com.pandapp.preferenceapp.repository

import android.util.Log
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.pandapp.preferenceapp.model.BolumTip
import com.pandapp.preferenceapp.model.Preference
import com.pandapp.preferenceapp.model.Preferences

class PreferenceRepository(preferenceIRepository: PreferenceIRepository) {
    private var preferenceIRepository : PreferenceIRepository ?= preferenceIRepository
    private var preferenceList = ArrayList<String>()

    fun getAllUniversityName(){
        FirebaseDatabase.getInstance().reference.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val uniAdiValue = snapshot.child("uniAdi").value
                preferenceList.add(uniAdiValue.toString())
                preferenceIRepository?.getListUniversityName(preferenceList)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        /*FirebaseDatabase.getInstance().getReference("0").addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("Hata2",previousChildName.toString())
                val ss = snapshot.value



               /*if (ss!=null){
                    //preferenceList.add(ss as Preference)
                    //preferenceIRepository?.getListUniversityName(preferenceList)
                }*/

            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })*/
    }
}