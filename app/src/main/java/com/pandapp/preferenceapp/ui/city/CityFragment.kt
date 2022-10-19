package com.pandapp.preferenceapp.ui.city

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
import com.pandapp.preferenceapp.adapter.CityRecyclerViewAdapter
import com.pandapp.preferenceapp.adapter.UniversityRecyclerViewAdapter
import com.pandapp.preferenceapp.databinding.FragmentSlideshowBinding
import com.pandapp.preferenceapp.ui.uni.UniversityViewModel

class CityFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null
    private val viewModel : CityViewModel by viewModels()
    var cityNameList = ArrayList<String>()
    private lateinit var adapter : CityRecyclerViewAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
        viewModel.getAllCityNameList()
        viewModel.cityLists.observe(viewLifecycleOwner, Observer {
            it.forEach {
                Log.d("Hata11",it)
            }
            adapter.universityNameUpdate(it)
        })

        adapter = CityRecyclerViewAdapter(cityNameList)
        _binding?.recyclerView?.adapter = adapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}