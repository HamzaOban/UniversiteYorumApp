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
    var rateList = ArrayList<Rate>()
    var userNameList = ArrayList<String>()
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
                val rate = snapshot.getValue(Rate::class.java)
                val snapName = snapshot.child("userName").value
                if (userNameList.isEmpty()){
                    rate?.let {
                        rateList.add(it)
                        userNameList.add(snapName.toString())
                        detailIRepository.showRate(rate,rateList)
                        Log.d("Logladık","1")
                    }

                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

                val rate = snapshot.getValue(Rate::class.java)
                val snapName = snapshot.child("userName").value

                for (i in rateList.indices){
                    Log.d("Logladık", rateList[i].userName)
                    if (rateList[i].userName == snapName){
                        if (rate != null) {
                            rateList[i].rate = rate.rate

                        }
                    }
                }
                rate?.let { detailIRepository.showRate(it,rateList) }
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
}