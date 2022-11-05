package com.pandapp.preferenceapp.repository

import android.util.Log
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.pandapp.preferenceapp.model.Comment

class CommentRepository(commentIRepo : CommentIRepository) {
    private var commentIRepository : CommentIRepository = commentIRepo
    private var commentList = ArrayList<Comment>()

    fun getCommentsList(userName : String){
        commentList.clear()
        FirebaseDatabase.getInstance().getReference("comment").addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                snapshot.children.forEach {
                    it.children.forEach { it2->
                        if (it2.child("userName").value.toString() == userName){
                            val comment = it2.child("comment").value.toString()
                            val uniName = snapshot.ref.key.toString()
                            val bolumName = it.ref.key.toString()
                            var comments = Comment(uniName,bolumName,comment)
                            commentList.add(comments)
                            commentIRepository.getCommentList(commentList)
                        }
                    }
                }


            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                snapshot.children.forEach {
                    it.children.forEach { it2->
                        if (it2.child("userName").value.toString() == userName){
                            val comment = it2.child("comment").value.toString()
                            val uniName = snapshot.ref.key.toString()
                            val bolumName = it.ref.key.toString()
                            var comments = Comment(uniName,bolumName,comment)
                            commentList.add(comments)
                            commentIRepository.getCommentList(commentList)
                        }
                    }
                }
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    it.children.forEach { it2->
                        if (it2.child("userName").value.toString() == userName){
                            val comment = it2.child("comment").value.toString()
                            val uniName = snapshot.ref.key.toString()
                            val bolumName = it.ref.key.toString()
                            var comments = Comment(uniName,bolumName,comment)
                            commentList.add(comments)
                            commentIRepository.getCommentList(commentList)
                        }
                    }
                }
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}