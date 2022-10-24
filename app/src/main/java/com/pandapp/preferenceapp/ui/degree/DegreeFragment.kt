package com.pandapp.preferenceapp.ui.degree

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pandapp.preferenceapp.adapter.DegreeRecyclerViewAdapter
import com.pandapp.preferenceapp.adapter.UniversityRecyclerViewAdapter
import com.pandapp.preferenceapp.databinding.FragmentGalleryBinding
import com.pandapp.preferenceapp.ui.uni.UniversityViewModel

class DegreeFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val viewModel : DegreeViewModel by viewModels()
    var degreeNameList = ArrayList<String>()
    private lateinit var adapter : DegreeRecyclerViewAdapter

    private val binding get() = _binding!!
    companion object{
        var uniName : String ?= ""
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        arguments.let {
            uniName = it?.let { it1 -> DegreeFragmentArgs.fromBundle(it1).uniName }
            Log.d("uniName", uniName.toString())
        }


        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.recyclerView?.layoutManager = LinearLayoutManager(context)

        if (uniName != ""){
            Log.d("Selam","true")
            viewModel.getAllDegreeList(uniName!!)

        }
        else{
            Log.d("Selam","false")
            viewModel.getAllDegreeList()
        }


        viewModel.degreeLists.observe(viewLifecycleOwner, Observer {
            adapter.universityNameUpdate(it)
        })

        adapter = DegreeRecyclerViewAdapter(degreeNameList)
        _binding?.recyclerView?.adapter = adapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}