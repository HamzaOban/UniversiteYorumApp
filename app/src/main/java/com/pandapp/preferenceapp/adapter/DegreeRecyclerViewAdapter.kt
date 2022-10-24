package com.pandapp.preferenceapp.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.pandapp.preferenceapp.R
import com.pandapp.preferenceapp.ui.degree.DegreeFragment
import com.pandapp.preferenceapp.ui.degree.DegreeFragmentDirections
import com.pandapp.preferenceapp.ui.uni.UniversityFragmentDirections

class DegreeRecyclerViewAdapter(private val degreeNameList : ArrayList<String>) : RecyclerView.Adapter<DegreeRecyclerViewAdapter.RecyclerViewHolder>() {
    private val degreeFragment = DegreeFragment()
    class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val uniName = itemView.findViewById<TextView>(R.id.uni_name_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_university_name,parent,false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.uniName.text = degreeNameList[position]
        holder.itemView.setOnClickListener {
            Log.d("views",DegreeFragment.uniName.toString())
            val action = DegreeFragmentDirections.actionNavBolumToDetailFragment(DegreeFragment.uniName.toString(),degreeNameList[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return degreeNameList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun universityNameUpdate(newDegreeNameList : List<String>){
        degreeNameList.clear()
        degreeNameList.addAll(newDegreeNameList)

        notifyDataSetChanged()
    }
}