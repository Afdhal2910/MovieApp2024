package com.example.movieapp2024_v3.ui.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp2024_v3.data.repository.MovieRepository
import com.example.movieapp2024_v3.data.vo.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movies = MutableLiveData<MutableList<Movie>>(mutableListOf())
    val movies: MutableLiveData<MutableList<Movie>> get() = _movies

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> get() = _loading
    val releaseDate = "2024"
    val sortBy = "title.asc"


    private var currentPage = 1
    private var isLoading = false

    // Fetch movies with pagination
    @SuppressLint("CheckResult")
    fun fetchMovies() {
        if (isLoading) return // Prevent multiple calls
        isLoading = true
        _loading.value = true // Show loading indicator

        Log.d("masuk1", currentPage.toString())
        repository.getPopularMovies(releaseDate, sortBy, currentPage, false)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                // Append new movies to the existing list
                _movies.value?.addAll(response.movieList)
                // Notify observers of the updated list
                _movies.value = _movies.value
                currentPage++ // Increment the page number
                isLoading = false
                _loading.value = false // Hide loading indicator
            }, { error ->
                // Handle error (e.g., log it, show a toast)
                isLoading = false
                _loading.value = false // Hide loading indicator
                // Optionally log the error or notify the UI
            })
    }
}