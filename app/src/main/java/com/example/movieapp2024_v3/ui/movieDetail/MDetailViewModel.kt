package com.example.movieapp2024_v3.ui.movieDetail

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp2024_v3.data.repository.MDetailRepository
import com.example.movieapp2024_v3.data.vo.MovieDetails
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MDetailViewModel(private val repository: MDetailRepository) : ViewModel() {


    private val _movieDetail = MutableLiveData<MovieDetails>()
    val movieDetail: LiveData<MovieDetails>
        get() = _movieDetail


    @SuppressLint("CheckResult")
    fun fetchDetail(movidId: Int) {

        repository.getDetailMovie(movidId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                _movieDetail.postValue(response)

            },{
                Log.e("MovieDetailsDataSource", it.message.toString())
            })
    }

}