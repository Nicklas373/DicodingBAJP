package com.dicoding.moviecatalog.callback

import com.dicoding.moviecatalog.data.movie.MovieEntity

interface TvShowCallback {
    fun onShareClick(movie: MovieEntity)
}