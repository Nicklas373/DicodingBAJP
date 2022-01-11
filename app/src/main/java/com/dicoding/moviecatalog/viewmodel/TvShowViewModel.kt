package com.dicoding.moviecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalog.data.movie.source.Repository
import com.dicoding.moviecatalog.data.tvshow.TvShowEntity

class TvShowViewModel(private val movieRepository: Repository) : ViewModel() {
    fun getTvShow(): LiveData<List<TvShowEntity>> = movieRepository.getAllTvShow()
}