package com.dicoding.moviecatalog.data.source.remote

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.moviecatalog.api.ApiConfig
import com.dicoding.moviecatalog.data.source.remote.response.movie.MovieListResponse
import com.dicoding.moviecatalog.data.source.remote.response.movie.MovieResponse
import com.dicoding.moviecatalog.data.source.remote.response.tvshow.TvShowListResponse
import com.dicoding.moviecatalog.data.source.remote.response.tvshow.TvShowResponse
import com.dicoding.moviecatalog.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor() {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(
                ).apply { instance = this }
            }
    }

    fun getAllMovies(listId: String): LiveData<ApiResponse<ArrayList<MovieListResponse>>> {
        val resultMovies = MutableLiveData<ApiResponse<ArrayList<MovieListResponse>>>()
        val client = ApiConfig.getApiService().getMovieList(listId)
        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                client.enqueue(object : Callback<MovieResponse> {
                    override fun onResponse(
                        call: Call<MovieResponse>,
                        response: Response<MovieResponse>
                    ) {
                        resultMovies.value =
                            ApiResponse.success(response.body()?.items as ArrayList<MovieListResponse>)
                        EspressoIdlingResource.decrement()
                    }

                    override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                        Log.e("RemoteDataSource", "getAllMovies onFailure : ${t.message}")
                        EspressoIdlingResource.decrement()
                    }
                })

            },
            SERVICE_LATENCY_IN_MILLIS
        )
        return resultMovies
    }

    fun getSelectedMovies(movieId: Int): LiveData<ApiResponse<MovieListResponse>> {
        val resultMovies = MutableLiveData<ApiResponse<MovieListResponse>>()
        val client = ApiConfig.getApiService().getSelectedMovie(movieId)

        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                client.enqueue(object : Callback<MovieListResponse> {
                    override fun onResponse(
                        call: Call<MovieListResponse>,
                        response: Response<MovieListResponse>
                    ) {
                        if (response.isSuccessful) {
                            resultMovies.value = ApiResponse.success(response.body()!!)
                            EspressoIdlingResource.decrement()
                        }
                    }

                    override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                        Log.e("RemoteDataSource", "getSelectedMovies onFailure : ${t.message}")
                        EspressoIdlingResource.decrement()
                    }
                })
            }, SERVICE_LATENCY_IN_MILLIS
        )
        return resultMovies
    }

    fun getAllTvShow(listId: String): LiveData<ApiResponse<ArrayList<TvShowListResponse>>> {
        val resultTvShow = MutableLiveData<ApiResponse<ArrayList<TvShowListResponse>>>()
        val client = ApiConfig.getApiService().getTvShowList(listId)

        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                client.enqueue(object : Callback<TvShowResponse> {
                    override fun onResponse(
                        call: Call<TvShowResponse>,
                        response: Response<TvShowResponse>
                    ) {
                        resultTvShow.value =
                            ApiResponse.success(response.body()?.items as ArrayList<TvShowListResponse>)
                        EspressoIdlingResource.decrement()
                    }

                    override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                        Log.e("RemoteDataSource", "getTvShow onFailure : ${t.message}")
                        EspressoIdlingResource.decrement()
                    }
                })
            }, SERVICE_LATENCY_IN_MILLIS
        )
        return resultTvShow
    }

    fun getSelectedTvShow(tvShowId: Int): LiveData<ApiResponse<TvShowListResponse>> {
        val resultTvShow = MutableLiveData<ApiResponse<TvShowListResponse>>()
        val client = ApiConfig.getApiService().getSelectedTvShow(tvShowId)

        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                client.enqueue(object : Callback<TvShowListResponse> {
                    override fun onResponse(
                        call: Call<TvShowListResponse>,
                        response: Response<TvShowListResponse>
                    ) {
                        if (response.isSuccessful) {
                            resultTvShow.value = ApiResponse.success(response.body()!!)
                            EspressoIdlingResource.decrement()
                        }
                    }

                    override fun onFailure(call: Call<TvShowListResponse>, t: Throwable) {
                        Log.e("RemoteDataSource", "getSelectedTvShow onFailure : ${t.message}")
                        EspressoIdlingResource.decrement()
                    }
                })
            }, SERVICE_LATENCY_IN_MILLIS
        )
        return resultTvShow
    }
}