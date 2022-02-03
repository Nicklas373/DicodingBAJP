package com.dicoding.moviecatalog.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.activity.DetailShowActivity
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowListEntity
import com.dicoding.moviecatalog.databinding.ItemsTvShowBinding
import com.dicoding.moviecatalog.utils.InlineVariable

class TvShowAdapter :
    PagedListAdapter<TvShowListEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {
    private val listTvShow = ArrayList<TvShowListEntity>()

    fun setTvShow(TvShow: List<TvShowListEntity>?) {
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
        val tvShow = listTvShow[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listTvShow.size

    inner class TvShowViewHolder(private val binding: ItemsTvShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val inlineVariable = InlineVariable()

        fun bind(tvShow: TvShowListEntity) {
            with(binding) {
                val tvShowImage =
                    itemView.context.getString(R.string.movieDb_static_image) + tvShow.tvShowPoster
                val releaseDate = inlineVariable.setReleaseDate(tvShow.tvShowFirstAirDate)
                tvshowTitle.text = tvShow.tvShowName
                tvshowReleaseDate.text = releaseDate
                tvshowRatingText.text = tvShow.tvShowVote.toString()
                separateRatingText.visibility = View.GONE
                tvshowDurationText.visibility = View.GONE
                imgPoster.contentDescription = tvShowImage
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailShowActivity::class.java)
                    intent.putExtra(DetailShowActivity.SHOW_ID, "TvShow")
                    intent.putExtra(DetailShowActivity.EXTRA_TV_SHOW, tvShow.tvShowId)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(tvShowImage)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowListEntity>() {
            override fun areItemsTheSame(
                oldItem: TvShowListEntity,
                newItem: TvShowListEntity
            ): Boolean {
                return oldItem.tvShowId == newItem.tvShowId
            }

            override fun areContentsTheSame(
                oldItem: TvShowListEntity,
                newItem: TvShowListEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}