package com.dicoding.moviecatalog.viewmodel

import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalog.data.movie.MovieEntity
import com.dicoding.moviecatalog.utils.CatalogDatabase

class MovieViewModel : ViewModel() {
    fun getMovie(): List<MovieEntity> = CatalogDatabase.generateMovieDatabase()
}