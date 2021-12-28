package com.dicoding.moviecatalog.utils

import com.dicoding.moviecatalog.data.movie.MovieEntity
import com.dicoding.moviecatalog.data.movie.MovieModuleEntity
import com.dicoding.moviecatalog.data.tvshow.TvShowEntity

object MovieDatabase {
    fun generateMovieDatabase(): List<MovieEntity> {

        val movie = ArrayList<MovieEntity>()

        movie.add(
            MovieEntity(
                "1",
                "A Star Is Born",
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                "2 Hours, 16 Minute",
                "October 3, 2018",
                "https://raw.githubusercontent.com/Nicklas373/DicodingBAJP-Asset/main/movies/poster_a_start_is_born.jpg",
                "7.5"
            )
        )
        movie.add(
            MovieEntity(
                "2",
                "Alita: Battle Angel",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "2 Hours, 2 Minute",
                "January 31, 2019",
                "https://raw.githubusercontent.com/Nicklas373/DicodingBAJP-Asset/main/movies/poster_alita.jpg",
                "7.2"
            )
        )
        movie.add(
            MovieEntity(
                "3",
                "Aquaman",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "2 Hours, 23 Minute",
                "July 6, 2018",
                "https://raw.githubusercontent.com/Nicklas373/DicodingBAJP-Asset/main/movies/poster_aquaman.jpg",
                "6.9"
            )
        )
        movie.add(
            MovieEntity(
                "4",
                "Bohemian Rhapsody",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "2 Hours, 15 Minute",
                "October 24, 2018",
                "https://raw.githubusercontent.com/Nicklas373/DicodingBAJP-Asset/main/movies/poster_bohemian.jpg",
                "8.0"
            )
        )
        movie.add(
            MovieEntity(
                "5",
                "Cold Pursuit",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "1 Hours, 59 Minute",
                "February 7, 2019",
                "https://raw.githubusercontent.com/Nicklas373/DicodingBAJP-Asset/main/movies/poster_cold_persuit.jpg",
                "5.7"
            )
        )

        return movie
    }

    fun generateMovieDetails(movieId: String): List<MovieModuleEntity> {

        val movieModule = ArrayList<MovieModuleEntity>()

        movieModule.add(
            MovieModuleEntity(
                "{$movieId}m1",
                movieId,
                "Modul 0 : Introduction",
                0, false
            )
        )
        movieModule.add(
            MovieModuleEntity(
                "{$movieId}m2",
                movieId,
                "Modul 1 : Teori 1",
                1, false
            )
        )
        movieModule.add(
            MovieModuleEntity(
                "{$movieId}m3",
                movieId,
                "Latihan 1",
                2, false
            )
        )
        movieModule.add(
            MovieModuleEntity(
                "{$movieId}m4",
                movieId,
                "Bedah Kode 1",
                3, false
            )
        )
        movieModule.add(
            MovieModuleEntity(
                "{$movieId}m5",
                movieId,
                "Modul 2 : Teori 2",
                4, false
            )
        )
        movieModule.add(
            MovieModuleEntity(
                "{$movieId}m6",
                movieId,
                "Latihan 2",
                5, false
            )
        )
        movieModule.add(
            MovieModuleEntity(
                "{$movieId}m7",
                movieId,
                "Bedah Kode 2",
                6, false
            )
        )

        return movieModule
    }

    fun generateTvShowDatabase(): List<TvShowEntity> {

        val tvShow = ArrayList<TvShowEntity>()

        tvShow.add(
            TvShowEntity(
                "1",
                "Arrow",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "42 Minute",
                "October 10, 2012",
                "https://raw.githubusercontent.com/Nicklas373/DicodingBAJP-Asset/main/tv_shows/poster_arrow.jpg",
                "6.7"
            )
        )

        return tvShow
    }

}