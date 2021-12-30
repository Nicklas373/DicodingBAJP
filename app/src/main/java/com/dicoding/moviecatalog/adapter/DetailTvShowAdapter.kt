package com.dicoding.moviecatalog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.data.tvshow.TvShowCastEntity
import com.dicoding.moviecatalog.databinding.ItemsCastListBinding

class DetailTvShowAdapter : RecyclerView.Adapter<DetailTvShowAdapter.TvShowCastViewHolder>() {

    private val listTvShowCastList = ArrayList<TvShowCastEntity>()

    fun setTvShowCastList(tvShowCast: List<TvShowCastEntity>) {
        this.listTvShowCastList.clear()
        this.listTvShowCastList.addAll(tvShowCast)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowCastViewHolder {
        val itemCastListBinding =
            ItemsCastListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowCastViewHolder(itemCastListBinding)
    }

    override fun onBindViewHolder(viewHolder: TvShowCastViewHolder, position: Int) {
        val tvShowCastList = listTvShowCastList[position]
        viewHolder.bind(tvShowCastList)
    }

    override fun getItemCount(): Int = listTvShowCastList.size

    inner class TvShowCastViewHolder(private val binding: ItemsCastListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cast: TvShowCastEntity) {
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