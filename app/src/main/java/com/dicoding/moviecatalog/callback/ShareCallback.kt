package com.dicoding.moviecatalog.callback

import com.dicoding.moviecatalog.data.movie.MovieEntity

interface ShareCallback {
    fun onShareClick(movie: MovieEntity)
}