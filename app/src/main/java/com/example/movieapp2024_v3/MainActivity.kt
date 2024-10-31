package com.example.movieapp2024_v3

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp2024_v3.data.api.TheMovieDbClient
import com.example.movieapp2024_v3.data.repository.MovieRepository
import com.example.movieapp2024_v3.data.repository.MovieViewModelFactory
import com.example.movieapp2024_v3.ui.main.MovieAdapter
import com.example.movieapp2024_v3.ui.main.MovieViewModel
import androidx.activity.viewModels // For Activity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp2024_v3.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var binding: ActivityMainBinding

    private val movieViewModel: MovieViewModel by viewModels {
        MovieViewModelFactory(MovieRepository(TheMovieDbClient.create()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieAdapter = MovieAdapter(this)

        setupRecyclerView()
        setupSpinner()

        val gridLayoutManager = GridLayoutManager(this, 2)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val viewType = movieAdapter.getItemViewType(position)
                if (viewType == movieAdapter.itemCount) return 1
                else return 2
            }
        }

        //binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = gridLayoutManager
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = movieAdapter

        movieViewModel.movies.observe(this, { movies ->
            movieAdapter.setMovies(movies)
        })

        movieViewModel.fetchMovies()
    }

    private fun setupRecyclerView() {

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                if (layoutManager.findLastCompletelyVisibleItemPosition() == movieAdapter.itemCount - 1) {
                    movieViewModel.fetchMovies() // Load next page
                    Toast.makeText(baseContext, "Update Page", Toast.LENGTH_SHORT).show()

                }
            }
        })

        movieViewModel.movies.observe(this, { movies ->
            movieAdapter.setMovies(movies)
        })
    }

    private fun setupSpinner() {
        val options = arrayOf("Release Date", "Alphabetical", "Rating")
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.sortSpinner.adapter = adapter

        // Set the default selection to "Release Date"
        binding.sortSpinner.setSelection(0)

        binding.sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when (position) {
                    0 -> sortMoviesByReleaseDate()
                    1 -> sortMoviesAlphabetically()
                    2 -> sortMoviesByRating()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }

    private fun sortMoviesByReleaseDate() {
        movieAdapter.sortMoviesByReleaseDate() // Implement this in your adapter
    }

    private fun sortMoviesAlphabetically() {
        movieAdapter.sortMoviesAlphabetically() // Implement this in your adapter
    }

    private fun sortMoviesByRating() {
        movieAdapter.sortMoviesByRating() // Implement this in your adapter
    }
}