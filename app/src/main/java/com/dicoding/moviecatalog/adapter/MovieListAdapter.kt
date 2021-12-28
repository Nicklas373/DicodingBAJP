package com.dicoding.moviecatalog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.moviecatalog.data.movie.MovieModuleEntity
import com.dicoding.moviecatalog.databinding.ItemsMovieListCustomsBinding

class MovieListAdapter internal constructor(private val listener: MovieDetailAdapterClickListener) :
    RecyclerView.Adapter<MovieListAdapter.MovieModuleViewHolder>() {
    private val listMovieModules = ArrayList<MovieModuleEntity>()

    internal fun setMovieModule(movieModule: List<MovieModuleEntity>?) {
        if (movieModule == null) return
        this.listMovieModules.clear()
        this.listMovieModules.addAll(movieModule)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieModuleViewHolder {
        val binding =
            ItemsMovieListCustomsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieModuleViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: MovieModuleViewHolder, position: Int) {
        val module = listMovieModules[position]
        viewHolder.bind(module)
        viewHolder.itemView.setOnClickListener {
            listener.onItemClicked(
                viewHolder.adapterPosition,
                listMovieModules[viewHolder.adapterPosition].moduleId
            )
        }
    }

    override fun getItemCount(): Int = listMovieModules.size

    inner class MovieModuleViewHolder(private val binding: ItemsMovieListCustomsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(module: MovieModuleEntity) {
            binding.textModuleTitle.text = module.title
        }
    }
}

internal interface MovieDetailAdapterClickListener {
    fun onItemClicked(position: Int, movieId: String)
}