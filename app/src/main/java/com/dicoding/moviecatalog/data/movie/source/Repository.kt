package com.dicoding.moviecatalog.data.movie.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.moviecatalog.api.ApiConfig
import com.dicoding.moviecatalog.data.movie.MovieCastEntity
import com.dicoding.moviecatalog.data.movie.MovieEntity
import com.dicoding.moviecatalog.data.movie.response.MovieListResponse
import com.dicoding.moviecatalog.data.movie.source.remote.RemoteDataSource
import com.dicoding.moviecatalog.data.movie.source.remote.response.CastMovieResponse
import com.dicoding.moviecatalog.data.movie.source.remote.response.CastTvShowResponse
import com.dicoding.moviecatalog.data.movie.source.remote.response.MovieResponse
import com.dicoding.moviecatalog.data.movie.source.remote.response.TvShowResponse
import com.dicoding.moviecatalog.data.tvshow.TvShowCastEntity
import com.dicoding.moviecatalog.data.tvshow.TvShowEntity
import com.dicoding.moviecatalog.utils.JsonHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository private constructor(private val remoteDataSource: RemoteDataSource) :
    DataSource {

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(remoteData: RemoteDataSource): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteData).apply { instance = this }
            }

        private const val TAG = "Repository"
    }

    override fun getAllMovies(): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponses: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in movieResponses) {
                    val movie = MovieEntity(
                        response.movieId,
                        response.title,
                        response.genre1,
                        response.genre2,
                        response.description,
                        response.duration,
                        response.releaseDate,
                        response.imagePath,
                        response.rating
                    )
                    movieList.add(movie)
                }
                movieResults.postValue(movieList)
            }
        })

        return movieResults
    }

    override fun getAllMoviesApi(listId: String): LiveData<ArrayList<MovieListResponse>> {
        val resultAllMoviesApi = MutableLiveData<ArrayList<MovieListResponse>>()
        val client = ApiConfig.getApiService().getMovieList(listId)
        remoteDataSource.getAllMoviesApi(object : RemoteDataSource.LoadMoviesApiCallback {
            override fun onAllMoviesApiReceived(movieApiResponses: ArrayList<MovieListResponse>) {

                client.enqueue(object :
                    Callback<com.dicoding.moviecatalog.data.movie.response.MovieResponse> {
                    override fun onResponse(
                        call: Call<com.dicoding.moviecatalog.data.movie.response.MovieResponse>,
                        response: Response<com.dicoding.moviecatalog.data.movie.response.MovieResponse>
                    ) {
                        if (response.isSuccessful) {
                            if (response.body()?.items?.isEmpty() == true) {
                                Log.e(
                                    "Repository",
                                    "response size:  ${response.body()!!.items.size}"
                                )
                            } else {
                                Log.e(
                                    "Repository",
                                    "response size:  ${response.body()!!.items.size}"
                                )
                            }
                            resultAllMoviesApi.postValue(response.body()?.items)
                        } else {
                            Log.e("Repository", "onFailure: ${response.message()}")
                        }
                    }

                    override fun onFailure(
                        call: Call<com.dicoding.moviecatalog.data.movie.response.MovieResponse>,
                        t: Throwable
                    ) {
                        Log.e("Repository", "onFailure: ${t.message}")
                    }
                })
            }
        })
        return resultAllMoviesApi
    }

    override fun getCastMovies(movieId: String): LiveData<List<MovieCastEntity>> {
        val castMovieResults = MutableLiveData<List<MovieCastEntity>>()
        remoteDataSource.getCastMovies(movieId, object : RemoteDataSource.LoadCastMoviesCallback {
            override fun onAllCastMoviesReceived(castMovieResponses: List<CastMovieResponse>) {
                val castMovieList = ArrayList<MovieCastEntity>()
                for (response in castMovieResponses) {
                    val castMovie = MovieCastEntity(
                        response.castId,
                        response.castMovieName,
                        response.castRealName,
                        response.castImage
                    )
                    castMovieList.add(castMovie)
                }
                castMovieResults.postValue(castMovieList)
            }
        })

        return castMovieResults
    }

    override fun getMovieWithCast(movieId: String): LiveData<MovieEntity> {
        val movieWithCastResults = MutableLiveData<MovieEntity>()
        lateinit var movie: MovieEntity
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponses: List<MovieResponse>) {
                for (response in movieResponses) {
                    if (response.movieId == movieId) {
                        movie = MovieEntity(
                            response.movieId,
                            response.title,
                            response.genre1,
                            response.genre2,
                            response.description,
                            response.duration,
                            response.releaseDate,
                            response.imagePath,
                            response.rating
                        )
                    }
                }
                movieWithCastResults.postValue(movie)
            }
        })

        return movieWithCastResults
    }

    override fun getAllMoviesByCast(movieId: String): LiveData<List<MovieCastEntity>> {
        val movieByCastResults = MutableLiveData<List<MovieCastEntity>>()
        remoteDataSource.getCastMovies(movieId, object : RemoteDataSource.LoadCastMoviesCallback {
            override fun onAllCastMoviesReceived(castMovieResponses: List<CastMovieResponse>) {
                val castMovieList = ArrayList<MovieCastEntity>()
                for (response in castMovieResponses) {
                    val castMovie = MovieCastEntity(
                        response.castId,
                        response.castMovieName,
                        response.castRealName,
                        response.castImage
                    )
                    castMovieList.add(castMovie)
                }
                movieByCastResults.postValue(castMovieList)
            }
        })

        return movieByCastResults
    }

    override fun getAllTvShow(): LiveData<List<TvShowEntity>> {
        val tvShowResults = MutableLiveData<List<TvShowEntity>>()
        remoteDataSource.getAllTvShow(object : RemoteDataSource.LoadTvShowCallback {
            override fun onAllTvShowReceived(tvShowResponse: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in tvShowResponse) {
                    val tvShow = TvShowEntity(
                        response.tvShowId,
                        response.title,
                        response.description,
                        response.genre1,
                        response.genre2,
                        response.episode,
                        response.season,
                        response.duration,
                        response.releaseDate,
                        response.imagePath,
                        response.rating
                    )
                    tvShowList.add(tvShow)
                }
                tvShowResults.postValue(tvShowList)
            }
        })

        return tvShowResults
    }

    override fun getCastTvShow(tvShowId: String): LiveData<List<TvShowCastEntity>> {
        val castTvShowResults = MutableLiveData<List<TvShowCastEntity>>()
        remoteDataSource.getCastTvShow(tvShowId, object : RemoteDataSource.LoadCastTvShowCallback {
            override fun onAllCastTvShowReceived(CastTvShowResponse: List<CastTvShowResponse>) {
                val castTvShowList = ArrayList<TvShowCastEntity>()
                for (response in CastTvShowResponse) {
                    val castTvShow = TvShowCastEntity(
                        response.castId,
                        response.castMovieName,
                        response.castRealName,
                        response.castImage
                    )
                    castTvShowList.add(castTvShow)
                }
                castTvShowResults.postValue(castTvShowList)
            }
        })

        return castTvShowResults
    }

    override fun getTvShowWithCast(tvShowId: String): LiveData<TvShowEntity> {
        val tvShowWithCastResults = MutableLiveData<TvShowEntity>()
        lateinit var tvShow: TvShowEntity
        remoteDataSource.getAllTvShow(object : RemoteDataSource.LoadTvShowCallback {
            override fun onAllTvShowReceived(tvShowResponse: List<TvShowResponse>) {
                for (response in tvShowResponse) {
                    if (response.tvShowId == tvShowId) {
                        tvShow = TvShowEntity(
                            response.tvShowId,
                            response.title,
                            response.description,
                            response.genre1,
                            response.genre2,
                            response.episode,
                            response.season,
                            response.duration,
                            response.releaseDate,
                            response.imagePath,
                            response.rating
                        )
                    }
                }
                tvShowWithCastResults.postValue(tvShow)
            }
        })

        return tvShowWithCastResults
    }

    override fun getAllTvShowByCast(tvShowId: String): LiveData<List<TvShowCastEntity>> {
        val tvShowByCastResults = MutableLiveData<List<TvShowCastEntity>>()
        remoteDataSource.getCastTvShow(tvShowId, object : RemoteDataSource.LoadCastTvShowCallback {
            override fun onAllCastTvShowReceived(CastTvShowResponse: List<CastTvShowResponse>) {
                val castTvShowList = ArrayList<TvShowCastEntity>()
                for (response in CastTvShowResponse) {
                    val castTvShow = TvShowCastEntity(
                        response.castId,
                        response.castMovieName,
                        response.castRealName,
                        response.castImage
                    )
                    castTvShowList.add(castTvShow)
                }
                tvShowByCastResults.postValue(castTvShowList)
            }
        })

        return tvShowByCastResults
    }
}