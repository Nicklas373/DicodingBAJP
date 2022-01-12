package com.dicoding.moviecatalog.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.activity.DetailShowActivity
import com.dicoding.moviecatalog.data.movie.response.MovieListResponse
import com.dicoding.moviecatalog.databinding.ItemsMovieBinding

class MovieApiAdapter(private val listMovieApi: ArrayList<MovieListResponse>) :
    RecyclerView.Adapter<MovieApiAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemsMovieBinding.bind(itemView)
    }

    override fun getItemCount(): Int = listMovieApi.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.items_movie, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        with(holder) {
            val (movieId, movieOverview, movieOriginalTitle, movieReleaseDate, movieVoteAverage, movieTitle, moviePosterPath) = listMovieApi[position]
            val movieImage =
                itemView.context.getString(R.string.movieDb_static_image) + moviePosterPath
            binding.movieTitle.text = movieOriginalTitle
            binding.movieReleaseDate.text = movieReleaseDate
            binding.movieRatingText.text = movieVoteAverage.toString()
            binding.separateRatingText.visibility = View.GONE
            binding.movieDurationText.visibility = View.GONE
            Glide.with(itemView.context)
                .load(movieImage)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(binding.imgPoster)
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailShowActivity::class.java)
                val index = position + 1
                intent.putExtra(DetailShowActivity.SHOW_ID, "Movie")
                intent.putExtra(DetailShowActivity.EXTRA_MOVIE, index.toString())
                itemView.context.startActivity(intent)
            }
        }
    }
}