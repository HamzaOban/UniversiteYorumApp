package com.pandapp.preferenceapp.ui.detail

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pandapp.preferenceapp.MainActivity
import com.pandapp.preferenceapp.R
import com.pandapp.preferenceapp.adapter.DetailRecyclerViewAdapter
import com.pandapp.preferenceapp.adapter.UniversityRecyclerViewAdapter
import com.pandapp.preferenceapp.databinding.FragmentDetailBinding
import com.pandapp.preferenceapp.model.Detail
import com.pandapp.preferenceapp.model.Rate
import com.pandapp.preferenceapp.util.appUtil

class DetailFragment : Fragment() {

    private lateinit var binding : FragmentDetailBinding
    private lateinit var adapter : DetailRecyclerViewAdapter
    var detailList = ArrayList<Detail>()
    private val viewModel : DetailViewModel by viewModels()
    var bolumName : String ?= ""
    var uniName : String ?= ""


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
                uniName.toString(), appUtil.userName ,bolumName.toString(),binding.textCommentEditText)
        }
        viewModel.showDetails(uniName.toString(),bolumName.toString())
        viewModel.detailList.observe(viewLifecycleOwner, Observer {
            if (it != null){
                adapter.detailListUpdate(it)
            }
        })

        binding.detailRatingBar.setOnRatingBarChangeListener { ratingBar, fl, b ->
            if (b){
                //popup ekranı çıkar evete basarsa
                val dialog = AlertDialog.Builder(this.context)
                dialog.setTitle("Puanlama yapmak istiyor musun?")
                dialog.setMessage("${binding.bolumNameDetailTv.text} Bolumune $fl Puan vermek istiyor musun?")
                dialog.setPositiveButton("Evet",object  : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        val rate = Rate(uniName.toString(),bolumName.toString(),fl.toDouble(),appUtil.userName)

                        viewModel.rateIts(rate)
                    }

                })
                dialog.setNegativeButton("Hayır",object  : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {

                    }

                })
                dialog.show()
            }

        }
        viewModel.showRates(Rate(uniName.toString(),bolumName.toString(),0.0,appUtil.userName))

        viewModel.rateList.observe(viewLifecycleOwner, Observer {
            val average = it.average()
            Log.d("comment2",average.toString())


            if (average.isNaN()){
                binding.ratingScoreTv.text = 0.0.toString()
                binding.detailRatingBar.rating = 0.0.toFloat()
                Log.d("selam2","true")
            }
            else{
                binding.ratingScoreTv.text = average.toString()
                binding.detailRatingBar.rating = average.toFloat()
                Log.d("selam2","false")

            }

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