package com.pandapp.preferenceapp.repository

import com.pandapp.preferenceapp.model.Comment
import com.pandapp.preferenceapp.model.Dislike
import com.pandapp.preferenceapp.model.Like

interface CommentIRepository {
    fun getCommentList(commentList : ArrayList<Comment>)


}