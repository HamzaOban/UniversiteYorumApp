package com.pandapp.preferenceapp.ui.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pandapp.preferenceapp.R
import com.pandapp.preferenceapp.databinding.FragmentLoginBinding
import com.pandapp.preferenceapp.databinding.FragmentRegisterBinding
import com.pandapp.preferenceapp.ui.auth.login.LoginFragmentDirections
import com.pandapp.preferenceapp.ui.auth.login.LoginViewModel

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
}