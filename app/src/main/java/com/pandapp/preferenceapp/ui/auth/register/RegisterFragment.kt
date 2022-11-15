package com.pandapp.preferenceapp.ui.auth.register

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pandapp.preferenceapp.R
import com.pandapp.preferenceapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private val viewModel : RegisterViewModel by viewModels()
    private lateinit var binding : FragmentRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root

    }

    override fun onStart() {
        super.onStart()
        if(Firebase.auth.currentUser != null){
            val action = RegisterFragmentDirections.actionRegisterFragmentToNavUni()
            view?.let { Navigation.findNavController(it).navigate(action) }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerbtn.setOnClickListener {
            viewModel.registerAuth(binding.RegisterUserNameET.text.toString(),
                binding.RegisterEmailET.text.toString(),
                binding.RegisterPasswordET.text.toString())
        }
        
        viewModel.isSuccessValue.observe(viewLifecycleOwner, Observer {
            if (it){
                val action = RegisterFragmentDirections.actionRegisterFragmentToNavUni()
                Navigation.findNavController(view).navigate(action)
            }
        })
        binding.alreadyAccountTV.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("Loglandın","31")
        activity?.actionBar?.setDisplayHomeAsUpEnabled(false)

    }

    override fun onDetach() {
        super.onDetach()
        Log.d("Loglandın","11")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Loglandın","21")
        activity?.actionBar?.setDisplayHomeAsUpEnabled(false)

    }
    fun onBackPressed(): Boolean {
        return false
    }
}