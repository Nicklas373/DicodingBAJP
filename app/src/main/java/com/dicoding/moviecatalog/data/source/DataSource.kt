package com.dicoding.moviecatalog.data.source

import androidx.lifecycle.LiveData
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieDetailEntity
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieListEntity
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowDetailEntity
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowListEntity
import com.dicoding.moviecatalog.vo.Resource

interface DataSource {

    fun getAllMovies(listId: String): LiveData<Resource<List<MovieListEntity>>>

    fun getSelectedMovies(movieId: Int): LiveData<Resource<MovieDetailEntity>>

    fun getAllTvShow(listId: String): LiveData<Resource<List<TvShowListEntity>>>

    fun getSelectedTvShow(tvShowId: Int): LiveData<Resource<TvShowDetailEntity>>
}