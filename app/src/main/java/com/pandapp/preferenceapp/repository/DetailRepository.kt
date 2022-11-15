package com.pandapp.preferenceapp.repository

import android.util.Log
import android.widget.TextView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.pandapp.preferenceapp.model.Detail
import com.pandapp.preferenceapp.model.Rate
import java.util.UUID

class DetailRepository(detailRepo: DetailIRepository) {
    private val detailIRepository : DetailIRepository = detailRepo
    var rateList = ArrayList<Double>()
    private var detailList : ArrayList<Detail> = arrayListOf()

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
        val ref = FirebaseDatabase.getInstance().getReference("comment/$uniName/$bolumName")
        ref.child(UUID.randomUUID().toString()).setValue(detail).addOnSuccessListener {
            commentTextView.text = ""
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
        rateList.clear()
        ref.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val snap = snapshot.child("rate").value
                rateList.add(snap.toString().toDouble())
                detailIRepository.showRate(rate,rateList)
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
        //Log.d("Average",rateList.average().toString())
        detailIRepository.showRate(rate,rateList)
    }
}