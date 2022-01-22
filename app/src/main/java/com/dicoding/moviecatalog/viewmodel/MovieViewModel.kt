package com.dicoding.moviecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalog.data.source.Repository
import com.dicoding.moviecatalog.data.source.remote.response.movie.MovieListResponse

class MovieViewModel(private val repository: Repository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isToast = MutableLiveData<Boolean>()
    val isToast: LiveData<Boolean> = _isToast

    private val _toastReason = MutableLiveData<String>()
    val toastReason: LiveData<String> = _toastReason

    fun getMovie(): MutableLiveData<ArrayList<MovieListResponse>> =
        repository.getAllMoviesApi(listId)

    fun getMovieLocal(): MutableLiveData<ArrayList<MovieListResponse>> = repository.getAllMovies()

    companion object {
        private const val listId = "8174952"
    }
}
