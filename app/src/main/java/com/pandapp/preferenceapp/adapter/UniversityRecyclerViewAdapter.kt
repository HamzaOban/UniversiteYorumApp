package com.pandapp.preferenceapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.pandapp.preferenceapp.R
import com.pandapp.preferenceapp.ui.uni.UniversityFragmentDirections
import java.util.*
import kotlin.collections.ArrayList

class UniversityRecyclerViewAdapter(private val universityNameList : ArrayList<String>) : RecyclerView.Adapter<UniversityRecyclerViewAdapter.RecyclerViewHolder>() , Filterable{
    private var universityNameFilterList = ArrayList<String>()
    init {
        universityNameFilterList = universityNameList
    }
    class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val uniName = itemView.findViewById<TextView>(R.id.uni_name_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_university_name,parent,false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.uniName.text = universityNameFilterList[position]
        holder.itemView.setOnClickListener {
            val action = UniversityFragmentDirections.actionNavHomeToNavGallery(universityNameFilterList[position].toString())
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return universityNameFilterList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun universityNameUpdate(newUniversityNameList : List<String>){
        universityNameFilterList.clear()
        universityNameFilterList.addAll(newUniversityNameList)

        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    universityNameFilterList = universityNameList
                } else {
                    val resultList = ArrayList<String>()
                    for (row in universityNameList) {
                        if (row.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    universityNameFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = universityNameFilterList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                universityNameFilterList = results?.values as ArrayList<String>
                notifyDataSetChanged()
            }

        }
    }
}