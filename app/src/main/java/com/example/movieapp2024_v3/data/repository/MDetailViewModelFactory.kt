package com.example.movieapp2024_v3.data.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp2024_v3.ui.movieDetail.MDetailViewModel

class MDetailViewModelFactory(private val repository: MDetailRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(MDetailViewModel::class.java)) {
            return  MDetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown MDetailViewModel class")
    }
}