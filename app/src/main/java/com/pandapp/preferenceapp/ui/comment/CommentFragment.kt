package com.pandapp.preferenceapp.ui.comment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pandapp.preferenceapp.R
import com.pandapp.preferenceapp.databinding.FragmentCommentBinding

class CommentFragment : Fragment() {
    private val viewModel : CommentViewModel by viewModels()
    private lateinit var binding : FragmentCommentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCommentList("Hamza Oban")
    }
}