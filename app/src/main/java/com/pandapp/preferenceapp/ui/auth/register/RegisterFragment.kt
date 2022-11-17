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
import com.pandapp.preferenceapp.ui.auth.login.LoginFragment
import com.pandapp.preferenceapp.ui.auth.login.LoginFragmentDirections

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
            val action = RegisterFragmentDirections.actionRegisterFragmentToNavHome()
            view?.let { Navigation.findNavController(it).navigate(action) }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerbtn.setOnClickListener {
            if (binding.RegisterUserNameET.text.isEmpty()){
                binding.RegisterUserNameET.error = "Lütfen Kullanıcı Adınızı Giriniz."
                return@setOnClickListener
            }
            if (binding.RegisterEmailET.text.isEmpty()){
                binding.RegisterEmailET.error = "Lütfen Email Giriniz."
                return@setOnClickListener
            }
            if (binding.RegisterPasswordET.text.isEmpty()){
                binding.RegisterPasswordET.error = "Lütfen Şifrenizi Giriniz."
                return@setOnClickListener
            }
            viewModel.registerAuth(binding.RegisterUserNameET.text.toString(),
                binding.RegisterEmailET.text.toString(),
                binding.RegisterPasswordET.text.toString(),
            view)
        }
        
        viewModel.isSuccessValue.observe(viewLifecycleOwner, Observer {
            if (it){
                val action = RegisterFragmentDirections.actionRegisterFragmentToNavHome()
                Navigation.findNavController(view).navigate(action)
            }
        })
        binding.alreadyAccountTV.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            Navigation.findNavController(view).navigate(action)
        }

    }
}