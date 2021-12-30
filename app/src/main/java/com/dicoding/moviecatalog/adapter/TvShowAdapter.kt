package com.dicoding.moviecatalog.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.activity.DetailShowActivity
import com.dicoding.moviecatalog.data.tvshow.TvShowEntity
import com.dicoding.moviecatalog.databinding.ItemsTvShowBinding

class TvShowAdapter :
    RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    private val listTvShow = ArrayList<TvShowEntity>()

    fun setTvShow(TvShow: List<TvShowEntity>?) {
        if (TvShow == null) return
        this.listTvShow.clear()
        this.listTvShow.addAll(TvShow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemsTvShowBinding =
            ItemsTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemsTvShowBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvshow = listTvShow[position]
        holder.bind(tvshow)
    }

    override fun getItemCount(): Int = listTvShow.size

    inner class TvShowViewHolder(private val binding: ItemsTvShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvshow: TvShowEntity) {
            with(binding) {
                tvshowTitle.text = tvshow.title
                tvshowReleaseDate.text = tvshow.releaseDate
                tvshowRatingText.text = tvshow.rating
                tvshowDurationText.text = tvshow.duration
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailShowActivity::class.java)
                    intent.putExtra(DetailShowActivity.SHOW_ID, "TvShow")
                    intent.putExtra(DetailShowActivity.EXTRA_TV_SHOW, tvshow.tvShowId)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(tvshow.imagePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
            }
        }
    }
}