package com.dicoding.moviecatalog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.moviecatalog.data.movie.MovieModuleEntity
import com.dicoding.moviecatalog.databinding.ItemsMovieListBinding

class DetailMovieAdapter : RecyclerView.Adapter<DetailMovieAdapter.MovieModuleViewHolder>() {

    private val listMovieModules = ArrayList<MovieModuleEntity>()

    fun setMovieModule(movieModule: List<MovieModuleEntity>?) {
        if (movieModule == null) return
        this.listMovieModules.clear()
        this.listMovieModules.addAll(movieModule)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieModuleViewHolder {
        val itemMovieListBinding =
            ItemsMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieModuleViewHolder(itemMovieListBinding)
    }

    override fun onBindViewHolder(viewHolder: MovieModuleViewHolder, position: Int) {
        val movieModule = listMovieModules[position]
        viewHolder.bind(movieModule)
    }

    override fun getItemCount(): Int = listMovieModules.size

    inner class MovieModuleViewHolder(private val binding: ItemsMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(module: MovieModuleEntity) {
            binding.textModuleTitle.text = module.title
        }
    }
}