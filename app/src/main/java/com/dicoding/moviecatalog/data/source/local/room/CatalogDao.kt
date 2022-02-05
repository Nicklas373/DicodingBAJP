package com.dicoding.moviecatalog.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieDetailEntity
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieListEntity
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowDetailEntity
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowListEntity

@Dao
interface CatalogDao {
    @RawQuery(observedEntities = [MovieListEntity::class])
    fun getMovies(query: SimpleSQLiteQuery): DataSource.Factory<Int, MovieListEntity>

    @Query("SELECT * FROM MOVIE_DETAIL_ENTITY WHERE movieId = :movieId")
    fun getSelectedMovies(movieId: Int): LiveData<MovieDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMoviesList(movies: List<MovieListEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMoviesDetails(movies: MovieDetailEntity)

    @RawQuery(observedEntities = [MovieDetailEntity::class])
    fun getFavMovies(query: SimpleSQLiteQuery): DataSource.Factory<Int, MovieDetailEntity>

    @Update
    fun updateFavMovies(movies: MovieDetailEntity)

    @RawQuery(observedEntities = [MovieListEntity::class])
    fun getTvShow(query: SimpleSQLiteQuery): DataSource.Factory<Int, TvShowListEntity>

    @Query("SELECT * FROM tvShow_detail_entity where tvShowId = :tvShowId")
    fun getSelectedTvShow(tvShowId: Int): LiveData<TvShowDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShowList(tvShow: List<TvShowListEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShowDetails(tvShow: TvShowDetailEntity)

    @RawQuery(observedEntities = [TvShowDetailEntity::class])
    fun getFavTvShow(query: SimpleSQLiteQuery): DataSource.Factory<Int, TvShowDetailEntity>

    @Update
    fun updateFavTvShow(tvShow: TvShowDetailEntity)
}