package com.pandapp.preferenceapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.pandapp.preferenceapp.R
import com.pandapp.preferenceapp.ui.uni.UniversityFragmentDirections

class DetailRecyclerViewAdapter(private val commentList : ArrayList<String>) : RecyclerView.Adapter<DetailRecyclerViewAdapter.RecyclerViewHolder>() {

    class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val userName = itemView.findViewById<TextView>(R.id.user_name_tv)
        val comment = itemView.findViewById<TextView>(R.id.detail_comment_tv)
    }


    @SuppressLint("NotifyDataSetChanged")
    fun universityNameUpdate(newCommentList : List<String>){
        commentList.clear()
        commentList.addAll(newCommentList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment_detail,parent,false)
        return DetailRecyclerViewAdapter.RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.userName.text = commentList[position]
        holder.comment.text = commentList[position]

    }

    override fun getItemCount(): Int {
        return commentList.size
    }

}