package com.dicoding.moviecatalog.api

import com.dicoding.moviecatalog.data.movie.response.MovieGenreResponse
import com.dicoding.moviecatalog.data.movie.response.MovieListResponse
import com.dicoding.moviecatalog.data.movie.response.MovieResponse
import com.dicoding.moviecatalog.data.tvshow.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("list/{list_id}?api_key=138cf97267100e481f54f6ac6dad2f8c")
    fun getMovieList(
        @Path("list_id") listId: String
    ): Call<MovieResponse>

    @GET("list/{list_id}?api_key=138cf97267100e481f54f6ac6dad2f8c")
    fun getTvShowList(
        @Path("list_id") listId: String
    ): Call<TvShowResponse>

    @GET("movie/{movie_id}?api_key=138cf97267100e481f54f6ac6dad2f8c")
    fun getMovieDetail(
        @Path("movie_id") movieId: String
    ): Call<MovieListResponse>

    @GET("movie/{movie_id}?api_key=138cf97267100e481f54f6ac6dad2f8c")
    fun getMovieGenreList(
        @Path("movie_id") movieId: String
    ): Call<MovieGenreResponse>
}