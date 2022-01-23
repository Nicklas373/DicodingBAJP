package com.dicoding.moviecatalog.data.source

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.dicoding.moviecatalog.api.ApiConfig
import com.dicoding.moviecatalog.data.source.remote.RemoteDataSource
import com.dicoding.moviecatalog.data.source.remote.response.ProductionCompaniesListResponse
import com.dicoding.moviecatalog.data.source.remote.response.ProductionCompaniesResponse
import com.dicoding.moviecatalog.data.source.remote.response.movie.MovieGenreListResponse
import com.dicoding.moviecatalog.data.source.remote.response.movie.MovieGenreResponse
import com.dicoding.moviecatalog.data.source.remote.response.movie.MovieListResponse
import com.dicoding.moviecatalog.data.source.remote.response.movie.MovieResponse
import com.dicoding.moviecatalog.data.source.remote.response.tvshow.TvShowGenreListResponse
import com.dicoding.moviecatalog.data.source.remote.response.tvshow.TvShowGenreResponse
import com.dicoding.moviecatalog.data.source.remote.response.tvshow.TvShowListResponse
import com.dicoding.moviecatalog.data.source.remote.response.tvshow.TvShowResponse
import com.dicoding.moviecatalog.utils.CatalogDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository private constructor(private val remoteDataSource: RemoteDataSource) :
    DataSource {

    override fun getAllMovies(): MutableLiveData<ArrayList<MovieListResponse>> {
        val resultAllMovies = MutableLiveData<ArrayList<MovieListResponse>>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponses: ArrayList<MovieListResponse>) {
                resultAllMovies.postValue(CatalogDatabase.generateMovieLocal() as ArrayList<MovieListResponse>)
            }
        })

        return resultAllMovies
    }

    override fun getAllMoviesApi(listId: String): MutableLiveData<ArrayList<MovieListResponse>> {
        val resultAllMoviesApi = MutableLiveData<ArrayList<MovieListResponse>>()
        val client = ApiConfig.getApiService().getMovieList(listId)
        remoteDataSource.getAllMoviesApi(object : RemoteDataSource.LoadMoviesApiCallback {
            override fun onAllMoviesApiReceived(movieApiResponses: ArrayList<MovieListResponse>) {

                client.enqueue(object :
                    Callback<MovieResponse> {
                    override fun onResponse(
                        call: Call<MovieResponse>,
                        response: Response<MovieResponse>
                    ) {
                        if (response.isSuccessful) {
                            resultAllMoviesApi.postValue(response.body()?.items)
                        } else {
                            Log.e("Repository", "onFailure: ${response.message()}")
                        }
                    }

                    override fun onFailure(
                        call: Call<MovieResponse>,
                        t: Throwable
                    ) {
                        Log.e("Repository", "onFailure: ${t.message}")
                    }
                })
            }
        })
        return resultAllMoviesApi
    }

    override fun getSelectedMovies(movieId: String): MutableLiveData<MovieListResponse> {
        val selectedMovies = MutableLiveData<MovieListResponse>()
        val client = ApiConfig.getApiService().getSelectedMovie(movieId)
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponses: ArrayList<MovieListResponse>) {
                client.enqueue(object :
                    Callback<MovieListResponse> {
                    override fun onResponse(
                        call: Call<MovieListResponse>,
                        response: Response<MovieListResponse>
                    ) {
                        if (response.isSuccessful) {
                            selectedMovies.postValue(response.body())
                        } else {
                            Log.e("Repository", "onFailure: ${response.message()}")
                        }
                    }

                    override fun onFailure(
                        call: Call<MovieListResponse>,
                        t: Throwable
                    ) {
                        Log.e("Repository", "onFailure: ${t.message}")
                    }
                })
            }
        })

        return selectedMovies
    }

    override fun getCompaniesFromMovies(movieId: String): MutableLiveData<ArrayList<ProductionCompaniesListResponse>> {
        val resultProductionMovieApi = MutableLiveData<ArrayList<ProductionCompaniesListResponse>>()
        val client = ApiConfig.getApiService().getMovieCompaniesList(movieId)
        remoteDataSource.getCompaniesWithMovies(object :
            RemoteDataSource.LoadCompaniesWithMoviesCallback {
            override fun onAllCompaniesWithMoviesReceived(movieResponses: ArrayList<ProductionCompaniesListResponse>) {
                client.enqueue(object : Callback<ProductionCompaniesResponse> {
                    override fun onResponse(
                        call: Call<ProductionCompaniesResponse>,
                        response: Response<ProductionCompaniesResponse>
                    ) {
                        if (response.isSuccessful) {
                            resultProductionMovieApi.postValue(response.body()?.productionCompanies)
                        } else {
                            Log.e("Repository", "onFailure: ${response.message()}")
                        }
                    }

                    override fun onFailure(
                        call: Call<ProductionCompaniesResponse>,
                        t: Throwable
                    ) {

                    }
                })
            }
        })
        return resultProductionMovieApi
    }

    override fun getGenresFromMovies(movieId: String): MutableLiveData<ArrayList<MovieGenreListResponse>> {
        val resultGenreMovieApi = MutableLiveData<ArrayList<MovieGenreListResponse>>()
        val client = ApiConfig.getApiService().getMovieGenreList(movieId)
        remoteDataSource.getGenresWithMovies(object : RemoteDataSource.LoadMoviesGenresCallback {
            override fun onAllMoviesGenresReceived(movieResponses: ArrayList<MovieGenreListResponse>) {
                client.enqueue(object : Callback<MovieGenreResponse> {
                    override fun onResponse(
                        call: Call<MovieGenreResponse>,
                        response: Response<MovieGenreResponse>
                    ) {
                        if (response.isSuccessful) {
                            resultGenreMovieApi.postValue(response.body()?.genres)
                        } else {
                            Log.e("Repository", "onFailure: ${response.message()}")
                        }
                    }

                    override fun onFailure(
                        call: Call<MovieGenreResponse>,
                        t: Throwable
                    ) {

                    }
                })
            }
        })
        return resultGenreMovieApi
    }

    override fun getAllTvShow(): MutableLiveData<ArrayList<TvShowListResponse>> {
        val resultAllTvShow = MutableLiveData<ArrayList<TvShowListResponse>>()
        remoteDataSource.getAllTvShow(object : RemoteDataSource.LoadTvShowCallback {
            override fun onAllTvShowReceived(tvShowResponses: ArrayList<TvShowListResponse>) {
                resultAllTvShow.postValue(CatalogDatabase.generateTvShowLocal() as ArrayList<TvShowListResponse>)
            }
        })

        return resultAllTvShow
    }

    override fun getAllTvShowApi(listId: String): MutableLiveData<ArrayList<TvShowListResponse>> {
        val resultAllTvShowApi = MutableLiveData<ArrayList<TvShowListResponse>>()
        val client = ApiConfig.getApiService().getTvShowList(listId)
        remoteDataSource.getAllTvShowApi(object : RemoteDataSource.LoadTvShowApiCallback {
            override fun onAllTvShowApiReceived(tvShowApiResponses: ArrayList<TvShowListResponse>) {

                client.enqueue(object :
                    Callback<TvShowResponse> {
                    override fun onResponse(
                        call: Call<TvShowResponse>,
                        response: Response<TvShowResponse>
                    ) {
                        if (response.isSuccessful) {
                            resultAllTvShowApi.postValue(response.body()?.items)
                        } else {
                            Log.e("Repository", "onFailure: ${response.message()}")
                        }
                    }

                    override fun onFailure(
                        call: Call<TvShowResponse>,
                        t: Throwable
                    ) {
                        Log.e("Repository", "onFailure: ${t.message}")
                    }
                })
            }
        })
        return resultAllTvShowApi
    }

    override fun getSelectedTvShow(tvShowId: String): MutableLiveData<TvShowListResponse> {
        val selectedTvShow = MutableLiveData<TvShowListResponse>()
        val client = ApiConfig.getApiService().getSelectedTvShow(tvShowId)
        remoteDataSource.getAllTvShow(object : RemoteDataSource.LoadTvShowCallback {
            override fun onAllTvShowReceived(tvShowResponses: ArrayList<TvShowListResponse>) {
                client.enqueue(object :
                    Callback<TvShowListResponse> {
                    override fun onResponse(
                        call: Call<TvShowListResponse>,
                        response: Response<TvShowListResponse>
                    ) {
                        if (response.isSuccessful) {
                            selectedTvShow.postValue(response.body())
                        } else {
                            Log.e("Repository", "onFailure: ${response.message()}")
                        }
                    }

                    override fun onFailure(
                        call: Call<TvShowListResponse>,
                        t: Throwable
                    ) {
                        Log.e("Repository", "onFailure: ${t.message}")
                    }
                })
            }
        })

        return selectedTvShow
    }

    override fun getCompaniesFromTvShow(tvShowId: String): MutableLiveData<ArrayList<ProductionCompaniesListResponse>> {
        val resultProductionTvShowApi =
            MutableLiveData<ArrayList<ProductionCompaniesListResponse>>()
        val client = ApiConfig.getApiService().getTvShowCompaniesList(tvShowId)
        remoteDataSource.getCompaniesWithTvShow(object :
            RemoteDataSource.LoadCompaniesWithTvShowCallback {
            override fun onAllCompaniesWithTvShowReceived(tvShowResponses: ArrayList<ProductionCompaniesListResponse>) {
                client.enqueue(object : Callback<ProductionCompaniesResponse> {
                    override fun onResponse(
                        call: Call<ProductionCompaniesResponse>,
                        response: Response<ProductionCompaniesResponse>
                    ) {
                        if (response.isSuccessful) {
                            resultProductionTvShowApi.postValue(response.body()?.productionCompanies)
                        } else {
                            Log.e("Repository", "onFailure: ${response.message()}")
                        }
                    }

                    override fun onFailure(
                        call: Call<ProductionCompaniesResponse>,
                        t: Throwable
                    ) {

                    }
                })
            }
        })
        return resultProductionTvShowApi
    }

    override fun getGenresFromTvShow(tvShowId: String): MutableLiveData<ArrayList<TvShowGenreListResponse>> {
        val resultGenreTvShowApi = MutableLiveData<ArrayList<TvShowGenreListResponse>>()
        val client = ApiConfig.getApiService().getTvShowGenreList(tvShowId)
        remoteDataSource.getGenresWithTvShow(object : RemoteDataSource.LoadTvShowGenresCallback {
            override fun onAllTvShowGenresReceived(tvShowResponses: ArrayList<TvShowGenreListResponse>) {
                client.enqueue(object : Callback<TvShowGenreResponse> {
                    override fun onResponse(
                        call: Call<TvShowGenreResponse>,
                        response: Response<TvShowGenreResponse>
                    ) {
                        if (response.isSuccessful) {
                            resultGenreTvShowApi.postValue(response.body()?.genres)
                        } else {
                            Log.e("Repository", "onFailure: ${response.message()}")
                        }
                    }

                    override fun onFailure(
                        call: Call<TvShowGenreResponse>,
                        t: Throwable
                    ) {

                    }
                })
            }
        })
        return resultGenreTvShowApi
    }

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(remoteData: RemoteDataSource): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteData).apply { instance = this }
            }
    }
}