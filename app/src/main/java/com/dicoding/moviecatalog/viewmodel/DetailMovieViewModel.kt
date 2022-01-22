package com.dicoding.moviecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalog.data.source.Repository
import com.dicoding.moviecatalog.data.source.remote.response.ProductionCompaniesListResponse
import com.dicoding.moviecatalog.data.source.remote.response.movie.MovieGenreListResponse
import com.dicoding.moviecatalog.data.source.remote.response.movie.MovieListResponse
import com.dicoding.moviecatalog.data.source.remote.response.tvshow.TvShowGenreListResponse
import com.dicoding.moviecatalog.data.source.remote.response.tvshow.TvShowListResponse

class DetailMovieViewModel(private val Repository: Repository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isToast = MutableLiveData<Boolean>()
    val isToast: LiveData<Boolean> = _isToast

    private val _toastReason = MutableLiveData<String>()
    val toastReason: LiveData<String> = _toastReason

    private lateinit var movieId: String
    private lateinit var tvShowId: String

    fun setSelectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun setSelectedTvShow(tvShowId: String) {
        this.tvShowId = tvShowId
    }

    fun getSelectedMovie(): MutableLiveData<MovieListResponse> =
        Repository.getSelectedMovies(movieId)

    fun getMovieGenres(): MutableLiveData<ArrayList<MovieGenreListResponse>> =
        Repository.getGenresFromMovies(movieId)

    fun getMovieCompanies(): MutableLiveData<ArrayList<ProductionCompaniesListResponse>> =
        Repository.getCompaniesFromMovies(movieId)

    fun getSelectedTvShow(): MutableLiveData<TvShowListResponse> =
        Repository.getSelectedTvShow(tvShowId)

    fun getTvShowGenres(): MutableLiveData<ArrayList<TvShowGenreListResponse>> =
        Repository.getGenresFromTvShow(tvShowId)

    fun getTvShowCompanies(): MutableLiveData<ArrayList<ProductionCompaniesListResponse>> =
        Repository.getCompaniesFromTvShow(tvShowId)

}