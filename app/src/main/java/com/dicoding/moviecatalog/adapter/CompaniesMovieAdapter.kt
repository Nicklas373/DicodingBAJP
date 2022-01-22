package com.dicoding.moviecatalog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.data.source.remote.response.ProductionCompaniesListResponse
import com.dicoding.moviecatalog.databinding.ItemsCompaniesListBinding

class CompaniesMovieAdapter(private val listCompanies: ArrayList<ProductionCompaniesListResponse>) :
    RecyclerView.Adapter<CompaniesMovieAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemsCompaniesListBinding.bind(itemView)
    }

    override fun getItemCount(): Int = listCompanies.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.items_companies_list, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        with(holder) {
            val (logo, name, id, origin) = listCompanies[position]
            val productionImage =
                itemView.resources.getString(R.string.movieDb_static_image) + logo
            binding.nameCompaniesTxt.text = name
            binding.originTxt.text = origin
            Glide.with(itemView.context)
                .load(productionImage)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(binding.imgCompanies)
        }
    }
}