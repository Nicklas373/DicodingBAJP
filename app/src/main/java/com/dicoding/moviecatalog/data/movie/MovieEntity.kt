package com.dicoding.moviecatalog.data.movie

data class MovieEntity(
    val movieId: String,
    val title: String,
    val genre1: String,
    val genre2: String,
    val description: String,
    val duration: String,
    val releaseDate: String,
    val imagePath: String,
    val rating: String
)