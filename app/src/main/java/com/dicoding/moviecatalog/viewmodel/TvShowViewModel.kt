package com.dicoding.moviecatalog.viewmodel

import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalog.data.movie.source.Repository
import com.dicoding.moviecatalog.data.tvshow.TvShowEntity
import com.dicoding.moviecatalog.utils.CatalogDatabase

class TvShowViewModel(private val movieRepository: Repository) : ViewModel() {
    fun getTvShow(): List<TvShowEntity> = movieRepository.getAllTvShow()
}