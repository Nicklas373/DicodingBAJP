package com.dicoding.moviecatalog.callback

import com.dicoding.moviecatalog.data.MovieEntity

interface TvShowCallback {
    fun onShareClick(movie: MovieEntity)
}