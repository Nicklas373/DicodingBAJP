package com.dicoding.moviecatalog.data.movie.source.remote

import com.dicoding.moviecatalog.data.movie.source.remote.response.CastMovieResponse
import com.dicoding.moviecatalog.data.movie.source.remote.response.CastTvShowResponse
import com.dicoding.moviecatalog.data.movie.source.remote.response.MovieResponse
import com.dicoding.moviecatalog.data.movie.source.remote.response.TvShowResponse
import com.dicoding.moviecatalog.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(
                    helper
                ).apply { instance = this }
            }
    }

    fun getAllMovies(): List<MovieResponse> = jsonHelper.loadMovie()

    fun getCastMovies(movieId: String): List<CastMovieResponse> = jsonHelper.loadCastMovie(movieId)

    fun getAllTvShow(): List<TvShowResponse> = jsonHelper.loadTvShow()

    fun getCastTvShow(tvShowId: String): List<CastTvShowResponse> =
        jsonHelper.loadCastTvShow(tvShowId)
}