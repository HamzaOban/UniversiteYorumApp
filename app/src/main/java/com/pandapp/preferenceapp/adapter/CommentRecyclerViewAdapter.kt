package com.pandapp.preferenceapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pandapp.preferenceapp.R
import com.pandapp.preferenceapp.model.Comment

class CommentRecyclerViewAdapter(private val commentsList : ArrayList<Comment>)  : RecyclerView.Adapter<CommentRecyclerViewAdapter.RecyclerViewHolder>(){

    class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val comment = itemView.findViewById<TextView>(R.id.comment)
        val commentUniName = itemView.findViewById<TextView>(R.id.comment_uni_name)
        val commentBolumName = itemView.findViewById<TextView>(R.id.comment_bolum_name)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment_list,parent,false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RecyclerViewHolder,
        position: Int
    ) {
        holder.comment.text = commentsList[position].comment
        holder.commentUniName.text = commentsList[position].uniName
        holder.commentBolumName.text = commentsList[position].bolumName
    }

    override fun getItemCount(): Int {
        return commentsList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun commentsListUpdate(newCommentsList : List<Comment>){
        commentsList.clear()
        commentsList.addAll(newCommentsList)
        notifyDataSetChanged()
    }
}
