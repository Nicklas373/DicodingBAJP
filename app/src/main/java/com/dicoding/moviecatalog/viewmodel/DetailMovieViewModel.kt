package com.dicoding.moviecatalog.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.api.ApiConfig
import com.dicoding.moviecatalog.data.movie.MovieCastEntity
import com.dicoding.moviecatalog.data.movie.MovieEntity
import com.dicoding.moviecatalog.data.movie.response.MovieGenreListResponse
import com.dicoding.moviecatalog.data.movie.response.MovieGenreResponse
import com.dicoding.moviecatalog.data.movie.response.MovieListResponse
import com.dicoding.moviecatalog.data.movie.source.Repository
import com.dicoding.moviecatalog.data.tvshow.TvShowCastEntity
import com.dicoding.moviecatalog.data.tvshow.TvShowEntity
import com.dicoding.moviecatalog.data.tvshow.response.TvShowListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMovieViewModel(private val movieRepository: Repository) : ViewModel() {
    private val _movieDetailList = MutableLiveData<MovieListResponse>()
    val movieDetailList: LiveData<MovieListResponse> = _movieDetailList

    private val _movieGenreList = MutableLiveData<ArrayList<MovieGenreListResponse>>()
    val movieGenreList: LiveData<ArrayList<MovieGenreListResponse>> = _movieGenreList

    private val _tvShowDetailList = MutableLiveData<TvShowListResponse>()
    val tvShowDetailList: LiveData<TvShowListResponse> = _tvShowDetailList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isToast = MutableLiveData<Boolean>()
    val isToast: LiveData<Boolean> = _isToast

    private val _toastReason = MutableLiveData<String>()
    val toastReason: LiveData<String> = _toastReason

    private lateinit var movieId: String
    private lateinit var tvShowId: String

    fun getMovieListDetails(movieId: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getMovieDetail(movieId)
        client.enqueue(object : Callback<MovieListResponse> {
            override fun onResponse(
                call: Call<MovieListResponse>,
                response: Response<MovieListResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        _toastReason.value = R.string.on_failure.toString()
                        Log.e(TAG, R.string.on_failure.toString() + " " + response.toString())
                        _movieDetailList.value = response.body()
                    } else {
                        _isToast.value = true
                    }
                } else {
                    _isLoading.value = true
                    _isToast.value = false
                    _toastReason.value = "onFailure: ${response.message()}"
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                _isLoading.value = false
                _isToast.value = false
                Log.e(TAG, R.string.on_failure.toString() + " " + t.message.toString())
            }
        })
    }

    fun getMovieGenreListDetails(movieId: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getMovieGenreList(movieId)
        client.enqueue(object : Callback<MovieGenreResponse> {
            override fun onResponse(
                call: Call<MovieGenreResponse>,
                response: Response<MovieGenreResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        _toastReason.value = R.string.on_failure.toString()
                        Log.e(TAG, R.string.on_failure.toString() + " " + response.toString())
                        _movieGenreList.value = response.body()!!.genres
                    } else {
                        _isToast.value = true
                    }
                } else {
                    _isToast.value = false
                    _toastReason.value = "onFailure: ${response.message()}"
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieGenreResponse>, t: Throwable) {
                _isLoading.value = false
                _isToast.value = false
                Log.e(TAG, R.string.on_failure.toString() + " " + t.message.toString())
            }
        })
    }

    fun setSelectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun setSelectedTvShow(tvShowId: String) {
        this.tvShowId = tvShowId
    }

    fun getMovie(): LiveData<MovieEntity> = movieRepository.getMovieWithCast(movieId)

    fun getCastMovie(movieId: String): LiveData<List<MovieCastEntity>> =
        movieRepository.getAllMoviesByCast(movieId)

    fun getTvShow(): LiveData<TvShowEntity> = movieRepository.getTvShowWithCast(tvShowId)

    fun getCastTvShow(tvShowId: String): LiveData<List<TvShowCastEntity>> =
        movieRepository.getAllTvShowByCast(tvShowId)

    companion object {
        private val TAG = DetailMovieViewModel::class.java.simpleName
    }
}