package com.pandapp.preferenceapp.repository

import com.pandapp.preferenceapp.model.Comment

interface CommentIRepository {
    fun getCommentList(commentList : ArrayList<Comment>)
}