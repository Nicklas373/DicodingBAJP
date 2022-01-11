package com.dicoding.moviecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalog.data.movie.MovieEntity
import com.dicoding.moviecatalog.data.movie.source.Repository

class MovieViewModel(private val movieRepository: Repository) : ViewModel() {
    fun getMovie(): LiveData<List<MovieEntity>> = movieRepository.getAllMovies()
}