package com.example.movieapp2024_v3.data.api

import com.example.movieapp2024_v3.data.vo.MovieDetails
import com.example.movieapp2024_v3.data.vo.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDbInterface {

    @GET("discover/movie?")
    fun getPopularMovies(
        @Query("primary_release_year") releaseDate: String,
        @Query("sort_by") sortBy: String,
        @Query("page") page: Int,
        @Query("include_adult") adult: Boolean

    ): Single<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int
    ): Single<MovieDetails>
}