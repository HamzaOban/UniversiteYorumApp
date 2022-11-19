package com.pandapp.preferenceapp.repository

import android.util.Log
import android.widget.TextView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.pandapp.preferenceapp.model.Detail
import com.pandapp.preferenceapp.model.Like
import com.pandapp.preferenceapp.model.Rate
import com.pandapp.preferenceapp.util.appUtil
import java.util.UUID

class DetailRepository(detailRepo: DetailIRepository) {
    private val detailIRepository : DetailIRepository = detailRepo
    var rateList = ArrayList<Rate>()
    var userNameList = ArrayList<String>()
    private var detailList : ArrayList<Detail> = arrayListOf()
    private var isSuccess : Boolean = false

    fun showDetails(uniName : String,bolumName : String){
        val ref = FirebaseDatabase.getInstance().getReference("comment/$uniName/$bolumName")
        ref.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val value = snapshot.getValue(Detail::class.java)
                if (value != null){
                    detailList.add(value)
                    detailIRepository.showDetail(detailList)
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val value = snapshot.getValue(Detail::class.java)
                if (value != null){
                    detailList.forEach {
                        if (it.userName == value.userName){
                            it.comment = value.comment
                            detailIRepository.showDetail(detailList)
                        }
                    }

                }
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        detailIRepository.showDetail(detailList)
    }

    fun sendComment(comment : String, uniName : String,userName : String,bolumName : String, commentTextView: TextView){
        val detail = Detail(userName,comment)
        val ref = FirebaseDatabase.getInstance().getReference("comment/$uniName/$bolumName/$userName")
        ref.setValue(detail)
            .addOnSuccessListener {
            commentTextView.text = ""
                detailIRepository.sendComment(comment,uniName,userName)
        }
    }

    fun rateIt(uniName: String, bolumName: String, rate : Double,userName: String){
        val rate = Rate(uniName,bolumName,rate,userName)
        val ref = FirebaseDatabase.getInstance().getReference("rate/$uniName/$bolumName/$userName")
        ref.setValue(rate)
            .addOnSuccessListener {
                detailIRepository.rateIt(rate)

            }
    }

    fun showRate(rate: Rate){
        val ref = FirebaseDatabase.getInstance().getReference("rate/${rate.uniName}/${rate.bolumName}")
        ref.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val rateValue = snapshot.getValue(Rate::class.java)
                val snapName = snapshot.child("userName").value
                rateValue?.let {
                    rateList.add(it)
                    userNameList.add(snapName.toString())
                    detailIRepository.showRate(rate,rateList)
                    Log.d("Logladık","1")
                }
            }
            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            }
            override fun onChildRemoved(snapshot: DataSnapshot) {
            }
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }
            override fun onCancelled(error: DatabaseError) {
            }

        })
        if (rateList.isEmpty()){
            detailIRepository.showRate(rate,rateList)
        }
    }

    fun isSuccess(rate: Rate){
        val ref = FirebaseDatabase.getInstance().getReference("rate/${rate.uniName}/${rate.bolumName}")
        ref.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val rate2 = snapshot.getValue(Rate::class.java)
                val snapName = snapshot.child("userName").value
                Log.d("Selam2",appUtil.userName + "  " +snapName)
                if (rate.userName == snapName){
                    isSuccess = true
                    detailIRepository.isSuccess(isSuccess)
                }
                else{
                    isSuccess = false
                    detailIRepository.isSuccess(isSuccess)
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val rate2 = snapshot.getValue(Rate::class.java)
                val snapName = snapshot.child("userName").value
                Log.d("Selam2",appUtil.userName + "  " +snapName)
                if (appUtil.userName == snapName){
                    isSuccess = true
                    detailIRepository.isSuccess(isSuccess)
                }
                else{
                    isSuccess = false
                    detailIRepository.isSuccess(isSuccess)
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

    fun setLikes(rate: Rate) {
        val likes = Like(rate.userName)
        FirebaseDatabase.getInstance()
            .getReference("comment/${rate.uniName}/${rate.bolumName}/${rate.userName}")
            .setValue(likes)
            .addOnSuccessListener {
                detailIRepository.setLike(likes)
            }
    }
}