package com.example.movieapp2024_v3.data.repository

import com.example.movieapp2024_v3.data.api.TheMovieDbInterface
import com.example.movieapp2024_v3.data.vo.MovieDetails
import io.reactivex.Single

class MDetailRepository(private val service: TheMovieDbInterface) {
    fun getDetailMovie(movieId: Int) : Single<MovieDetails> {
        return service.getMovieDetails(movieId)
    }
}