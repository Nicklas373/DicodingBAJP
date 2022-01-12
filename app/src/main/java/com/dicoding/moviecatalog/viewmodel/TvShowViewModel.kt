package com.dicoding.moviecatalog.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.api.ApiConfig
import com.dicoding.moviecatalog.data.movie.source.Repository
import com.dicoding.moviecatalog.data.tvshow.TvShowEntity
import com.dicoding.moviecatalog.data.tvshow.response.TvShowListResponse
import com.dicoding.moviecatalog.data.tvshow.response.TvShowResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowViewModel(private val movieRepository: Repository) : ViewModel() {
    private val _tvShowList = MutableLiveData<ArrayList<TvShowListResponse>>()
    val tvShowList: LiveData<ArrayList<TvShowListResponse>> = _tvShowList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isToast = MutableLiveData<Boolean>()
    val isToast: LiveData<Boolean> = _isToast

    private val _toastReason = MutableLiveData<String>()
    val toastReason: LiveData<String> = _toastReason

    fun getTvShow(): LiveData<List<TvShowEntity>> = movieRepository.getAllTvShow()

    fun getTvShowList() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getTvShowList(listId)
        client.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
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
                    _tvShowList.value = response.body()?.items
                } else {
                    _isToast.value = false
                    _toastReason.value = "onFailure: ${response.message()}"
                    Log.e(TAG, R.string.on_failure.toString() + " " + response.message())
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                _isLoading.value = false
                _isToast.value = false
                Log.e(TAG, R.string.on_failure.toString() + " " + t.message.toString())
            }
        })
    }

    companion object {
        private const val TAG = "TvShowViewModel"
        private const val listId = "8174957"
    }
}