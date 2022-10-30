package com.pandapp.preferenceapp.repository

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.pandapp.preferenceapp.model.Detail
import java.util.UUID

class DetailRepository(detailRepo: DetailIRepository) {
    private val detailIRepository : DetailIRepository = detailRepo
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
    }
    fun sendComment(comment : String, uniName : String,userName : String,bolumName : String){
        val detail = Detail(userName,comment)
        val ref = FirebaseDatabase.getInstance().getReference("comment/$uniName/$bolumName")
        ref.child(UUID.randomUUID().toString()).setValue(detail)
    }
}