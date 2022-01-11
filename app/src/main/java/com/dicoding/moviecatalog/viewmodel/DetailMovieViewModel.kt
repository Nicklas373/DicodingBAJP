package com.dicoding.moviecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalog.data.movie.MovieCastEntity
import com.dicoding.moviecatalog.data.movie.MovieEntity
import com.dicoding.moviecatalog.data.movie.source.Repository
import com.dicoding.moviecatalog.data.tvshow.TvShowCastEntity
import com.dicoding.moviecatalog.data.tvshow.TvShowEntity

class DetailMovieViewModel(private val movieRepository: Repository) : ViewModel() {
    private lateinit var movieId: String
    private lateinit var tvShowId: String

    fun setSelectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun setSelectedTvShow(tvShowId: String) {
        this.tvShowId = tvShowId
    }

    fun getMovie(): LiveData<MovieEntity> = movieRepository.getMovieWithCast(movieId)

    fun getCastMovie(movieId: String): LiveData<List<MovieCastEntity>> =
        movieRepository.getAllMoviesByCast(movieId)

    fun getTvShow(): LiveData<TvShowEntity> = movieRepository.getTvShowWithCast(tvShowId)

    fun getCastTvShow(tvShowId: String): LiveData<List<TvShowCastEntity>> =
        movieRepository.getAllTvShowByCast(tvShowId)
}