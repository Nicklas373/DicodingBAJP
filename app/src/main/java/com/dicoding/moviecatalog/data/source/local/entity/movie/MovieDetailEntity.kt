package com.dicoding.moviecatalog.data.source.local.entity.movie

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_detail_entity")
data class MovieDetailEntity(

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "original_language")
    val originalLanguage: String,

    @ColumnInfo(name = "original_title")
    val originalTitle: String,

    @ColumnInfo(name = "genres_1")
    val genres_1: String,

    @ColumnInfo(name = "genres_2")
    val genres_2: String,

    @ColumnInfo(name = "revenue")
    val revenue: Int,

    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @ColumnInfo(name = "popularity")
    val popularity: Double,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    val movieId: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @ColumnInfo(name = "production_companies_name_1")
    val compName_1: String,

    @ColumnInfo(name = "production_companies_name_2")
    val compName_2: String,

    @ColumnInfo(name = "production_companies_logo_1")
    val compLogo_1: String,

    @ColumnInfo(name = "production_companies_logo_2")
    val compLogo_2: String,

    @ColumnInfo(name = "production_companies_origin_1")
    val compOrigin_1: String,

    @ColumnInfo(name = "production_companies_origin_2")
    val compOrigin_2: String
)