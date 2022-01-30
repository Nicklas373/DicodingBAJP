package com.dicoding.moviecatalog.data.source

import androidx.lifecycle.LiveData
import com.dicoding.moviecatalog.data.source.local.LocalDataSource
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieDetailEntity
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieListEntity
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowDetailEntity
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowListEntity
import com.dicoding.moviecatalog.data.source.remote.ApiResponse
import com.dicoding.moviecatalog.data.source.remote.RemoteDataSource
import com.dicoding.moviecatalog.data.source.remote.response.movie.MovieListResponse
import com.dicoding.moviecatalog.data.source.remote.response.tvshow.TvShowListResponse
import com.dicoding.moviecatalog.utils.AppExecutors
import com.dicoding.moviecatalog.vo.Resource

class Repository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    DataSource {

    override fun getAllMovies(listId: String): LiveData<Resource<List<MovieListEntity>>> {
        return object :
            NetworkBoundResource<List<MovieListEntity>, ArrayList<MovieListResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<MovieListEntity>> =
                localDataSource.getAllMovies()

            override fun shouldFetch(data: List<MovieListEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<ArrayList<MovieListResponse>>> =
                remoteDataSource.getAllMovies(listId)

            public override fun saveCallResult(data: ArrayList<MovieListResponse>) {
                val movieList = ArrayList<MovieListEntity>()
                for (response in data) {
                    val movie = MovieListEntity(
                        response.releaseDate,
                        response.id,
                        response.title,
                        response.posterPath,
                        response.voteAverage
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getSelectedMovies(movieId: Int): LiveData<Resource<MovieDetailEntity>> {
        return object :
            NetworkBoundResource<MovieDetailEntity, MovieListResponse>(appExecutors) {
            public override fun loadFromDB(): LiveData<MovieDetailEntity> =
                localDataSource.getSelectedMovies(movieId)

            override fun shouldFetch(data: MovieDetailEntity?): Boolean =
                data == null || data.equals(null)

            public override fun createCall(): LiveData<ApiResponse<MovieListResponse>> =
                remoteDataSource.getSelectedMovies(movieId)

            public override fun saveCallResult(data: MovieListResponse) {
                val genres1 = StringBuilder().append("")
                val genres2 = StringBuilder().append("")
                val compName1 = StringBuilder().append("")
                val compName2 = StringBuilder().append("")
                val compLogo1 = StringBuilder().append("")
                val compLogo2 = StringBuilder().append("")
                val compOrigin1 = StringBuilder().append("")
                val compOrigin2 = StringBuilder().append("")

                if (data.genres.size > 1) {
                    genres1.append(data.genres[0].name)
                    genres2.append(data.genres[1].name)
                } else {
                    genres1.append(data.genres[0].name)
                    genres2.append("null")
                }

                if (data.productionCompanies.size > 1) {
                    compName1.append(data.productionCompanies[0].name)
                    compLogo1.append(data.productionCompanies[0].logoPath)
                    compOrigin1.append(data.productionCompanies[0].originCountry)
                    compName2.append(data.productionCompanies[1].name)
                    compLogo2.append(data.productionCompanies[1].logoPath)
                    compOrigin2.append(data.productionCompanies[1].originCountry)
                } else {
                    compName1.append(data.productionCompanies[0].name)
                    compLogo1.append(data.productionCompanies[0].logoPath)
                    compOrigin1.append(data.productionCompanies[0].originCountry)
                    compName2.append("null")
                    compLogo2.append("null")
                    compOrigin2.append("null")
                }

                val movie = MovieDetailEntity(
                    data.overview,
                    data.originalLanguage,
                    data.originalTitle,
                    genres1.toString(),
                    genres2.toString(),
                    data.revenue,
                    data.releaseDate,
                    data.popularity,
                    data.voteAverage,
                    data.id,
                    data.title,
                    data.posterPath,
                    compName1.toString(),
                    compName2.toString(),
                    compLogo1.toString(),
                    compLogo2.toString(),
                    compOrigin1.toString(),
                    compOrigin2.toString()
                )
                localDataSource.insertMoviesDetails(movie)
            }
        }.asLiveData()
    }

    override fun getAllTvShow(listId: String): LiveData<Resource<List<TvShowListEntity>>> {
        return object :
            NetworkBoundResource<List<TvShowListEntity>, ArrayList<TvShowListResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<TvShowListEntity>> =
                localDataSource.getAllTvShow()

            override fun shouldFetch(data: List<TvShowListEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<ArrayList<TvShowListResponse>>> =
                remoteDataSource.getAllTvShow(listId)

            public override fun saveCallResult(data: ArrayList<TvShowListResponse>) {
                val tvShowList = ArrayList<TvShowListEntity>()
                for (response in data) {
                    val tvShow = TvShowListEntity(
                        response.tvShowFirstAirDate,
                        response.tvShowId,
                        response.tvShowName,
                        response.tvShowPoster,
                        response.tvShowVote
                    )
                    tvShowList.add(tvShow)
                }
                localDataSource.insertTvShow(tvShowList)
            }

        }.asLiveData()
    }

    override fun getSelectedTvShow(tvShowId: Int): LiveData<Resource<TvShowDetailEntity>> {
        return object :
            NetworkBoundResource<TvShowDetailEntity, TvShowListResponse>(appExecutors) {
            public override fun loadFromDB(): LiveData<TvShowDetailEntity> =
                localDataSource.getSelectedTvShow(tvShowId)

            override fun shouldFetch(data: TvShowDetailEntity?): Boolean =
                data == null || data.equals(null)

            public override fun createCall(): LiveData<ApiResponse<TvShowListResponse>> =
                remoteDataSource.getSelectedTvShow(tvShowId)

            public override fun saveCallResult(data: TvShowListResponse) {
                val genres1 = StringBuilder().append("")
                val genres2 = StringBuilder().append("")
                val compName1 = StringBuilder().append("")
                val compName2 = StringBuilder().append("")
                val compLogo1 = StringBuilder().append("")
                val compLogo2 = StringBuilder().append("")
                val compOrigin1 = StringBuilder().append("")
                val compOrigin2 = StringBuilder().append("")

                if (data.tvShowGenres.size > 1) {
                    genres1.append(data.tvShowGenres[0].name)
                    genres2.append(data.tvShowGenres[1].name)
                } else {
                    genres1.append(data.tvShowGenres[0].name)
                    genres2.append("null")
                }

                if (data.tvShowProductionCompanies.size > 1) {
                    compName1.append(data.tvShowProductionCompanies[0].name)
                    compLogo1.append(data.tvShowProductionCompanies[0].logoPath)
                    compOrigin1.append(data.tvShowProductionCompanies[0].originCountry)
                    compName2.append(data.tvShowProductionCompanies[1].name)
                    compLogo2.append(data.tvShowProductionCompanies[1].logoPath)
                    compOrigin2.append(data.tvShowProductionCompanies[1].originCountry)
                } else {
                    compName1.append(data.tvShowProductionCompanies[0].name)
                    compLogo1.append(data.tvShowProductionCompanies[0].logoPath)
                    compOrigin1.append(data.tvShowProductionCompanies[0].originCountry)
                    compName2.append("null")
                    compLogo2.append("null")
                    compOrigin2.append("null")
                }

                val tvShow = TvShowDetailEntity(
                    data.tvShowFirstAirDate,
                    data.tvShowId,
                    data.tvShowName,
                    data.tvShowEpisodes,
                    data.tvShowSeasons,
                    data.tvShowLanguage,
                    data.tvShowOverview,
                    data.tvShowPoster,
                    data.tvShowVote,
                    data.tvShowPopularity,
                    genres1.toString(),
                    genres2.toString(),
                    compName1.toString(),
                    compName2.toString(),
                    compLogo1.toString(),
                    compLogo2.toString(),
                    compOrigin1.toString(),
                    compOrigin2.toString()
                )
                localDataSource.insertTvShowDetails(tvShow)
            }
        }.asLiveData()
    }

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteData, localData, appExecutors).apply {
                    instance = this
                }
            }
    }
}