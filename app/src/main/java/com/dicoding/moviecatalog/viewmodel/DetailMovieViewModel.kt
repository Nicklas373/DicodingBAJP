package com.dicoding.moviecatalog.viewmodel

import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalog.data.movie.CastEntity
import com.dicoding.moviecatalog.data.movie.MovieEntity
import com.dicoding.moviecatalog.utils.CatalogDatabase

class DetailMovieViewModel : ViewModel() {
    private lateinit var movieId: String

    fun setSelectedMovie(movieId: String) {
        this.movieId = movieId
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

    fun getCastMovie1(): List<CastEntity> = CatalogDatabase.generateCastListMovie1()
    fun getCastMovie2(): List<CastEntity> = CatalogDatabase.generateCastListMovie2()
    fun getCastMovie3(): List<CastEntity> = CatalogDatabase.generateCastListMovie3()
    fun getCastMovie4(): List<CastEntity> = CatalogDatabase.generateCastListMovie4()
    fun getCastMovie5(): List<CastEntity> = CatalogDatabase.generateCastListMovie5()
    fun getCastMovie6(): List<CastEntity> = CatalogDatabase.generateCastListMovie6()
    fun getCastMovie7(): List<CastEntity> = CatalogDatabase.generateCastListMovie7()
    fun getCastMovie8(): List<CastEntity> = CatalogDatabase.generateCastListMovie8()
    fun getCastMovie9(): List<CastEntity> = CatalogDatabase.generateCastListMovie9()
    fun getCastMovie10(): List<CastEntity> = CatalogDatabase.generateCastListMovie10()
}