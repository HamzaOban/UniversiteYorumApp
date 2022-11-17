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
import com.google.firebase.auth.FirebaseAuth
import com.pandapp.preferenceapp.R
import com.pandapp.preferenceapp.databinding.FragmentLoginBinding
import com.pandapp.preferenceapp.ui.auth.register.RegisterFragment
import com.pandapp.preferenceapp.ui.auth.register.RegisterFragmentDirections

class LoginFragment : Fragment() {

    private val viewModel : LoginViewModel by viewModels()
    private lateinit var binding : FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseAuth.getInstance().signOut()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
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
            Navigation.findNavController(view).navigate(action)
        }

    }

    fun onBackPressed() : Boolean {
        return false
    }
}