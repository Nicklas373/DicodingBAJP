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
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieDetailEntity
import com.dicoding.moviecatalog.databinding.ItemsMovieBinding
import com.dicoding.moviecatalog.utils.InlineVariable
import com.squareup.picasso.Picasso

class MovieFavAdapter :
    PagedListAdapter<MovieDetailEntity, MovieFavAdapter.MovieViewHolder>(DIFF_CALLBACK) {
    private var listFavMovies = ArrayList<MovieDetailEntity>()

    fun setMovies(movies: List<MovieDetailEntity>?) {
        if (movies == null) return
        this.listFavMovies.clear()
        this.listFavMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMovieBinding =
            ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieList = listFavMovies[position]
        holder.bind(movieList)
    }

    override fun getItemCount(): Int = listFavMovies.size

    class MovieViewHolder(private val binding: ItemsMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val inlineVariable = InlineVariable()

        fun bind(movie: MovieDetailEntity) {
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
                Picasso.get()
                    .load(movieImage)
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
                    .into(imgPoster)
            }
        }
    }

    fun getSwipedData(swipedPosition: Int): MovieDetailEntity? = getItem(swipedPosition)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieDetailEntity>() {
            override fun areItemsTheSame(
                oldItem: MovieDetailEntity,
                newItem: MovieDetailEntity
            ): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(
                oldItem: MovieDetailEntity,
                newItem: MovieDetailEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}