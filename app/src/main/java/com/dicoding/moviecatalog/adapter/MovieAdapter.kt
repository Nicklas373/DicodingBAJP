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
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieListEntity
import com.dicoding.moviecatalog.databinding.ItemsMovieBinding
import com.dicoding.moviecatalog.utils.InlineVariable

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listMovies = ArrayList<MovieListEntity>()

    fun setMovies(movies: List<MovieListEntity>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsAcademyBinding =
            ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsAcademyBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieList = listMovies[position]
        holder.bind(movieList)
    }

    override fun getItemCount(): Int = listMovies.size

    class MovieViewHolder(private val binding: ItemsMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val inlineVariable = InlineVariable()

        fun bind(movie: MovieListEntity) {
            with(binding) {
                val movieImage =
                    itemView.context.getString(R.string.movieDb_static_image) + movie.posterPath
                val releaseDate = inlineVariable.setReleaseDate(movie.releaseDate)

                movieTitle.text = movie.title
                movieReleaseDate.text = releaseDate
                movieRatingText.text = movie.voteAverage.toString()
                separateRatingText.visibility = View.GONE
                movieDurationText.visibility = View.GONE
                imgPoster.contentDescription = movie.posterPath
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailShowActivity::class.java)
                    intent.putExtra(DetailShowActivity.SHOW_ID, "Movie")
                    intent.putExtra(DetailShowActivity.EXTRA_MOVIE, movie.movieId)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(movieImage)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
            }
        }
    }
}