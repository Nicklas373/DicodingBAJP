package com.dicoding.moviecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalog.data.source.Repository
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieDetailEntity

class MovieFavViewModel(private val repository: Repository) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isToast = MutableLiveData<Boolean>()
    val isToast: LiveData<Boolean> = _isToast

    private val _toastReason = MutableLiveData<String>()
    val toastReason: LiveData<String> = _toastReason

    fun getFavMovies(sort: String) = repository.getFavoriteMovies(sort)

    fun updateFavMovies(movieDetailEntity: MovieDetailEntity) {
        val newSus = !movieDetailEntity.isSus
        repository.updateFavMovies(movieDetailEntity, newSus)
    }
}