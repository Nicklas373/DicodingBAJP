package com.dicoding.moviecatalog.data.source

import androidx.lifecycle.MutableLiveData
import com.dicoding.moviecatalog.data.source.remote.response.ProductionCompaniesListResponse
import com.dicoding.moviecatalog.data.source.remote.response.movie.MovieGenreListResponse
import com.dicoding.moviecatalog.data.source.remote.response.movie.MovieListResponse
import com.dicoding.moviecatalog.data.source.remote.response.tvshow.TvShowGenreListResponse
import com.dicoding.moviecatalog.data.source.remote.response.tvshow.TvShowListResponse

interface DataSource {

    fun getAllMovies(): MutableLiveData<ArrayList<MovieListResponse>>

    fun getAllMoviesApi(listId: String): MutableLiveData<ArrayList<MovieListResponse>>

    fun getSelectedMovies(movieId: String): MutableLiveData<MovieListResponse>

    fun getCompaniesFromMovies(movieId: String): MutableLiveData<ArrayList<ProductionCompaniesListResponse>>

    fun getGenresFromMovies(movieId: String): MutableLiveData<ArrayList<MovieGenreListResponse>>

    fun getAllTvShow(): MutableLiveData<ArrayList<TvShowListResponse>>

    fun getAllTvShowApi(listId: String): MutableLiveData<ArrayList<TvShowListResponse>>

    fun getSelectedTvShow(tvShowId: String): MutableLiveData<TvShowListResponse>

    fun getCompaniesFromTvShow(tvShowId: String): MutableLiveData<ArrayList<ProductionCompaniesListResponse>>

    fun getGenresFromTvShow(tvShowId: String): MutableLiveData<ArrayList<TvShowGenreListResponse>>
}