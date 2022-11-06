package com.pandapp.preferenceapp.ui.comment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pandapp.preferenceapp.R
import com.pandapp.preferenceapp.adapter.CommentRecyclerViewAdapter
import com.pandapp.preferenceapp.adapter.DegreeRecyclerViewAdapter
import com.pandapp.preferenceapp.databinding.FragmentCommentBinding
import com.pandapp.preferenceapp.model.Comment
import com.pandapp.preferenceapp.util.appUtil

class CommentFragment : Fragment() {
    private val viewModel : CommentViewModel by viewModels()
    private lateinit var adapter : CommentRecyclerViewAdapter
    private var commentsList = ArrayList<Comment>()
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
        binding.commentRecyclerView.layoutManager = LinearLayoutManager(context)
        appUtil.getUserName()
        viewModel.getCommentList(appUtil.userName)
        Log.d("Selammm",appUtil.userName)
        viewModel.commentLists.observe(viewLifecycleOwner, Observer {
            if (it != null){
                adapter.commentsListUpdate(it)
            }
        })
        adapter = CommentRecyclerViewAdapter(commentsList)
        binding.commentRecyclerView.adapter = adapter
    }
}