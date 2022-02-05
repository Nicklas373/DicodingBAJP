package com.dicoding.moviecatalog.data.source.local.entity.tvshow

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.*

@Entity(tableName = "tvShow_detail_entity")
data class TvShowDetailEntity(

    @ColumnInfo(name = "first_air_date")
    val tvShowFirstAirDate: String,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvShowId")
    val tvShowId: Int,

    @ColumnInfo(name = "title")
    val tvShowName: String,

    @ColumnInfo(name = "number_of_episodes")
    val tvShowEpisodes: Int,

    @ColumnInfo(name = "number_of_seasons")
    val tvShowSeasons: Int,

    @ColumnInfo(name = "original_language")
    val tvShowLanguage: String,

    @ColumnInfo(name = "overview")
    val tvShowOverview: String,

    @ColumnInfo(name = "poster_path")
    val tvShowPoster: String,

    @ColumnInfo(name = "vote_average")
    val tvShowVote: Double,

    @ColumnInfo(name = "popularity")
    val tvShowPopularity: String,

    @ColumnInfo(name = "genres_1")
    val tvShowGenres_1: String,

    @ColumnInfo(name = "genres_2")
    val tvShowGenres_2: String,

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
    val compOrigin_2: String,

    @ColumnInfo(name = "is_sus")
    var isSus: Boolean = false
)