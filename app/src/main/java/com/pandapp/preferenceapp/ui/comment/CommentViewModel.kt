package com.pandapp.preferenceapp.ui.comment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pandapp.preferenceapp.model.Comment
import com.pandapp.preferenceapp.repository.CommentIRepository
import com.pandapp.preferenceapp.repository.CommentRepository
import com.pandapp.preferenceapp.repository.PreferenceRepository

class CommentViewModel : ViewModel(), CommentIRepository{
    val commentLists = MutableLiveData<List<Comment>>()
    val isLoaded = MutableLiveData<Boolean>()
    val isEmpty = MutableLiveData<Boolean>()
    private var commentRepository = CommentRepository(this)

    fun getCommentLists(userName : String){
        commentRepository.getCommentsList(userName)
    }

    override fun getCommentList(commentList: ArrayList<Comment>) {
        isLoaded.value = true
        if (commentList.isEmpty()){
            Log.d("comment","isEmpty")
            isLoaded.value = false
            isEmpty.value = true
        }
        else{
            isLoaded.value = false
            isEmpty.value = false
        }
        commentLists.value = commentList
    }
}