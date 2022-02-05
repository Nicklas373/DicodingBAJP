package com.dicoding.moviecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalog.data.source.Repository
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieDetailEntity
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowDetailEntity
import com.dicoding.moviecatalog.vo.Resource

class DetailViewModel(private val Repository: Repository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isToast = MutableLiveData<Boolean>()
    val isToast: LiveData<Boolean> = _isToast

    private val _toastReason = MutableLiveData<String>()
    val toastReason: LiveData<String> = _toastReason

    private lateinit var isSusMovie: LiveData<Resource<MovieDetailEntity>>
    private lateinit var isSusTvShow: LiveData<Resource<TvShowDetailEntity>>

    fun nGetSusMovie() = isSusMovie

    fun nGetSelectedMovie(movieId: Int) {
        isSusMovie = Repository.getSelectedMovies(movieId)
    }

    fun nUpdateFavMovie() {
        val isSus = isSusMovie.value
        if (isSus?.data != null) {
            val newSus = !isSus.data.isSus
            Repository.updateFavMovies(isSus.data, newSus)
        }
    }

    fun nGetSusTvShow() = isSusTvShow

    fun nGetSelectedTvShow(tvShowId: Int) {
        isSusTvShow = Repository.getSelectedTvShow(tvShowId)
    }

    fun nUpdateFavTvShow() {
        val isSus = isSusTvShow.value
        if (isSus?.data != null) {
            val newSus = !isSus.data.isSus
            Repository.updateFavTvShow(isSus.data, newSus)
        }
    }
}