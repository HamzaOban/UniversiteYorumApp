package com.pandapp.preferenceapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pandapp.preferenceapp.R
import com.pandapp.preferenceapp.databinding.FragmentDetailBinding
import com.pandapp.preferenceapp.util.appUtil

class DetailFragment : Fragment() {

    private lateinit var binding : FragmentDetailBinding
    private val viewModel : DetailViewModel by viewModels()
    var bolumName : String ?= ""
    var uniName : String ?= ""
    private val appUtil = appUtil()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments.let {
            bolumName = it?.let { it1 -> DetailFragmentArgs.fromBundle(it1).bolumName }
            uniName = it?.let { it1 -> DetailFragmentArgs.fromBundle(it1).uniName }
            binding.bolumNameDetailTv.text = bolumName
            binding.uniNameDetailTv.text = uniName
        }
        binding.button2.setOnClickListener {
            viewModel.sendComments(binding.textCommentEditText.text.toString(),
                uniName.toString(),appUtil.getUserName())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

}