package com.dicoding.moviecatalog.data.movie.source

import com.dicoding.moviecatalog.data.movie.MovieCastEntity
import com.dicoding.moviecatalog.data.movie.MovieEntity
import com.dicoding.moviecatalog.data.movie.source.remote.RemoteDataSource
import com.dicoding.moviecatalog.data.tvshow.TvShowCastEntity
import com.dicoding.moviecatalog.data.tvshow.TvShowEntity

class Repository private constructor(private val remoteDataSource: RemoteDataSource) :
    DataSource {

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(remoteData: RemoteDataSource): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteData).apply { instance = this }
            }
    }

    override fun getAllMovies(): List<MovieEntity> {
        val movieResponses = remoteDataSource.getAllMovies()
        val movieList = ArrayList<MovieEntity>()
        for (response in movieResponses) {
            val movie = MovieEntity(
                response.movieId,
                response.title,
                response.genre1,
                response.genre2,
                response.description,
                response.duration,
                response.releaseDate,
                response.imagePath,
                response.rating
            )
            movieList.add(movie)
        }
        return movieList
    }

    override fun getCastMovies(movieId: String): List<MovieCastEntity> {
        val castMovieResponses = remoteDataSource.getCastMovies(movieId)
        val castMovieList = ArrayList<MovieCastEntity>()
        for (response in castMovieResponses) {
            val castMovie = MovieCastEntity(
                response.castId,
                response.castMovieName,
                response.castRealName,
                response.castImage
            )
            castMovieList.add(castMovie)
        }
        return castMovieList
    }

    override fun getMovieWithCast(movieId: String): MovieEntity {
        val movieResponses = remoteDataSource.getAllMovies()
        lateinit var movie: MovieEntity
        for (response in movieResponses) {
            if (response.movieId == movieId) {
                movie = MovieEntity(
                    response.movieId,
                    response.title,
                    response.genre1,
                    response.genre2,
                    response.description,
                    response.duration,
                    response.releaseDate,
                    response.imagePath,
                    response.rating
                )
            }
        }
        return movie
    }

    override fun getAllMoviesByCast(movieId: String): ArrayList<MovieCastEntity> {
        val castMovieResponses = remoteDataSource.getCastMovies(movieId)
        val castMovieList = ArrayList<MovieCastEntity>()
        for (response in castMovieResponses) {
            val castMovie = MovieCastEntity(
                response.castId,
                response.castMovieName,
                response.castRealName,
                response.castImage
            )
            castMovieList.add(castMovie)
        }
        return castMovieList
    }

    override fun getAllTvShow(): List<TvShowEntity> {
        val tvShowResponses = remoteDataSource.getAllTvShow()
        val tvShowList = ArrayList<TvShowEntity>()
        for (response in tvShowResponses) {
            val tvShow = TvShowEntity(
                response.tvShowId,
                response.title,
                response.description,
                response.genre1,
                response.genre2,
                response.episode,
                response.season,
                response.duration,
                response.releaseDate,
                response.imagePath,
                response.rating
            )
            tvShowList.add(tvShow)
        }
        return tvShowList
    }

    override fun getCastTvShow(tvShowId: String): List<TvShowCastEntity> {
        val castTvShowResponses = remoteDataSource.getCastTvShow(tvShowId)
        val castTvShowList = ArrayList<TvShowCastEntity>()
        for (response in castTvShowResponses) {
            val castTvShow = TvShowCastEntity(
                response.castId,
                response.castMovieName,
                response.castRealName,
                response.castImage
            )
            castTvShowList.add(castTvShow)
        }
        return castTvShowList
    }

    override fun getTvShowWithCast(tvShowId: String): TvShowEntity {
        val tvShowResponses = remoteDataSource.getAllTvShow()
        lateinit var tvShow: TvShowEntity
        for (response in tvShowResponses) {
            if (response.tvShowId == tvShowId) {
                tvShow = TvShowEntity(
                    response.tvShowId,
                    response.title,
                    response.description,
                    response.genre1,
                    response.genre2,
                    response.episode,
                    response.season,
                    response.duration,
                    response.releaseDate,
                    response.imagePath,
                    response.rating
                )
            }
        }
        return tvShow
    }

    override fun getAllTvShowByCast(tvShowId: String): ArrayList<TvShowCastEntity> {
        val castTvShowResponses = remoteDataSource.getCastTvShow(tvShowId)
        val castTvShowList = ArrayList<TvShowCastEntity>()
        for (response in castTvShowResponses) {
            val castTvShow = TvShowCastEntity(
                response.castId,
                response.castMovieName,
                response.castRealName,
                response.castImage
            )
            castTvShowList.add(castTvShow)
        }
        return castTvShowList
    }
}