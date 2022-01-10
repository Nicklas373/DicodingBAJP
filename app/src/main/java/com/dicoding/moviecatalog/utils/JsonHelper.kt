package com.dicoding.moviecatalog.utils

import android.content.Context
import com.dicoding.moviecatalog.data.movie.source.remote.response.CastMovieResponse
import com.dicoding.moviecatalog.data.movie.source.remote.response.MovieResponse
import com.dicoding.moviecatalog.data.movie.source.remote.response.CastTvShowResponse
import com.dicoding.moviecatalog.data.movie.source.remote.response.TvShowResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadMovie(): List<MovieResponse> {
        val list = ArrayList<MovieResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("MovieResponses.json").toString())
            val listArray = responseObject.getJSONArray("movie")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val movieId = movie.getString("movieId")
                val title = movie.getString("title")
                val genre1 = movie.getString("genre1")
                val genre2 = movie.getString("genre2")
                val description = movie.getString("description")
                val duration = movie.getString("duration")
                val releaseDate = movie.getString("releaseDate")
                val imagePath = movie.getString("imagePath")
                val rating = movie.getString("rating")

                val movieResponse = MovieResponse(
                    movieId,
                    title,
                    genre1,
                    genre2,
                    description,
                    duration,
                    releaseDate,
                    imagePath,
                    rating
                )
                list.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadTvShow(): List<TvShowResponse> {
        val list = ArrayList<TvShowResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("TvShowResponses.json").toString())
            val listArray = responseObject.getJSONArray("tvShow")
            for (i in 0 until listArray.length()) {
                val tvShow = listArray.getJSONObject(i)

                val tvShowId = tvShow.getString("tvShowId")
                val title = tvShow.getString("title")
                val description = tvShow.getString("description")
                val genre1 = tvShow.getString("genre1")
                val genre2 = tvShow.getString("genre2")
                val episode = tvShow.getString("episode")
                val season = tvShow.getString("season")
                val duration = tvShow.getString("duration")
                val releaseDate = tvShow.getString("releaseDate")
                val imagePath = tvShow.getString("imagePath")
                val rating = tvShow.getString("rating")

                val tvShowResponse = TvShowResponse(
                    tvShowId,
                    title,
                    description,
                    genre1,
                    genre2,
                    episode,
                    season,
                    duration,
                    releaseDate,
                    imagePath,
                    rating
                )
                list.add(tvShowResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadCastMovie(movieId: String): List<CastMovieResponse> {
        val fileName = String.format("MovieCast%sResponses.json", movieId)
        val list = ArrayList<CastMovieResponse>()
        try {
            val result = parsingFileToString(fileName)
            if (result != null) {
                val responseObject = JSONObject(result)
                val listArray = responseObject.getJSONArray("movieCast")
                for (i in 0 until listArray.length()) {
                    val movieCastId = listArray.getJSONObject(i)

                    val castId = movieCastId.getString("castId")
                    val castMovieName = movieCastId.getString("castMovieName")
                    val castRealName = movieCastId.getString("castRealName")
                    val castImage = movieCastId.getString("castImage")

                    val castMovieResponse =
                        CastMovieResponse(castId, castMovieName, castRealName, castImage)
                    list.add(castMovieResponse)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadCastTvShow(tvShowId: String): List<CastTvShowResponse> {
        val fileName = String.format("TvShowCast%sResponses.json", tvShowId)
        val list = ArrayList<CastTvShowResponse>()
        try {
            val result = parsingFileToString(fileName)
            if (result != null) {
                val responseObject = JSONObject(result)
                val listArray = responseObject.getJSONArray("tvShowCast")
                for (i in 0 until listArray.length()) {
                    val tvShowCastId = listArray.getJSONObject(i)

                    val castId = tvShowCastId.getString("castId")
                    val castMovieName = tvShowCastId.getString("castMovieName")
                    val castRealName = tvShowCastId.getString("castRealName")
                    val castImage = tvShowCastId.getString("castImage")

                    val castTvShowResponse =
                        CastTvShowResponse(castId, castMovieName, castRealName, castImage)
                    list.add(castTvShowResponse)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }
}