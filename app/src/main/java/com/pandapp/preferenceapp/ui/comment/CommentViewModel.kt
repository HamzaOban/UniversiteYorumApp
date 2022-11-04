package com.pandapp.preferenceapp.ui.comment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pandapp.preferenceapp.model.Comment
import com.pandapp.preferenceapp.repository.CommentIRepository
import com.pandapp.preferenceapp.repository.CommentRepository
import com.pandapp.preferenceapp.repository.PreferenceRepository

class CommentViewModel : ViewModel(), CommentIRepository{
    val commentLists = MutableLiveData<List<Comment>>()
    private var commentRepository = CommentRepository(this)

    fun getCommentList(userName : String){
        commentRepository.getCommentsList(userName)
    }

    override fun getCommentList(commentList: ArrayList<Comment>) {
        commentLists.value = commentList
    }
}