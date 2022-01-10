package com.dicoding.moviecatalog.viewmodel

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

    fun getMovie(): MovieEntity = movieRepository.getMovieWithCast(movieId)

    fun getCastMovie(movieId: String): List<MovieCastEntity> =
        movieRepository.getAllMoviesByCast(movieId)

    fun getTvShow(): TvShowEntity = movieRepository.getTvShowWithCast(tvShowId)

    fun getCastTvShow(tvShowId: String): List<TvShowCastEntity> =
        movieRepository.getAllTvShowByCast(tvShowId)
}