package com.dicoding.moviecatalog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.moviecatalog.data.MovieModuleEntity
import com.dicoding.moviecatalog.databinding.ItemsMovieListCustomsBinding

class MovieListAdapter internal constructor(private val listener: MyAdapterClickListener) :
    RecyclerView.Adapter<MovieListAdapter.ModuleViewHolder>() {
    private val listModules = ArrayList<MovieModuleEntity>()

    internal fun setModules(modules: List<MovieModuleEntity>?) {
        if (modules == null) return
        this.listModules.clear()
        this.listModules.addAll(modules)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleViewHolder {
        val binding =
            ItemsMovieListCustomsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ModuleViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ModuleViewHolder, position: Int) {
        val module = listModules[position]
        viewHolder.bind(module)
        viewHolder.itemView.setOnClickListener {
            listener.onItemClicked(
                viewHolder.adapterPosition,
                listModules[viewHolder.adapterPosition].moduleId
            )
        }
    }

    override fun getItemCount(): Int = listModules.size

    inner class ModuleViewHolder(private val binding: ItemsMovieListCustomsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(module: MovieModuleEntity) {
            binding.textModuleTitle.text = module.title
        }
    }
}

internal interface MyAdapterClickListener {
    fun onItemClicked(position: Int, moduleId: String)
}