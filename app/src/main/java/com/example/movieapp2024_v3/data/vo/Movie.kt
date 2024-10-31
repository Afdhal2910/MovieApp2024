package com.example.movieapp2024_v3.data.vo

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String,
    @SerializedName("popularity")
    val voteAverage: String,
    val overview: String,


)