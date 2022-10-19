package com.pandapp.preferenceapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pandapp.preferenceapp.R

class CityRecyclerViewAdapter(private val cityNameList : ArrayList<String>)  : RecyclerView.Adapter<CityRecyclerViewAdapter.RecyclerViewHolder>(){

    class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val uniName = itemView.findViewById<TextView>(R.id.uni_name_tv)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_university_name,parent,false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RecyclerViewHolder,
        position: Int
    ) {
        holder.uniName.text = cityNameList[position]
    }

    override fun getItemCount(): Int {
        return cityNameList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun universityNameUpdate(newCityNameList : List<String>){
        cityNameList.clear()
        cityNameList.addAll(newCityNameList)
        notifyDataSetChanged()
    }
}