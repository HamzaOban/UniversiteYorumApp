package com.pandapp.preferenceapp.ui.uni

import android.annotation.SuppressLint
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.TextView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pandapp.preferenceapp.MainActivity
import com.pandapp.preferenceapp.R
import com.pandapp.preferenceapp.adapter.UniversityRecyclerViewAdapter
import com.pandapp.preferenceapp.databinding.ActivityMainBinding
import com.pandapp.preferenceapp.databinding.FragmentHomeBinding
import com.pandapp.preferenceapp.databinding.FragmentUniBinding
import com.pandapp.preferenceapp.model.User
import com.pandapp.preferenceapp.ui.auth.register.RegisterFragment
import com.pandapp.preferenceapp.util.appUtil

class UniversityFragment : Fragment() {

    private var _binding: FragmentUniBinding? = null
    private val viewModel : UniversityViewModel by viewModels()
    private val mainActivity : MainActivity = MainActivity()
    var universityNameList = ArrayList<String>()
    private lateinit var adapter : UniversityRecyclerViewAdapter

    companion object{
        var user : User = User()
    }
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUniBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserInfo()
        viewModel.userInfo.observe(viewLifecycleOwner, Observer {
            val activityAccess = activity?.findViewById<NavigationView>(R.id.nav_view)?.getHeaderView(0)
            activityAccess?.findViewById<TextView>(R.id.header_user_name)?.text = user.userName
            activityAccess?.findViewById<TextView>(R.id.header_email)?.text = user.email
        })



        appUtil.getUserName()
        viewModel.getUserInfo()
        viewModel.userInfo.observe(viewLifecycleOwner, Observer {
            user.userName = it.userName
            user.email = it.email

        })
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



    @Deprecated("Deprecated in Java", ReplaceWith(
        "super.onCreateOptionsMenu(menu, inflater)",
        "androidx.fragment.app.Fragment"
    )
    )
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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