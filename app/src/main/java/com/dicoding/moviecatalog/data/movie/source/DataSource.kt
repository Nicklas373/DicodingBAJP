package com.dicoding.moviecatalog.data.movie.source

import androidx.lifecycle.LiveData
import com.dicoding.moviecatalog.data.movie.MovieCastEntity
import com.dicoding.moviecatalog.data.movie.MovieEntity
import com.dicoding.moviecatalog.data.tvshow.TvShowCastEntity
import com.dicoding.moviecatalog.data.tvshow.TvShowEntity

interface DataSource {

    fun getAllMovies(): LiveData<List<MovieEntity>>

    fun getCastMovies(movieId: String): LiveData<List<MovieCastEntity>>

    fun getMovieWithCast(movieId: String): LiveData<MovieEntity>

    fun getAllMoviesByCast(movieId: String): LiveData<List<MovieCastEntity>>

    fun getAllTvShow(): LiveData<List<TvShowEntity>>

    fun getCastTvShow(tvShowId: String): LiveData<List<TvShowCastEntity>>

    fun getTvShowWithCast(tvShowId: String): LiveData<TvShowEntity>

    fun getAllTvShowByCast(tvShowId: String): LiveData<List<TvShowCastEntity>>
}