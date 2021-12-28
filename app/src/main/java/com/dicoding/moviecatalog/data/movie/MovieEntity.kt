package com.dicoding.moviecatalog.data.movie

data class MovieEntity(
    var movieId: String,
    var title: String,
    var description: String,
    var duration: String,
    var releaseDate: String,
    var imagePath: String,
    var rating: String
)