package com.dicoding.moviecatalog.data.source.local

import androidx.lifecycle.LiveData
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieDetailEntity
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieListEntity
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowDetailEntity
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowListEntity
import com.dicoding.moviecatalog.data.source.local.room.CatalogDao

class LocalDataSource private constructor(private val mCatalogDao: CatalogDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(catalogDao: CatalogDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(catalogDao)
    }

    fun getAllMovies(): LiveData<List<MovieListEntity>> = mCatalogDao.getMovies()

    fun getSelectedMovies(movieId: Int): LiveData<MovieDetailEntity> =
        mCatalogDao.getSelectedMovies(movieId)

    fun insertMovies(movie: List<MovieListEntity>) = mCatalogDao.insertMoviesList(movie)

    fun insertMoviesDetails(movie: MovieDetailEntity) = mCatalogDao.insertMoviesDetails(movie)

    fun getAllTvShow(): LiveData<List<TvShowListEntity>> = mCatalogDao.getTvShow()

    fun getSelectedTvShow(tvShowId: Int): LiveData<TvShowDetailEntity> =
        mCatalogDao.getSelectedTvShow(tvShowId)

    fun insertTvShow(tvShow: List<TvShowListEntity>) = mCatalogDao.insertTvShowList(tvShow)

    fun insertTvShowDetails(tvShow: TvShowDetailEntity) = mCatalogDao.insertTvShowDetails(tvShow)
}