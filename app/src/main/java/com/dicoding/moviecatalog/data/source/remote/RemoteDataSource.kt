package com.dicoding.moviecatalog.data.source.remote

import android.os.Handler
import android.os.Looper
import com.dicoding.moviecatalog.data.source.remote.response.ProductionCompaniesListResponse
import com.dicoding.moviecatalog.data.source.remote.response.movie.MovieGenreListResponse
import com.dicoding.moviecatalog.data.source.remote.response.movie.MovieListResponse
import com.dicoding.moviecatalog.data.source.remote.response.tvshow.TvShowGenreListResponse
import com.dicoding.moviecatalog.data.source.remote.response.tvshow.TvShowListResponse
import com.dicoding.moviecatalog.utils.EspressoIdlingResource
import com.dicoding.moviecatalog.utils.Helper

class RemoteDataSource private constructor(private val helper: Helper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: Helper): RemoteDataSource =
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
                callback.onAllMoviesReceived(helper.loadMovies())
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getAllMoviesApi(callback: LoadMoviesApiCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                callback.onAllMoviesApiReceived(helper.loadMovies())
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getCompaniesWithMovies(callback: LoadCompaniesWithMoviesCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                callback.onAllCompaniesWithMoviesReceived(helper.loadCompaniesMovies())
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getGenresWithMovies(callback: LoadMoviesGenresCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                callback.onAllMoviesGenresReceived(helper.loadGenresMovies())
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getAllTvShow(callback: LoadTvShowCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                callback.onAllTvShowReceived(helper.loadTvShow())
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getAllTvShowApi(callback: LoadTvShowApiCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                callback.onAllTvShowApiReceived(helper.loadTvShow())
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getCompaniesWithTvShow(callback: LoadCompaniesWithTvShowCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                callback.onAllCompaniesWithTvShowReceived(helper.loadCompaniesTvShow())
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getGenresWithTvShow(callback: LoadTvShowGenresCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                callback.onAllTvShowGenresReceived(helper.loadGenresTvShow())
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponses: ArrayList<MovieListResponse>)
    }

    interface LoadMoviesApiCallback {
        fun onAllMoviesApiReceived(movieApiResponses: ArrayList<MovieListResponse>)
    }

    interface LoadMoviesGenresCallback {
        fun onAllMoviesGenresReceived(movieResponses: ArrayList<MovieGenreListResponse>)
    }

    interface LoadCompaniesWithMoviesCallback {
        fun onAllCompaniesWithMoviesReceived(movieResponses: ArrayList<ProductionCompaniesListResponse>)
    }

    interface LoadTvShowCallback {
        fun onAllTvShowReceived(tvShowResponses: ArrayList<TvShowListResponse>)
    }

    interface LoadTvShowApiCallback {
        fun onAllTvShowApiReceived(tvShowApiResponses: ArrayList<TvShowListResponse>)
    }

    interface LoadTvShowGenresCallback {
        fun onAllTvShowGenresReceived(tvShowResponses: ArrayList<TvShowGenreListResponse>)
    }

    interface LoadCompaniesWithTvShowCallback {
        fun onAllCompaniesWithTvShowReceived(tvShowResponses: ArrayList<ProductionCompaniesListResponse>)
    }
}