package com.pandapp.preferenceapp.ui.uni

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.pandapp.preferenceapp.R
import com.pandapp.preferenceapp.adapter.UniversityRecyclerViewAdapter
import com.pandapp.preferenceapp.databinding.FragmentHomeBinding
import com.pandapp.preferenceapp.util.appUtil

class UniversityFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val viewModel : UniversityViewModel by viewModels()
    var universityNameList = ArrayList<String>()
    private lateinit var adapter : UniversityRecyclerViewAdapter


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
        appUtil.getUserName()
        _binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
        viewModel.getAllUniversityName()

        viewModel.preference.observe(viewLifecycleOwner, Observer {
            adapter.universityNameUpdate(it)
        })
        viewModel.isLoaded.observe(viewLifecycleOwner, Observer {
            if (it){
                binding.uniProgressBar.visibility = View.VISIBLE
            }
            else{
                binding.uniProgressBar.visibility = View.GONE
            }
        })
        adapter = UniversityRecyclerViewAdapter(universityNameList)
        searchViewOnQuery()
        _binding?.recyclerView?.adapter = adapter



    }
    private fun searchViewOnQuery(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                adapter.filter.filter(p0)
                return false
            }

        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}