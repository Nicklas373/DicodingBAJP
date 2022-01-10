package com.dicoding.moviecatalog.data.movie.source

import com.dicoding.moviecatalog.data.movie.MovieCastEntity
import com.dicoding.moviecatalog.data.movie.MovieEntity
import com.dicoding.moviecatalog.data.tvshow.TvShowCastEntity
import com.dicoding.moviecatalog.data.tvshow.TvShowEntity

interface DataSource {

    fun getAllMovies(): List<MovieEntity>

    fun getCastMovies(movieId: String): List<MovieCastEntity>

    fun getMovieWithCast(movieId: String): MovieEntity

    fun getAllMoviesByCast(movieId: String): List<MovieCastEntity>

    fun getAllTvShow(): List<TvShowEntity>

    fun getCastTvShow(tvShowId: String): List<TvShowCastEntity>

    fun getTvShowWithCast(tvShowId: String): TvShowEntity

    fun getAllTvShowByCast(tvShowId: String): List<TvShowCastEntity>
}