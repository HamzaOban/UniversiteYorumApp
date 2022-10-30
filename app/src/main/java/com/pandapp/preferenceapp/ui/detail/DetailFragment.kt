package com.pandapp.preferenceapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pandapp.preferenceapp.R
import com.pandapp.preferenceapp.adapter.DetailRecyclerViewAdapter
import com.pandapp.preferenceapp.adapter.UniversityRecyclerViewAdapter
import com.pandapp.preferenceapp.databinding.FragmentDetailBinding
import com.pandapp.preferenceapp.model.Detail
import com.pandapp.preferenceapp.util.appUtil

class DetailFragment : Fragment() {

    private lateinit var binding : FragmentDetailBinding
    private lateinit var adapter : DetailRecyclerViewAdapter
    var detailList = ArrayList<Detail>()
    private val viewModel : DetailViewModel by viewModels()
    var bolumName : String ?= ""
    var uniName : String ?= ""
    private val appUtil = appUtil()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.yorumlarRecyclerViewTv.layoutManager = LinearLayoutManager(context)

        adapter = DetailRecyclerViewAdapter(detailList)
        binding.yorumlarRecyclerViewTv.adapter = adapter

        arguments.let {
            bolumName = it?.let { it1 -> DetailFragmentArgs.fromBundle(it1).bolumName }
            uniName = it?.let { it1 -> DetailFragmentArgs.fromBundle(it1).uniName }
            binding.bolumNameDetailTv.text = bolumName
            binding.uniNameDetailTv.text = uniName
        }
        binding.button2.setOnClickListener {
            viewModel.sendComments(binding.textCommentEditText.text.toString(),
                uniName.toString(),"hAMZA",bolumName.toString())
        }
        viewModel.showDetails(uniName.toString(),bolumName.toString())
        viewModel.detailList.observe(viewLifecycleOwner, Observer {
            if (it != null)
            adapter.detailListUpdate(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

}