package com.dicoding.moviecatalog.utils

import com.dicoding.moviecatalog.data.source.remote.response.ProductionCompaniesListResponse
import com.dicoding.moviecatalog.data.source.remote.response.movie.MovieGenreListResponse
import com.dicoding.moviecatalog.data.source.remote.response.movie.MovieListResponse
import com.dicoding.moviecatalog.data.source.remote.response.tvshow.TvShowGenreListResponse
import com.dicoding.moviecatalog.data.source.remote.response.tvshow.TvShowListResponse

class Helper {

    fun loadMovies(): ArrayList<MovieListResponse> {
        val resultAllMovies = ArrayList<MovieListResponse>()

        for (movie in resultAllMovies) {
            val movieListResponse = MovieListResponse(
                movie.overview,
                movie.originalLanguage,
                movie.revenue,
                movie.originalTitle,
                movie.releaseDate,
                movie.popularity,
                movie.voteAverage,
                movie.id,
                movie.title,
                movie.posterPath
            )
            resultAllMovies.add(movieListResponse)
        }
        return resultAllMovies
    }

    fun loadCompaniesMovies(): ArrayList<ProductionCompaniesListResponse> {
        val resultCompaniesMovies = ArrayList<ProductionCompaniesListResponse>()

        for (company in resultCompaniesMovies) {
            val companiesListResponse = ProductionCompaniesListResponse(
                company.logoPath,
                company.name,
                company.id,
                company.originCountry
            )
            resultCompaniesMovies.add(companiesListResponse)
        }
        return resultCompaniesMovies
    }

    fun loadGenresMovies(): ArrayList<MovieGenreListResponse> {
        val resultGenreMovies = ArrayList<MovieGenreListResponse>()

        for (genre in resultGenreMovies) {
            val genreListResponse = MovieGenreListResponse(
                genre.id,
                genre.name
            )
            resultGenreMovies.add(genreListResponse)
        }
        return resultGenreMovies
    }

    fun loadTvShow(): ArrayList<TvShowListResponse> {
        val resultAllTvShow = ArrayList<TvShowListResponse>()

        for (tvShow in resultAllTvShow) {
            val tvShowListResponse = TvShowListResponse(
                tvShow.tvShowFirstAirDate,
                tvShow.tvShowId,
                tvShow.tvShowName,
                tvShow.tvShowEpisodes,
                tvShow.tvShowSeasons,
                tvShow.tvShowLanguage,
                tvShow.tvShowOverview,
                tvShow.tvShowPoster,
                tvShow.tvShowVote,
                tvShow.tvShowPopularity
            )
            resultAllTvShow.add(tvShowListResponse)
        }
        return resultAllTvShow
    }

    fun loadCompaniesTvShow(): ArrayList<ProductionCompaniesListResponse> {
        val resultCompaniesTvShow = ArrayList<ProductionCompaniesListResponse>()

        for (company in resultCompaniesTvShow) {
            val companiesListResponse = ProductionCompaniesListResponse(
                company.logoPath,
                company.name,
                company.id,
                company.originCountry
            )
            resultCompaniesTvShow.add(companiesListResponse)
        }
        return resultCompaniesTvShow
    }

    fun loadGenresTvShow(): ArrayList<TvShowGenreListResponse> {
        val resultGenreTvShow = ArrayList<TvShowGenreListResponse>()

        for (genre in resultGenreTvShow) {
            val genreListResponse = TvShowGenreListResponse(
                genre.id,
                genre.name
            )
            resultGenreTvShow.add(genreListResponse)
        }
        return resultGenreTvShow
    }
}