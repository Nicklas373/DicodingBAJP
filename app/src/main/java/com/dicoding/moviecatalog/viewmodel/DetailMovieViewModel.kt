package com.dicoding.moviecatalog.viewmodel

import android.os.Handler
import android.os.Looper
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
import com.dicoding.moviecatalog.data.tvshow.response.TvShowDetailResponse
import com.dicoding.moviecatalog.data.tvshow.response.TvShowGenreListResponse
import com.dicoding.moviecatalog.data.tvshow.response.TvShowGenreResponse
import com.dicoding.moviecatalog.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMovieViewModel(private val movieRepository: Repository) : ViewModel() {
    private val _movieDetailList = MutableLiveData<MovieListResponse>()
    val movieDetailList: LiveData<MovieListResponse> = _movieDetailList

    private val _movieGenreList = MutableLiveData<ArrayList<MovieGenreListResponse>>()
    val movieGenreList: LiveData<ArrayList<MovieGenreListResponse>> = _movieGenreList

    private val _tvShowDetailList = MutableLiveData<TvShowDetailResponse>()
    val tvShowDetailList: LiveData<TvShowDetailResponse> = _tvShowDetailList

    private val _tvShowGenreList = MutableLiveData<ArrayList<TvShowGenreListResponse>>()
    val tvShowGenreList: LiveData<ArrayList<TvShowGenreListResponse>> = _tvShowGenreList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isToast = MutableLiveData<Boolean>()
    val isToast: LiveData<Boolean> = _isToast

    private val _toastReason = MutableLiveData<String>()
    val toastReason: LiveData<String> = _toastReason

    private lateinit var movieId: String
    private lateinit var tvShowId: String

    private val handler = Handler(Looper.getMainLooper())

    fun getMovieListDetails(movieId: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getMovieDetail(movieId)
        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                client.enqueue(object : Callback<MovieListResponse> {
                    override fun onResponse(
                        call: Call<MovieListResponse>,
                        response: Response<MovieListResponse>
                    ) {
                        _isLoading.value = false
                        if (response.isSuccessful) {
                            if (response.body() != null) {
                                _toastReason.value = R.string.on_failure.toString()
                                Log.e(
                                    TAG,
                                    R.string.on_failure.toString() + " " + response.toString()
                                )
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
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getTvShowListDetails(tvShowId: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getTvShowDetail(tvShowId)
        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                client.enqueue(object : Callback<TvShowDetailResponse> {
                    override fun onResponse(
                        call: Call<TvShowDetailResponse>,
                        response: Response<TvShowDetailResponse>
                    ) {
                        _isLoading.value = false
                        if (response.isSuccessful) {
                            if (response.body() != null) {
                                _toastReason.value = R.string.on_failure.toString()
                                Log.e(
                                    TAG,
                                    R.string.on_failure.toString() + " " + response.toString()
                                )
                                _tvShowDetailList.value = response.body()
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

                    override fun onFailure(call: Call<TvShowDetailResponse>, t: Throwable) {
                        _isLoading.value = false
                        _isToast.value = false
                        Log.e(TAG, R.string.on_failure.toString() + " " + t.message.toString())
                    }
                })
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getMovieGenreListDetails(movieId: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getMovieGenreList(movieId)
        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                client.enqueue(object : Callback<MovieGenreResponse> {
                    override fun onResponse(
                        call: Call<MovieGenreResponse>,
                        response: Response<MovieGenreResponse>
                    ) {
                        _isLoading.value = false
                        if (response.isSuccessful) {
                            if (response.body() != null) {
                                _toastReason.value = R.string.on_failure.toString()
                                Log.e(
                                    TAG,
                                    R.string.on_failure.toString() + " " + response.toString()
                                )
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
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getTvShowGenreListDetails(tvShowId: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getTvShowGenreList(tvShowId)
        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                client.enqueue(object : Callback<TvShowGenreResponse> {
                    override fun onResponse(
                        call: Call<TvShowGenreResponse>,
                        response: Response<TvShowGenreResponse>
                    ) {
                        _isLoading.value = false
                        if (response.isSuccessful) {
                            if (response.body() != null) {
                                _toastReason.value = R.string.on_failure.toString()
                                Log.e(
                                    TAG,
                                    R.string.on_failure.toString() + " " + response.toString()
                                )
                                _tvShowGenreList.value = response.body()!!.genres
                            } else {
                                _isToast.value = true
                            }
                        } else {
                            _isToast.value = false
                            _toastReason.value = "onFailure: ${response.message()}"
                            Log.e(TAG, "onFailure: ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<TvShowGenreResponse>, t: Throwable) {
                        _isLoading.value = false
                        _isToast.value = false
                        Log.e(TAG, R.string.on_failure.toString() + " " + t.message.toString())
                    }
                })
            },
            SERVICE_LATENCY_IN_MILLIS
        )
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
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000
    }
}