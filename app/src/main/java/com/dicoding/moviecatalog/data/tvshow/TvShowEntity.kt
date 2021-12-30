package com.dicoding.moviecatalog.data.tvshow

data class TvShowEntity(
    var tvShowId: String,
    var title: String,
    var description: String,
    var genre1: String,
    var genre2: String,
    var episode: String,
    var season: String,
    var duration: String,
    var releaseDate: String,
    var imagePath: String,
    var rating: String
)