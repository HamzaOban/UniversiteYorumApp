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

class UniversityRecyclerViewAdapter(private val universityNameList : ArrayList<String>) : RecyclerView.Adapter<UniversityRecyclerViewAdapter.RecyclerViewHolder>() {

    class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val uniName = itemView.findViewById<TextView>(R.id.uni_name_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_university_name,parent,false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.uniName.text = universityNameList[position]
        holder.itemView.setOnClickListener {
            val action = UniversityFragmentDirections.actionNavHomeToNavSlideshow()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return universityNameList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun universityNameUpdate(newUniversityNameList : List<String>){
        universityNameList.clear()
        universityNameList.addAll(newUniversityNameList)

        notifyDataSetChanged()
    }
}