package com.dicoding.moviecatalog.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.activity.DetailShowActivity
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieListEntity
import com.dicoding.moviecatalog.databinding.ItemsMovieBinding
import com.dicoding.moviecatalog.utils.InlineVariable
import com.squareup.picasso.Picasso

class MovieAdapter :
    PagedListAdapter<MovieListEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {
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
                imgPoster.contentDescription = movieImage
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailShowActivity::class.java)
                    intent.putExtra(DetailShowActivity.SHOW_ID, "Movie")
                    intent.putExtra(DetailShowActivity.EXTRA_MOVIE, movie.movieId)
                    itemView.context.startActivity(intent)
                }
                Picasso.get()
                    .load(movieImage)
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
                    .into(imgPoster)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieListEntity>() {
            override fun areItemsTheSame(
                oldItem: MovieListEntity,
                newItem: MovieListEntity
            ): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(
                oldItem: MovieListEntity,
                newItem: MovieListEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}