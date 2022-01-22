package com.dicoding.moviecatalog.adapter.local

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.activity.DetailShowActivity
import com.dicoding.moviecatalog.data.source.remote.response.tvshow.TvShowListResponse
import com.dicoding.moviecatalog.databinding.ItemsTvShowBinding
import com.dicoding.moviecatalog.utils.InlineVariable
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TvShowAdapter :
    RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    private val listTvShow = ArrayList<TvShowListResponse>()

    fun setTvShow(TvShow: List<TvShowListResponse>?) {
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
        private val inlineVariable = InlineVariable()

        fun bind(tvShow: TvShowListResponse) {
            with(binding) {
                val tvShowImage =
                    itemView.context.getString(R.string.movieDb_static_image) + tvShow.tvShowPoster
                val releaseDate = inlineVariable.setReleaseDate(tvShow.tvShowFirstAirDate)
                tvshowTitle.text = tvShow.tvShowName
                tvshowReleaseDate.text = releaseDate
                tvshowRatingText.text = tvShow.tvShowVote.toString()
                separateRatingText.visibility = View.GONE
                tvshowDurationText.visibility = View.GONE
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
}