package com.dicoding.moviecatalog.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.activity.DetailShowActivity
import com.dicoding.moviecatalog.data.tvshow.response.TvShowListResponse
import com.dicoding.moviecatalog.databinding.ItemsTvShowBinding

class TvShowApiAdapter(private val listTvShowApi: ArrayList<TvShowListResponse>) :
    RecyclerView.Adapter<TvShowApiAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemsTvShowBinding.bind(itemView)
    }

    override fun getItemCount(): Int = listTvShowApi.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.items_tv_show, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        with(holder) {
            val (tvShowOverview, tvShowOriginalName, tvShowReleaseDate, tvShowVoteAverage, tvShowName, tvShowPosterPath) = listTvShowApi[position]
            val tvShowImage =
                itemView.context.getString(R.string.movieDb_static_image) + tvShowPosterPath
            binding.tvshowTitle.text = tvShowName
            binding.tvshowReleaseDate.text = tvShowReleaseDate
            binding.tvshowRatingText.text = tvShowVoteAverage.toString()
            binding.separateRatingText.visibility = View.GONE
            binding.tvshowDurationText.visibility = View.GONE
            Glide.with(itemView.context)
                .load(tvShowImage)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(binding.imgPoster)
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailShowActivity::class.java)
                val index = position + 1
                intent.putExtra(DetailShowActivity.SHOW_ID, "TvShow")
                intent.putExtra(DetailShowActivity.EXTRA_TV_SHOW, index.toString())
                itemView.context.startActivity(intent)
            }
        }
    }
}