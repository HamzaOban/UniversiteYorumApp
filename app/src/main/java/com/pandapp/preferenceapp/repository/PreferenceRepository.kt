package com.pandapp.preferenceapp.repository

import android.util.Log
import android.widget.TextView
import com.google.firebase.database.*
import com.pandapp.preferenceapp.R
import com.pandapp.preferenceapp.model.User
import com.pandapp.preferenceapp.util.appUtil

class PreferenceRepository(preferenceIRepository: PreferenceIRepository) {
    private var preferenceIRepository : PreferenceIRepository ?= preferenceIRepository
    private var uniNameList = ArrayList<String>()
    private var cityNameList = ArrayList<String>()
    private var degreeNameList = ArrayList<String>()
    private lateinit var userInfo : User

    fun getAllUniversityName(){
        FirebaseDatabase.getInstance().reference.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val uniAdiValue = snapshot.child("uniAdi").value

                if (!uniNameList.contains(uniAdiValue)){
                    uniNameList.add(uniAdiValue.toString())
                    preferenceIRepository?.getListUniversityName(uniNameList)
                }
            }
            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val uniAdiValue = snapshot.child("uniAdi").value

                if (!uniNameList.contains(uniAdiValue)){
                    uniNameList.add(uniAdiValue.toString())
                    preferenceIRepository?.getListUniversityName(uniNameList)
                }
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

    }
    fun getAllDegreeList(uniName : String){
        FirebaseDatabase.getInstance().reference.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val uniAdiValue = snapshot.child("uniAdi").value
                if (uniAdiValue != uniName){
                    return
                }

                snapshot.child("dil").children.forEach {
                    val degrees = it.child("bolumAdi").value
                    Log.d("selam", degrees.toString())
                    if (uniAdiValue == uniName) {
                        if (!degreeNameList.contains(degrees) && degrees != null) {
                            degreeNameList.add(degrees.toString())
                        }
                    }
                }
                snapshot.child("ea").children.forEach {
                    val degrees = it.child("bolumAdi").value
                    Log.d("selam", degrees.toString())
                    if (uniAdiValue == uniName) {
                        if (!degreeNameList.contains(degrees) && degrees != null) {
                            degreeNameList.add(degrees.toString())
                        }
                    }
                }
                snapshot.child("say").children.forEach {
                    val degrees = it.child("bolumAdi").value
                    Log.d("selam", degrees.toString())
                    if (uniAdiValue == uniName) {
                        if (!degreeNameList.contains(degrees) && degrees != null) {
                            degreeNameList.add(degrees.toString())
                        }
                    }
                }
                snapshot.child("soz").children.forEach {
                    val degrees = it.child("bolumAdi").value
                    Log.d("selam", degrees.toString())
                    if (uniAdiValue == uniName) {
                        if (!degreeNameList.contains(degrees) && degrees != null) {
                            degreeNameList.add(degrees.toString())
                        }
                    }
                }

                preferenceIRepository?.getListDegreeName(degreeNameList)
            }
            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val uniAdiValue = snapshot.child("uniAdi").value
                if (uniAdiValue != uniName){
                    return
                }

                snapshot.child("dil").children.forEach {
                    val degrees = it.child("bolumAdi").value
                    Log.d("selam", degrees.toString())
                    if (uniAdiValue == uniName) {
                        if (!degreeNameList.contains(degrees) && degrees != null) {
                            degreeNameList.add(degrees.toString())
                        }
                    }
                }
                snapshot.child("ea").children.forEach {
                    val degrees = it.child("bolumAdi").value
                    Log.d("selam", degrees.toString())
                    if (uniAdiValue == uniName) {
                        if (!degreeNameList.contains(degrees) && degrees != null) {
                            degreeNameList.add(degrees.toString())
                        }
                    }
                }
                snapshot.child("say").children.forEach {
                    val degrees = it.child("bolumAdi").value
                    Log.d("selam", degrees.toString())
                    if (uniAdiValue == uniName) {
                        if (!degreeNameList.contains(degrees) && degrees != null) {
                            degreeNameList.add(degrees.toString())
                        }
                    }
                }
                snapshot.child("soz").children.forEach {
                    val degrees = it.child("bolumAdi").value
                    Log.d("selam", degrees.toString())
                    if (uniAdiValue == uniName) {
                        if (!degreeNameList.contains(degrees) && degrees != null) {
                            degreeNameList.add(degrees.toString())
                        }
                    }
                }

                preferenceIRepository?.getListDegreeName(degreeNameList)
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
    }
    fun getAllDegreeList(){
        FirebaseDatabase.getInstance().reference.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val dilDegree = snapshot.child("dil/0/bolumAdi").value
                val eaDegree = snapshot.child("ea/0/bolumAdi").value
                val sayDegree = snapshot.child("say/0/bolumAdi").value
                val sozDegree = snapshot.child("soz/0/bolumAdi").value

                if (!degreeNameList.contains(dilDegree) && dilDegree != null){
                    degreeNameList.add(dilDegree.toString())
                }
                if (!degreeNameList.contains(eaDegree) && eaDegree != null){
                    degreeNameList.add(eaDegree.toString())
                }
                if (!degreeNameList.contains(sayDegree) && sayDegree != null){
                    degreeNameList.add(sayDegree.toString())
                }
                if (!degreeNameList.contains(sozDegree) && sozDegree != null){
                    degreeNameList.add(sozDegree.toString())
                }
                preferenceIRepository?.getListDegreeName(degreeNameList)
            }
            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val dilDegree = snapshot.child("dil/0/bolumAdi").value
                val eaDegree = snapshot.child("ea/0/bolumAdi").value
                val sayDegree = snapshot.child("say/0/bolumAdi").value
                val sozDegree = snapshot.child("soz/0/bolumAdi").value

                if (!degreeNameList.contains(dilDegree) && dilDegree != null){
                    degreeNameList.add(dilDegree.toString())
                }
                if (!degreeNameList.contains(eaDegree) && eaDegree != null){
                    degreeNameList.add(eaDegree.toString())
                }
                if (!degreeNameList.contains(sayDegree) && sayDegree != null){
                    degreeNameList.add(sayDegree.toString())
                }
                if (!degreeNameList.contains(sozDegree) && sozDegree != null){
                    degreeNameList.add(sozDegree.toString())
                }
                preferenceIRepository?.getListDegreeName(degreeNameList)
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
    }
    fun getAllCityList(){
        FirebaseDatabase.getInstance().reference.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val uniAdiValue = snapshot.child("sehir").value
                Log.d("Hata11",uniAdiValue.toString())
                if (!cityNameList.contains(uniAdiValue)){
                    cityNameList.add(uniAdiValue.toString())
                    preferenceIRepository?.getListCityName(cityNameList)
                }

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
    }

    fun getUserName(){

        FirebaseDatabase.getInstance().getReference("users/${appUtil.getUID()}").addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val snap = snapshot.getValue(User::class.java)
                if (snap != null){
                    userInfo = snap
                    preferenceIRepository?.getUserInfo(userInfo)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


}