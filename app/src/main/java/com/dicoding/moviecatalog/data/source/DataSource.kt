package com.dicoding.moviecatalog.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieDetailEntity
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieListEntity
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowDetailEntity
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowListEntity
import com.dicoding.moviecatalog.vo.Resource

interface DataSource {

    fun getAllMovies(listId: String): LiveData<Resource<PagedList<MovieListEntity>>>

    fun getSelectedMovies(movieId: Int): LiveData<Resource<MovieDetailEntity>>

    fun getFavoriteMovies(): LiveData<PagedList<MovieDetailEntity>>

    fun updateFavMovies(movie: MovieDetailEntity, isSus: Boolean)

    fun getAllTvShow(listId: String): LiveData<Resource<PagedList<TvShowListEntity>>>

    fun getSelectedTvShow(tvShowId: Int): LiveData<Resource<TvShowDetailEntity>>

    fun getFavoriteTvShow(): LiveData<PagedList<TvShowDetailEntity>>

    fun updateFavTvShow(tvShow: TvShowDetailEntity, isSus: Boolean)
}