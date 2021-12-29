package com.dicoding.moviecatalog.viewmodel

import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalog.data.movie.MovieEntity
import com.dicoding.moviecatalog.utils.MovieDatabase

class MovieViewModel : ViewModel() {
    fun getMovie(): List<MovieEntity> = MovieDatabase.generateMovieDatabase()
}