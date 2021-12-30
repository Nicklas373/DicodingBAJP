package com.dicoding.moviecatalog.viewmodel

import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalog.data.movie.MovieCastEntity
import com.dicoding.moviecatalog.data.movie.MovieEntity
import com.dicoding.moviecatalog.data.tvshow.TvShowCastEntity
import com.dicoding.moviecatalog.data.tvshow.TvShowEntity
import com.dicoding.moviecatalog.utils.CatalogDatabase

class DetailMovieViewModel : ViewModel() {
    private lateinit var movieId: String
    private lateinit var tvShowId: String

    fun setSelectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun setSelectedTvShow(tvShowId: String) {
        this.tvShowId = tvShowId
    }

    fun getMovie(): MovieEntity {
        lateinit var movie: MovieEntity
        val movieEntities = CatalogDatabase.generateMovieDatabase()
        for (movieEntity in movieEntities) {
            if (movieEntity.movieId == movieId) {
                movie = movieEntity
            }
        }
        return movie
    }

    fun getTvShow(): TvShowEntity {
        lateinit var tvShow: TvShowEntity
        val tvShowEntities = CatalogDatabase.generateTvShowDatabase()
        for (tvShowEntity in tvShowEntities) {
            if (tvShowEntity.tvShowId == tvShowId) {
                tvShow = tvShowEntity
            }
        }
        return tvShow
    }

    fun getCastMovie1(): List<MovieCastEntity> = CatalogDatabase.generateCastListMovie1()
    fun getCastMovie2(): List<MovieCastEntity> = CatalogDatabase.generateCastListMovie2()
    fun getCastMovie3(): List<MovieCastEntity> = CatalogDatabase.generateCastListMovie3()
    fun getCastMovie4(): List<MovieCastEntity> = CatalogDatabase.generateCastListMovie4()
    fun getCastMovie5(): List<MovieCastEntity> = CatalogDatabase.generateCastListMovie5()
    fun getCastMovie6(): List<MovieCastEntity> = CatalogDatabase.generateCastListMovie6()
    fun getCastMovie7(): List<MovieCastEntity> = CatalogDatabase.generateCastListMovie7()
    fun getCastMovie8(): List<MovieCastEntity> = CatalogDatabase.generateCastListMovie8()
    fun getCastMovie9(): List<MovieCastEntity> = CatalogDatabase.generateCastListMovie9()
    fun getCastMovie10(): List<MovieCastEntity> = CatalogDatabase.generateCastListMovie10()
    fun getCastTvShow1(): List<TvShowCastEntity> = CatalogDatabase.generateCastListTvShow1()
    fun getCastTvShow2(): List<TvShowCastEntity> = CatalogDatabase.generateCastListTvShow2()
    fun getCastTvShow3(): List<TvShowCastEntity> = CatalogDatabase.generateCastListTvShow3()
    fun getCastTvShow4(): List<TvShowCastEntity> = CatalogDatabase.generateCastListTvShow4()
    fun getCastTvShow5(): List<TvShowCastEntity> = CatalogDatabase.generateCastListTvShow5()
    fun getCastTvShow6(): List<TvShowCastEntity> = CatalogDatabase.generateCastListTvShow6()
    fun getCastTvShow7(): List<TvShowCastEntity> = CatalogDatabase.generateCastListTvShow7()
    fun getCastTvShow8(): List<TvShowCastEntity> = CatalogDatabase.generateCastListTvShow8()
    fun getCastTvShow9(): List<TvShowCastEntity> = CatalogDatabase.generateCastListTvShow9()
    fun getCastTvShow10(): List<TvShowCastEntity> = CatalogDatabase.generateCastListTvShow10()
}