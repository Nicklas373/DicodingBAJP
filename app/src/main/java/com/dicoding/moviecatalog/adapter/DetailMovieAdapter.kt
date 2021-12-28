package com.dicoding.moviecatalog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.moviecatalog.data.MovieModuleEntity
import com.dicoding.moviecatalog.databinding.ItemsMovieListBinding

class DetailMovieAdapter : RecyclerView.Adapter<DetailMovieAdapter.ModuleViewHolder>() {

    private val listModules = ArrayList<MovieModuleEntity>()

    fun setModules(modules: List<MovieModuleEntity>?) {
        if (modules == null) return
        this.listModules.clear()
        this.listModules.addAll(modules)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleViewHolder {
        val itemMovieListBinding =
            ItemsMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ModuleViewHolder(itemMovieListBinding)
    }

    override fun onBindViewHolder(viewHolder: ModuleViewHolder, position: Int) {
        val module = listModules[position]
        viewHolder.bind(module)
    }

    override fun getItemCount(): Int = listModules.size

    inner class ModuleViewHolder(private val binding: ItemsMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(module: MovieModuleEntity) {
            binding.textModuleTitle.text = module.title
        }
    }
}