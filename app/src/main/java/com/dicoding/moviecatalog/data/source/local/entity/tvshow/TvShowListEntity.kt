package com.dicoding.moviecatalog.data.source.local.entity.tvshow

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.*

@Entity(tableName = "tvShow_list_entity")
data class TvShowListEntity(

    @ColumnInfo(name = "first_air_date")
    val tvShowFirstAirDate: String,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvShowId")
    val tvShowId: Int,

    @ColumnInfo(name = "title")
    val tvShowName: String,

    @ColumnInfo(name = "poster_path")
    val tvShowPoster: String,

    @ColumnInfo(name = "vote_average")
    val tvShowVote: Double
)