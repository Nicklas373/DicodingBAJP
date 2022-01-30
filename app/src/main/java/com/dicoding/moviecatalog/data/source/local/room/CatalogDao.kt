package com.dicoding.moviecatalog.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieDetailEntity
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieListEntity
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowDetailEntity
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowListEntity

@Dao
interface CatalogDao {
    @Query("SELECT * FROM MOVIE_LIST_ENTITY")
    fun getMovies(): LiveData<List<MovieListEntity>>

    @Query("SELECT * FROM MOVIE_DETAIL_ENTITY WHERE movieId = :movieId")
    fun getSelectedMovies(movieId: Int): LiveData<MovieDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMoviesList(movies: List<MovieListEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMoviesDetails(movies: MovieDetailEntity)

    @Query("SELECT * FROM tvShow_list_entity")
    fun getTvShow(): LiveData<List<TvShowListEntity>>

    @Query("SELECT * FROM tvShow_detail_entity where tvShowId = :tvShowId")
    fun getSelectedTvShow(tvShowId: Int): LiveData<TvShowDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShowList(tvShow: List<TvShowListEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShowDetails(tvShow: TvShowDetailEntity)
}