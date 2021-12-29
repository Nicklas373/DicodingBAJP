package com.dicoding.moviecatalog.viewmodel

import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalog.data.tvshow.TvShowEntity
import com.dicoding.moviecatalog.utils.MovieDatabase

class TvShowViewModel : ViewModel() {
    fun getTvShow(): List<TvShowEntity> = MovieDatabase.generateTvShowDatabase()
}