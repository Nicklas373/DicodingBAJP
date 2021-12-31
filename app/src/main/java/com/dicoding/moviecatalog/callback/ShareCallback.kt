package com.dicoding.moviecatalog.callback

import com.dicoding.moviecatalog.data.movie.MovieEntity
import com.dicoding.moviecatalog.data.tvshow.TvShowEntity

interface ShareCallback {
    fun onShareClickMovie(movie: MovieEntity)
    fun onShareClickTvShow(tvShow: TvShowEntity)
}