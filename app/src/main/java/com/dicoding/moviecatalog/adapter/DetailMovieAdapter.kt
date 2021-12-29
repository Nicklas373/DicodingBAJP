package com.dicoding.moviecatalog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.data.movie.CastEntity
import com.dicoding.moviecatalog.databinding.ItemsCastListBinding

class DetailMovieAdapter : RecyclerView.Adapter<DetailMovieAdapter.MovieModuleViewHolder>() {

    private val listMovieModules = ArrayList<CastEntity>()

    fun setMovieModule(movieModule: List<CastEntity>?) {
        if (movieModule == null) return
        this.listMovieModules.clear()
        this.listMovieModules.addAll(movieModule)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieModuleViewHolder {
        val itemCastListBinding =
            ItemsCastListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieModuleViewHolder(itemCastListBinding)
    }

    override fun onBindViewHolder(viewHolder: MovieModuleViewHolder, position: Int) {
        val movieModule = listMovieModules[position]
        viewHolder.bind(movieModule)
    }

    override fun getItemCount(): Int = listMovieModules.size

    inner class MovieModuleViewHolder(private val binding: ItemsCastListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cast: CastEntity) {
            binding.castRealName.text = cast.castRealName
            binding.castName.text = cast.castMovieName
            Glide.with(itemView.context)
                .load(cast.castImage)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(binding.imgCast)
        }
    }
}