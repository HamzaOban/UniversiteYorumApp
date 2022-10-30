package com.pandapp.preferenceapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pandapp.preferenceapp.model.Detail
import com.pandapp.preferenceapp.repository.DetailIRepository
import com.pandapp.preferenceapp.repository.DetailRepository
import com.pandapp.preferenceapp.repository.PreferenceRepository

class DetailViewModel : ViewModel() , DetailIRepository{
    private var detailRepository = DetailRepository(this)
    val detail = MutableLiveData<Detail>()
    val detailList = MutableLiveData<List<Detail>>()

    fun sendComments(comment: String, uniName: String, userName: String,bolumName : String){
        detailRepository.sendComment(comment,uniName,userName,bolumName)
    }
    fun showDetails( uniName: String,bolumName : String){
        detailRepository.showDetails(uniName,bolumName)
    }

    override fun showDetail(commentList: ArrayList<Detail>) {
        detailList.value = commentList
    }

    override fun sendComment(comment: String, uniName: String, userName: String) {
        detail.value?.userName = userName
        detail.value?.comment = comment
    }
}