package com.example.movieapp2024_v3.ui.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp2024_v3.R
import com.example.movieapp2024_v3.data.vo.Movie
import com.example.movieapp2024_v3.ui.movieDetail.MDetailActivity

class MovieAdapter(private val context: Context) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    //private var movieList: List<Movie> = listOf()
    private var movieList: MutableList<Movie> = mutableListOf() // Use MutableList for easy sorting


    fun setMovies(movies: List<Movie>) {
        this.movieList = movies.toMutableList()
        notifyDataSetChanged()
    }

    // Sort movies by release date
    fun sortMoviesByReleaseDate() {
        movieList.sortByDescending { it.releaseDate } // Ensure release_date is formatted correctly
        notifyDataSetChanged()
    }

    // Sort movies alphabetically by title
    fun sortMoviesAlphabetically() {
        movieList.sortByDescending { it.title }
        notifyDataSetChanged()
    }

    // Sort movies by rating
    fun sortMoviesByRating() {
        movieList.sortByDescending { it.voteAverage } // Assuming vote_average is a Double
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)

        holder.itemView.setOnClickListener {
            Toast.makeText(context, "Click ${movie.id}", Toast.LENGTH_SHORT).show()

            val intent = Intent(context, MDetailActivity::class.java)
            intent.putExtra("movie_id", movie.id) // Pass the movie title
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = movieList.size

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.movieTitle)
        private val rating: TextView = itemView.findViewById(R.id.movieRating)
        private val moviePoster: ImageView = itemView.findViewById(R.id.moviePoster)

        fun bind(movie: Movie) {
            title.text = movie.title
            val vote = movie.voteAverage.toDouble()
            rating.text = String.format("%.1f", vote)
            val imageUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
            Glide.with(itemView.context)
                .load(imageUrl)
                .into(moviePoster);
        }
    }
}