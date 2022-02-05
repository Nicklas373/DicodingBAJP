package com.dicoding.moviecatalog.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieDetailEntity
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieListEntity
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowDetailEntity
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowListEntity
import com.dicoding.moviecatalog.data.source.local.room.CatalogDao
import com.dicoding.moviecatalog.utils.SortUtils
import com.dicoding.moviecatalog.utils.SortUtils.MOVIE_ENTITIES
import com.dicoding.moviecatalog.utils.SortUtils.MOVIE_FAV_ENTITIES
import com.dicoding.moviecatalog.utils.SortUtils.TVSHOW_ENTITIES
import com.dicoding.moviecatalog.utils.SortUtils.TVSHOW_FAV_ENTITIES

class LocalDataSource private constructor(private val mCatalogDao: CatalogDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(catalogDao: CatalogDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(catalogDao)
    }

    fun getAllMovies(sort: String): DataSource.Factory<Int, MovieListEntity> = mCatalogDao.getMovies(SortUtils.getSortedQuery(sort, MOVIE_ENTITIES))

    fun getSelectedMovies(movieId: Int): LiveData<MovieDetailEntity> =
        mCatalogDao.getSelectedMovies(movieId)

    fun insertMovies(movie: List<MovieListEntity>) = mCatalogDao.insertMoviesList(movie)

    fun insertMoviesDetails(movie: MovieDetailEntity) = mCatalogDao.insertMoviesDetails(movie)

    fun getFavMovies(sort: String): DataSource.Factory<Int, MovieDetailEntity> = mCatalogDao.getFavMovies(SortUtils.getSortedQueryFav(sort, MOVIE_FAV_ENTITIES))

    fun updateFavMovies(movie: MovieDetailEntity, isSus: Boolean) {
        movie.isSus = isSus
        mCatalogDao.updateFavMovies(movie)
    }

    fun getAllTvShow(sort: String): DataSource.Factory<Int, TvShowListEntity> = mCatalogDao.getTvShow(SortUtils.getSortedQuery(sort, TVSHOW_ENTITIES))

    fun getSelectedTvShow(tvShowId: Int): LiveData<TvShowDetailEntity> =
        mCatalogDao.getSelectedTvShow(tvShowId)

    fun insertTvShow(tvShow: List<TvShowListEntity>) = mCatalogDao.insertTvShowList(tvShow)

    fun insertTvShowDetails(tvShow: TvShowDetailEntity) = mCatalogDao.insertTvShowDetails(tvShow)

    fun getFavTvShow(sort: String): DataSource.Factory<Int, TvShowDetailEntity> = mCatalogDao.getFavTvShow(SortUtils.getSortedQueryFav(sort, TVSHOW_FAV_ENTITIES))

    fun updateFavTvShow(tvShow: TvShowDetailEntity, isSus: Boolean) {
        tvShow.isSus = isSus
        mCatalogDao.updateFavTvShow(tvShow)
    }
}