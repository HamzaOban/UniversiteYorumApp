package com.pandapp.preferenceapp.ui.detail

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pandapp.preferenceapp.model.Detail
import com.pandapp.preferenceapp.model.Dislike
import com.pandapp.preferenceapp.model.Like
import com.pandapp.preferenceapp.model.Rate
import com.pandapp.preferenceapp.repository.DetailIRepository
import com.pandapp.preferenceapp.repository.DetailRepository
import com.pandapp.preferenceapp.repository.PreferenceRepository

class DetailViewModel : ViewModel() , DetailIRepository{
    private var detailRepository = DetailRepository(this)
    val detail = MutableLiveData<Detail>()
    val rate = MutableLiveData<Rate>()
    val rateList = MutableLiveData<List<Rate>>()
    val detailList = MutableLiveData<List<Detail>>()
    val isLoaded = MutableLiveData<Boolean>()
    val isEmpty = MutableLiveData<Boolean>()
    val isSuccess = MutableLiveData<Boolean>()
    val like = MutableLiveData<Like>()

    fun setLikes(rate: Rate){
        detailRepository.setLikes(rate)
    }

    fun sendComments(comment: String, uniName: String, userName: String,bolumName : String, commentTextView: TextView){
        detailRepository.sendComment(comment,uniName,userName,bolumName,commentTextView)
    }
    fun showDetails( uniName: String,bolumName : String){
        detailRepository.showDetails(uniName,bolumName)
    }
    fun rateIts(rate: Rate){
        detailRepository.rateIt(rate.uniName,rate.bolumName,rate.rate,rate.userName)
    }
    fun showRates(rate: Rate){
        Log.d("Average",rate.rate.toString())
        detailRepository.showRate(rate)
    }
    fun isSuccess(rate: Rate){
        detailRepository.isSuccess(rate)
    }

    override fun showDetail(commentList: ArrayList<Detail>) {
        detailList.value = commentList
    }

    override fun sendComment(comment: String, uniName: String, userName: String) {
        detail.value?.userName = userName
        detail.value?.comment = comment
    }

    override fun rateIt(rate: Rate) {
        this.rate.value = rate
    }

    override fun showRate(rate: Rate, rateList: ArrayList<Rate>) {
        isLoaded.value = true

        if (rateList.isEmpty()){
            isLoaded.value = false
            isEmpty.value = true
            this.rateList.value = arrayListOf()
        }
        else{
            isLoaded.value = false
            isEmpty.value = false
            this.rateList.value = rateList
        }

    }

    override fun isSuccess(isSuccess: Boolean) {
        this.isSuccess.value = isSuccess
    }

    override fun getLikeList(likeList: ArrayList<Like>) {
        TODO("Not yet implemented")
    }

    override fun getDislikeList(dislikeList: ArrayList<Dislike>) {
        TODO("Not yet implemented")
    }

    override fun setLike(like: Like) {
        this.like.value = like
    }

    override fun setDislike(dislike: Dislike) {
        TODO("Not yet implemented")
    }

}