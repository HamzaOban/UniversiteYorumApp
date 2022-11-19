package com.pandapp.preferenceapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.pandapp.preferenceapp.R
import com.pandapp.preferenceapp.model.Detail
import com.pandapp.preferenceapp.model.Rate
import com.pandapp.preferenceapp.ui.detail.DetailFragment
import com.pandapp.preferenceapp.ui.detail.DetailViewModel

class DetailRecyclerViewAdapter(private val detailList: ArrayList<Detail>, private val ratingList: ArrayList<Rate>) : RecyclerView.Adapter<DetailRecyclerViewAdapter.RecyclerViewHolder>() {
    val detailFragment : DetailFragment = DetailFragment()
    class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val userName = itemView.findViewById<TextView>(R.id.user_name_tv)
        val comment = itemView.findViewById<TextView>(R.id.detail_comment_tv)
        val rating = itemView.findViewById<RatingBar>(R.id.detail_rating_bar)
        val like = itemView.findViewById<ImageView>(R.id.detail_like_button)
        val dislike = itemView.findViewById<ImageView>(R.id.detail_dislike_button)

    }


    @SuppressLint("NotifyDataSetChanged")
    fun detailListUpdate(detailLists : List<Detail>){
        detailList.clear()
        detailList.addAll(detailLists)
        notifyDataSetChanged()
    }

    @JvmName("detailListUpdate1")
    @SuppressLint("NotifyDataSetChanged")
    fun detailListUpdate(rateLists : List<Rate>){
        ratingList.clear()
        ratingList.addAll(rateLists)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment_detail,parent,false)
        return DetailRecyclerViewAdapter.RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.userName.text = detailList[position].userName
        holder.comment.text = detailList[position].comment
        holder.rating.rating = ratingList[position].rate.toFloat()
        holder.like.setOnClickListener {
            detailFragment.viewModel.setLikes(Rate(ratingList[position].uniName,ratingList[position].bolumName,ratingList[position].rate,ratingList[position].userName))
        }
    }

    override fun getItemCount(): Int {
        return detailList.size
    }

}