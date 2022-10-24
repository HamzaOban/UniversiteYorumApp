package com.pandapp.preferenceapp.repository

import com.google.firebase.database.FirebaseDatabase
import com.pandapp.preferenceapp.model.Detail

class DetailRepository(detailRepo: DetailIRepository) {
    private val detailIRepository : DetailIRepository = detailRepo

    fun showDetails(){

    }
    fun sendComment(comment : String, uniName : String,userName : String){
        detailIRepository.sendComment(comment,uniName,userName)
        val detail = Detail(userName,comment)
        FirebaseDatabase.getInstance().getReference("comment/$uniName").setValue(detail)

    }
}