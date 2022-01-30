package com.dicoding.moviecatalog.data.source.local.entity.movie

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.*

@Entity(tableName = "movie_list_entity")
data class MovieListEntity(

    @Nullable
    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    val movieId: Int,

    @Nullable
    @ColumnInfo(name = "title")
    val title: String,

    @Nullable
    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @Nullable
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,
)