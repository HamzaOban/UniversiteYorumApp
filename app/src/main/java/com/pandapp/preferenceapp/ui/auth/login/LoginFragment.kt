package com.pandapp.preferenceapp.ui.auth.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.pandapp.preferenceapp.R
import com.pandapp.preferenceapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private val viewModel : LoginViewModel by viewModels()
    private lateinit var binding : FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment

        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginbtn.setOnClickListener {
            if (binding.LoginEmailET.text.isEmpty()){
                binding.LoginEmailET.error = "Lütfen Email Giriniz."
                return@setOnClickListener
            }
            if (binding.LoginPasswordET.text.isEmpty()){
                binding.LoginPasswordET.error = "Lütfen Şifrenizi Giriniz."
                return@setOnClickListener
            }
            viewModel.loginAuth(binding.LoginEmailET.text.toString(),binding.LoginPasswordET.text.toString(), view)
        }
        binding.backToRegistrationTV.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }

    fun onBackPressed() : Boolean {
        return false
    }
}