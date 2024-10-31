package com.example.movieapp2024_v3.data.repository

import android.util.Log
import com.example.movieapp2024_v3.data.api.TheMovieDbInterface
import com.example.movieapp2024_v3.data.vo.MovieResponse
import io.reactivex.Single

class MovieRepository(private val service: TheMovieDbInterface) {
    fun getPopularMovies(releaseDate: String, sortBy: String, page: Int, adult: Boolean) : Single<MovieResponse> {
        Log.d("masukUrl", "https://api.themoviedb.org/3/movie/popular?api_key=primary_release_date.lte=$releaseDate&sort_by=$sortBy&page=$page")
        return service.getPopularMovies( releaseDate, sortBy , page, adult)
    }
}