package com.example.movieapp2024_v3.data.vo


import com.google.gson.annotations.SerializedName

//Synopsis
//Genres
//Language
//Duration
//Book the movie

data class MovieDetails(

    val overview: String,
    val genres: List<Genre>,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,
    val runtime: Int,

    @SerializedName("poster_path")
    val posterPath: String,
    val title: String,


    )