package com.dicoding.moviecatalog.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.api.ApiConfig
import com.dicoding.moviecatalog.data.movie.MovieEntity
import com.dicoding.moviecatalog.data.movie.response.MovieListResponse
import com.dicoding.moviecatalog.data.movie.response.MovieResponse
import com.dicoding.moviecatalog.data.movie.source.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel(private val movieRepository: Repository) : ViewModel() {
    private val _movieList = MutableLiveData<ArrayList<MovieListResponse>>()
    val movieList: LiveData<ArrayList<MovieListResponse>> = _movieList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isToast = MutableLiveData<Boolean>()
    val isToast: LiveData<Boolean> = _isToast

    private val _toastReason = MutableLiveData<String>()
    val toastReason: LiveData<String> = _toastReason

    fun getMovie(): LiveData<List<MovieEntity>> = movieRepository.getAllMovies()

    fun getMovieList() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getMovieList(listId)
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    if (response.body()?.items?.isEmpty() == true) {
                        _isToast.value = false
                        _toastReason.value = R.string.on_failure.toString()
                        Log.e(TAG, R.string.on_failure.toString() + " " + response.toString())
                    } else {
                        _isToast.value = true
                    }
                    _movieList.value = response.body()?.items
                } else {
                    _isToast.value = false
                    _toastReason.value = "onFailure: ${response.message()}"
                    Log.e(TAG, R.string.on_failure.toString() + " " + response.message())
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _isLoading.value = false
                _isToast.value = false
                Log.e(TAG, R.string.on_failure.toString() + " " + t.message.toString())
            }
        })
    }

    companion object {
        private const val TAG = "MovieViewModel"
        private const val listId = "8174952"
    }
}
