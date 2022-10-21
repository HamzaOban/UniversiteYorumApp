package com.pandapp.preferenceapp.ui.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.pandapp.preferenceapp.R
import com.pandapp.preferenceapp.databinding.FragmentLoginBinding
import com.pandapp.preferenceapp.databinding.FragmentSlideshowBinding

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
            viewModel.loginAuth(binding.LoginEmailET.text.toString(),binding.LoginPasswordET.text.toString(), view)
        }
        binding.backToRegistrationTV.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

}