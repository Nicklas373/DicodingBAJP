package com.dicoding.moviecatalog.api

import com.dicoding.moviecatalog.BuildConfig
import com.dicoding.moviecatalog.data.source.remote.response.movie.MovieListResponse
import com.dicoding.moviecatalog.data.source.remote.response.movie.MovieResponse
import com.dicoding.moviecatalog.data.source.remote.response.tvshow.TvShowListResponse
import com.dicoding.moviecatalog.data.source.remote.response.tvshow.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("list/{list_id}?api_key=${apiKEY}")
    fun getMovieList(
        @Path("list_id") listId: String
    ): Call<MovieResponse>

    @GET("movie/{movie_id}?api_key=${apiKEY}")
    fun getSelectedMovie(
        @Path("movie_id") movieId: Int
    ): Call<MovieListResponse>

    @GET("list/{list_id}?api_key=${apiKEY}")
    fun getTvShowList(
        @Path("list_id") listId: String
    ): Call<TvShowResponse>

    @GET("tv/{tv_id}?api_key=${apiKEY}")
    fun getSelectedTvShow(
        @Path("tv_id") tvId: Int
    ): Call<TvShowListResponse>

    companion object {
        const val apiKEY: String = BuildConfig.KEY
    }
}