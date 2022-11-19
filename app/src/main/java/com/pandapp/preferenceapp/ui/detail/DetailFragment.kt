package com.pandapp.preferenceapp.ui.detail

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pandapp.preferenceapp.adapter.DetailRecyclerViewAdapter
import com.pandapp.preferenceapp.databinding.FragmentDetailBinding
import com.pandapp.preferenceapp.model.Detail
import com.pandapp.preferenceapp.model.Rate
import com.pandapp.preferenceapp.util.appUtil
import kotlin.math.round


class DetailFragment : Fragment() {

    private lateinit var binding : FragmentDetailBinding
    private lateinit var adapter : DetailRecyclerViewAdapter
    var detailList = ArrayList<Detail>()
    var rateLists = ArrayList<Rate>()
    val rateList : ArrayList<Double> = arrayListOf()
    val viewModel : DetailViewModel by viewModels()
    var bolumName : String ?= ""
    var uniName : String ?= ""
    var rate : Double ?= 0.0

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.yorumlarRecyclerViewTv.layoutManager = LinearLayoutManager(context)

        adapter = DetailRecyclerViewAdapter(detailList,rateLists)
        val verticalDividers = DividerItemDecoration(context,DividerItemDecoration.VERTICAL)
        verticalDividers.setDrawable(resources.getDrawable(com.pandapp.preferenceapp.R.drawable.vertical_divider))
        appUtil.getUserName()


        binding.yorumlarRecyclerViewTv.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        binding.yorumlarRecyclerViewTv.adapter = adapter


        arguments.let {
            bolumName = it?.let { it1 -> DetailFragmentArgs.fromBundle(it1).bolumName }
            uniName = it?.let { it1 -> DetailFragmentArgs.fromBundle(it1).uniName }
            binding.bolumNameDetailTv.text = bolumName
            binding.uniNameDetailTv.text = uniName
        }
        //GÖnder butonuna tıklandığında olacak şeyler
        binding.button2.setOnClickListener {
            if (rate == 0.0){
                Toast.makeText(view.context,"Lütfen puanlama yapınız.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (binding.textCommentEditText.text?.isEmpty() == true){
                Toast.makeText(view.context,"Lütfen yorum yazınız.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val dialog = AlertDialog.Builder(context)
            dialog.setTitle("Puanlama ve Yorum Yapmak İstiyor Musun?")
            dialog.setMessage("${binding.bolumNameDetailTv.text} Bolumune $rate Puan vermek ve ${binding.textCommentEditText.text} yorumunu yapmak istiyor musun?")
            dialog.setPositiveButton("Evet",object  : DialogInterface.OnClickListener{
                @SuppressLint("NotifyDataSetChanged")
                override fun onClick(p00: DialogInterface?, p1: Int) {
                    val rate = rate?.let { it1 ->
                            val ratemodel = Rate(uniName.toString(),bolumName.toString(),
                                it1,appUtil.userName)
                            viewModel.rateIts(ratemodel)
                            viewModel.showRates(Rate(uniName.toString(),bolumName.toString(),
                                0.0,appUtil.userName))
                        }

                    binding.detailRatingBar.rating = rateList.average().toFloat()
                    viewModel.sendComments(binding.textCommentEditText.text.toString(),
                        uniName.toString(), appUtil.userName ,bolumName.toString(),binding.textCommentEditText)
                    adapter.notifyDataSetChanged()

                    viewModel.isSuccess(Rate(uniName.toString(),bolumName.toString(),0.0,appUtil.userName))

                    viewModel.isSuccess.observe(viewLifecycleOwner, Observer {
                        if (it){
                            binding.textInputLayout.visibility = View.GONE
                            binding.detailRatingBarShow.visibility = View.GONE
                            binding.button2.visibility = View.GONE
                        }
                    })
                }
            })
            dialog.setNegativeButton("Hayır",object  : DialogInterface.OnClickListener{
                override fun onClick(p00: DialogInterface?, p1: Int) {
                    binding.detailRatingBar.rating = rateList.average().toFloat()
                }
            })
            dialog.show()

        }

        //Kullanıcının verdiği puanı anlık olarak kaydediyoruz.
        binding.detailRatingBarShow.setOnRatingBarChangeListener { ratingBar, fl, b ->
            rate = fl.toDouble()
        }
        viewModel.showRates(Rate(uniName.toString(),bolumName.toString(),0.0,appUtil.userName))
        //yorumları liste şeklinde gözlemlediğimiz yer.
        viewModel.detailList.observe(viewLifecycleOwner, Observer {comment->


                viewModel.rateList.observe(viewLifecycleOwner, Observer {rate->
                    if (rate != null && comment != null){
                        binding.detailInformationTv.text = ""
                        adapter.detailListUpdate(comment)
                        adapter.detailListUpdate(rate)
                    }
                })


        })

        viewModel.showRates(Rate(uniName.toString(),bolumName.toString(),0.0,appUtil.userName))

        binding.detailRatingBar.setOnRatingBarChangeListener { p0, p1, p2 ->
            if (p2){
                //popup ekranı çıkar evete basarsa
                viewModel.showRates(Rate(uniName.toString(),bolumName.toString(),0.0,appUtil.userName))
                p0.rating = rateList.average().toFloat()
                }
        }


        bolumName?.let { uniName?.let { it1 -> viewModel.showDetails(it1, it) } }


        viewModel.isEmpty.observe(viewLifecycleOwner, Observer {
            if (it){
                binding.detailProgressBar.visibility = View.GONE
                binding.yorumlarRecyclerViewTv.visibility = View.VISIBLE
                binding.detailInformationTv.text = "Yorum yapılmadı..."
            }
            else{
                Log.d("comment","isNotEmpty")
                binding.yorumlarRecyclerViewTv.visibility = View.VISIBLE
                binding.detailInformationTv.text = ""
            }
        })
        viewModel.rateList.observe(viewLifecycleOwner, Observer {

            rateList.clear()
            it.forEach {
                rateList.add(it.rate)
            }

            val average = rateList.average()

            if (average.isNaN()){
                binding.ratingScoreTv.text = 0.0.toString()
                binding.detailRatingBar.rating = 0.0.toFloat()
                Log.d("selam2","true")
            }
            else{
                val round = round(average * 100) / 100
                binding.ratingScoreTv.text = round.toString()
                binding.detailRatingBar.rating = average.toFloat()
                Log.d("selam2","false")

            }

        })
        viewModel.isSuccess(Rate(uniName.toString(),bolumName.toString(),0.0,appUtil.userName))
        viewModel.isSuccess.observe(viewLifecycleOwner, Observer {
            if (it){
                binding.textInputLayout.visibility = View.GONE
                binding.detailRatingBarShow.visibility = View.GONE
                binding.button2.visibility = View.GONE
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