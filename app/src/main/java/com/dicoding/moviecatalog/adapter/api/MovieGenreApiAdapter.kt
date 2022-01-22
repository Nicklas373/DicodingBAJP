package com.dicoding.moviecatalog.adapter.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.data.source.remote.response.movie.MovieGenreListResponse
import com.dicoding.moviecatalog.databinding.ItemsGenreApiBinding

class MovieGenreApiAdapter(private val listMovieApi: ArrayList<MovieGenreListResponse>) :
    RecyclerView.Adapter<MovieGenreApiAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemsGenreApiBinding.bind(itemView)
    }

    override fun getItemCount(): Int = listMovieApi.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.items_genre_api, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        with(holder) {
            val (movieId, movieName) = listMovieApi[position]
            binding.movieGenreText.text = movieName
            genreColoringApi(holder, movieName)
            if (movieName == science) {
                binding.movieGenreText.text = sciFi
            }
        }
    }

    private fun genreColoringApi(holder: ListViewHolder, genre: String) {
        with(holder) {
            when (genre) {
                drama -> binding.cvGenre.setCardBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.red
                    )
                )
                romance -> binding.cvGenre.setCardBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.red
                    )
                )
                action -> binding.cvGenre.setCardBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.blue
                    )
                )
                adventure -> binding.cvGenre.setCardBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.green
                    )
                )
                music -> binding.cvGenre.setCardBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.orange
                    )
                )
                crime -> binding.cvGenre.setCardBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.purple
                    )
                )
                fantasy -> binding.cvGenre.setCardBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.red
                    )
                )
                thriller -> binding.cvGenre.setCardBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.dark_green
                    )
                )
                family -> binding.cvGenre.setCardBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.blue
                    )
                )
                animation -> binding.cvGenre.setCardBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.red
                    )
                )
                sciFi -> binding.cvGenre.setCardBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.silver
                    )
                )
                comedy -> binding.cvGenre.setCardBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.yellow
                    )
                )
                mystery -> binding.cvGenre.setCardBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.green
                    )
                )
            }
        }
    }

    companion object {
        const val drama = "Drama"
        const val romance = "Romance"
        const val action = "Action"
        const val adventure = "Adventure"
        const val music = "Music"
        const val crime = "Crime"
        const val fantasy = "Fantasy"
        const val thriller = "Thriller"
        const val family = "Family"
        const val animation = "Animation"
        const val sciFi = "Sci-Fi"
        const val comedy = "Comedy"
        const val mystery = "Mystery"
        const val science = "Science Fiction"
    }
}