package com.dicoding.moviecatalog.data.movie.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.moviecatalog.api.ApiConfig
import com.dicoding.moviecatalog.data.movie.response.MovieListResponse
import com.dicoding.moviecatalog.data.movie.response.MovieResponse
import com.dicoding.moviecatalog.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiFetchDataSource {

    fun setMoviesData(): List<MovieListResponse> {
        val resultAllMoviesApi = ArrayList<MovieListResponse>()
        val client = ApiConfig.getApiService().getMovieList(listId)
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        resultAllMoviesApi.clear()
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
        return resultAllMoviesApi
    }

    companion object {
        private const val listId = "8174952"
        private const val TAG = "ApiFetch"
    }
}