package com.pandapp.preferenceapp.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.pandapp.preferenceapp.R
import com.pandapp.preferenceapp.ui.degree.DegreeFragment
import com.pandapp.preferenceapp.ui.degree.DegreeFragmentDirections
import java.util.*
import kotlin.collections.ArrayList

class DegreeRecyclerViewAdapter(private val degreeNameList : ArrayList<String>) : RecyclerView.Adapter<DegreeRecyclerViewAdapter.RecyclerViewHolder>(), Filterable {
    private var degreeNameFilterList = kotlin.collections.ArrayList<String>()
    init {
        degreeNameFilterList = degreeNameList
    }

    private val degreeFragment = DegreeFragment()
    class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val uniName = itemView.findViewById<TextView>(R.id.uni_name_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_university_name,parent,false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.uniName.text = degreeNameFilterList[position]
        holder.itemView.setOnClickListener {
            Log.d("views",DegreeFragment.uniName.toString())
            val action = DegreeFragmentDirections.actionNavBolumToDetailFragment(DegreeFragment.uniName.toString(),degreeNameFilterList[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return degreeNameFilterList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun universityNameUpdate(newDegreeNameList : List<String>){
        degreeNameFilterList.clear()
        degreeNameFilterList.addAll(newDegreeNameList)

        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    degreeNameFilterList = degreeNameList
                } else {
                    val resultList = ArrayList<String>()
                    for (row in degreeNameList) {
                        if (row.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    degreeNameFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = degreeNameFilterList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                degreeNameFilterList = results?.values as ArrayList<String>
                notifyDataSetChanged()
            }

        }
    }
}