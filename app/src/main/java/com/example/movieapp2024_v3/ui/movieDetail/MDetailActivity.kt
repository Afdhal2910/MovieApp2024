package com.example.movieapp2024_v3.ui.movieDetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.movieapp2024_v3.data.api.POSTER_BASE_URL
import com.example.movieapp2024_v3.data.api.TheMovieDbClient
import com.example.movieapp2024_v3.data.repository.MDetailRepository
import com.example.movieapp2024_v3.data.repository.MDetailViewModelFactory
import com.example.movieapp2024_v3.data.vo.Genre
import com.example.movieapp2024_v3.data.vo.MovieDetails
import com.example.movieapp2024_v3.data.vo.SpokenLanguage
import com.example.movieapp2024_v3.databinding.ActivityMdetailactivityBinding
import com.example.movieapp2024_v3.ui.WebViewActivity

class MDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMdetailactivityBinding

    private val mDetailViewModel: MDetailViewModel by viewModels {
        MDetailViewModelFactory(MDetailRepository(TheMovieDbClient.create()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMdetailactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bookBtn.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("EXTRA_URL", "https://www.cathaycineplexes.com.sg/") // Pass the URL
            startActivity(intent)
        }

        val movidId: Int = intent.getIntExtra("movie_id", 1)
        mDetailViewModel.movieDetail.observe(this, {
            bindUI(it)
        })

        mDetailViewModel.fetchDetail(movidId)
    }

    private fun bindUI(it: MovieDetails?) {
        if (it != null) {
            binding.movieTitle.text = it.title

            val genre = it.genres.joinToString(" , ") { genre: Genre -> genre.name }
            binding.movieGenres.text = genre

            val language = it.spokenLanguages.joinToString(", ") { spokenLanguage: SpokenLanguage -> spokenLanguage.name }
            binding.movieLanguage.text = language

            binding.movieDuration.text = it.runtime.toString() + " minutes"
            binding.movieOverview.text = it.overview

            val moviePosterURL = POSTER_BASE_URL + it.posterPath
            Glide.with(this)
                .load(moviePosterURL)
                .into(binding.ivMoviePoster);


        }
    }
}