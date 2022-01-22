package com.dicoding.moviecatalog.api

import com.dicoding.moviecatalog.BuildConfig
import com.dicoding.moviecatalog.api.ApiService.Companion.apiKEY
import com.dicoding.moviecatalog.data.source.remote.response.ProductionCompaniesResponse
import com.dicoding.moviecatalog.data.source.remote.response.movie.MovieGenreResponse
import com.dicoding.moviecatalog.data.source.remote.response.movie.MovieListResponse
import com.dicoding.moviecatalog.data.source.remote.response.movie.MovieResponse
import com.dicoding.moviecatalog.data.source.remote.response.tvshow.TvShowGenreResponse
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
        @Path("movie_id") movieId: String
    ): Call<MovieListResponse>

    @GET("movie/{movie_id}?api_key=${apiKEY}")
    fun getMovieGenreList(
        @Path("movie_id") movieId: String
    ): Call<MovieGenreResponse>

    @GET("movie/{movie_id}?api_key=${apiKEY}")
    fun getMovieCompaniesList(
        @Path("movie_id") movieId: String
    ): Call<ProductionCompaniesResponse>

    @GET("list/{list_id}?api_key=${apiKEY}")
    fun getTvShowList(
        @Path("list_id") listId: String
    ): Call<TvShowResponse>

    @GET("tv/{tv_id}?api_key=${apiKEY}")
    fun getSelectedTvShow(
        @Path("tv_id") tvId: String
    ): Call<TvShowListResponse>

    @GET("tv/{tv_id}?api_key=${apiKEY}")
    fun getTvShowGenreList(
        @Path("tv_id") tvId: String
    ): Call<TvShowGenreResponse>

    @GET("tv/{tv_id}?api_key=${apiKEY}")
    fun getTvShowCompaniesList(
        @Path("tv_id") tvId: String
    ): Call<ProductionCompaniesResponse>

    companion object {
        const val apiKEY: String = BuildConfig.KEY
    }
}