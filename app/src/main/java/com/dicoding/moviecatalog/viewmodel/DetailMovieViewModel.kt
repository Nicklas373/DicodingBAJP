package com.dicoding.moviecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalog.data.source.Repository
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieDetailEntity
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowDetailEntity
import com.dicoding.moviecatalog.vo.Resource

class DetailMovieViewModel(private val Repository: Repository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isToast = MutableLiveData<Boolean>()
    val isToast: LiveData<Boolean> = _isToast

    private val _toastReason = MutableLiveData<String>()
    val toastReason: LiveData<String> = _toastReason

    private val nMovieId = MutableLiveData<Int>()
    private val nTvShowId = MutableLiveData<Int>()

    fun nSetSelectedMovie(movieId: Int) {
        this.nMovieId.value = movieId
    }

    fun nSetSelectedTvShow(tvShowId: Int) {
        this.nTvShowId.value = tvShowId
    }

    fun nGetSelectedMovie(movieId: Int): LiveData<Resource<MovieDetailEntity>> =
        Repository.getSelectedMovies(movieId)

    fun nGetSelectedTvShow(tvShowId: Int): LiveData<Resource<TvShowDetailEntity>> =
        Repository.getSelectedTvShow(tvShowId)
}