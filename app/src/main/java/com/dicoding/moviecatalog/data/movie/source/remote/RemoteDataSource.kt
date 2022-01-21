package com.dicoding.moviecatalog.data.movie.source.remote

import android.os.Handler
import android.os.Looper
import com.dicoding.moviecatalog.data.movie.response.MovieListResponse
import com.dicoding.moviecatalog.data.movie.source.remote.response.CastMovieResponse
import com.dicoding.moviecatalog.data.movie.source.remote.response.CastTvShowResponse
import com.dicoding.moviecatalog.data.movie.source.remote.response.MovieResponse
import com.dicoding.moviecatalog.data.movie.source.remote.response.TvShowResponse
import com.dicoding.moviecatalog.utils.EspressoIdlingResource
import com.dicoding.moviecatalog.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(
                    helper
                ).apply { instance = this }
            }
    }

    fun getAllMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                callback.onAllMoviesReceived(jsonHelper.loadMovie())
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getAllMoviesApi(callback: LoadMoviesApiCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                callback.onAllMoviesApiReceived(jsonHelper.loadMoviesApi())
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getCastMovies(movieId: String, callback: LoadCastMoviesCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                callback.onAllCastMoviesReceived(jsonHelper.loadCastMovie(movieId))
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )

    }

    fun getAllTvShow(callback: LoadTvShowCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                callback.onAllTvShowReceived(jsonHelper.loadTvShow())
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getCastTvShow(
        tvShowId: String,
        callback: LoadCastTvShowCallback
    ) {
        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                callback.onAllCastTvShowReceived(jsonHelper.loadCastTvShow(tvShowId))
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponses: List<MovieResponse>)
    }

    interface LoadMoviesApiCallback {
        fun onAllMoviesApiReceived(movieApiResponses: ArrayList<MovieListResponse>)
    }

    interface LoadTvShowCallback {
        fun onAllTvShowReceived(tvShowResponse: List<TvShowResponse>)
    }

    interface LoadCastMoviesCallback {
        fun onAllCastMoviesReceived(castMovieResponses: List<CastMovieResponse>)
    }

    interface LoadCastTvShowCallback {
        fun onAllCastTvShowReceived(CastTvShowResponse: List<CastTvShowResponse>)
    }
}