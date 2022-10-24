package com.pandapp.preferenceapp.repository

import com.pandapp.preferenceapp.model.Detail

interface DetailIRepository {
    fun showDetail(commentList : ArrayList<Detail>)
    fun sendComment(comment : String,uniName : String, userName : String)
}