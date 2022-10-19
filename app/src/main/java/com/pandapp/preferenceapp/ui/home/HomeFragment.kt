package com.pandapp.preferenceapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pandapp.preferenceapp.adapter.RecyclerViewAdapter
import com.pandapp.preferenceapp.databinding.FragmentHomeBinding
import com.pandapp.preferenceapp.model.Preference

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val viewModel : HomeViewModel by viewModels()
    var universityNameList = ArrayList<String>()
    private lateinit var adapter : RecyclerViewAdapter


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
        viewModel.getAllUniversityName()
        viewModel.preference.observe(viewLifecycleOwner, Observer {
            adapter.universityNameUpdate(it)
        })

        adapter = RecyclerViewAdapter(universityNameList)
        _binding?.recyclerView?.adapter = adapter



    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}