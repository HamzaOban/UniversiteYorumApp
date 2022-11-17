package com.pandapp.preferenceapp.repository

import com.pandapp.preferenceapp.model.Detail
import com.pandapp.preferenceapp.model.Rate

interface DetailIRepository {
    fun showDetail(commentList : ArrayList<Detail>)
    fun sendComment(comment : String,uniName : String, userName : String)
    fun rateIt(rate: Rate)
    fun showRate(rate: Rate,rateList : ArrayList<Rate>)
}