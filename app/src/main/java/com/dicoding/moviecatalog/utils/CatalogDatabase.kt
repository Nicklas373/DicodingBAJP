package com.dicoding.moviecatalog.utils

import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieDetailEntity
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieListEntity
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowDetailEntity
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowListEntity

object CatalogDatabase {
    fun generateMovieLocal(): List<MovieListEntity> {

        val movie = ArrayList<MovieListEntity>()

        movie.add(
            MovieListEntity(
                "2018-10-03",
                332562,
                "A Star Is Born",
                "/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                7.5
            )
        )
        movie.add(
            MovieListEntity(
                "2019-01-31",
                399579,
                "Alita: Battle Angel",
                "/xRWht48C2V8XNfzvPehyClOvDni.jpg",
                7.2
            )
        )
        movie.add(
            MovieListEntity(
                "2018-07-06",
                297802,
                "Aquaman",
                "/xLPffWMhMj1l50ND3KchMjYoKmE.jpg",
                6.9
            )
        )
        movie.add(
            MovieListEntity(
                "2018-10-24",
                424694,
                "Bohemian Rhapsody",
                "/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg",
                8.0
            )
        )
        movie.add(
            MovieListEntity(
                "2019-02-07",
                438650,
                "Cold Pursuit",
                "/hXgmWPd1SuujRZ4QnKLzrj79PAw.jpg",
                5.7
            )
        )
        movie.add(
            MovieListEntity(
                "2018-11-21",
                480530,
                "Creed II",
                "/v3QyboWRoA4O9RbcsqH8tJMe8EB.jpg",
                6.9
            )
        )
        movie.add(
            MovieListEntity(
                "2018-11-14",
                338952,
                "Fantastic Beasts: The Crimes of Grindelwald",
                "/fMMrl8fD9gRCFJvsx0SuFwkEOop.jpg",
                6.9
            )
        )
        movie.add(
            MovieListEntity(
                "2019-01-16",
                450465,
                "Glass",
                "/svIDTNUoajS8dLEo7EosxvyAsgJ.jpg",
                6.7
            )
        )
        movie.add(
            MovieListEntity(
                "2019-01-03",
                166428,
                "How to Train Your Dragon: The Hidden World",
                "/xvx4Yhf0DVH8G4LzNISpMfFBDy2.jpg",
                6.7
            )
        )
        movie.add(
            MovieListEntity(
                "2018-04-25",
                299536,
                "Avengers: Infinity War",
                "/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
                8.3
            )
        )

        return movie
    }

    fun generateTvShowLocal(): List<TvShowListEntity> {

        val tvShow = ArrayList<TvShowListEntity>()

        tvShow.add(
            TvShowListEntity(
                "2012-10-10",
                1412,
                "Arrow",
                "/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg",
                6.7
            )
        )
        tvShow.add(
            TvShowListEntity(
                "2019-02-15",
                79501,
                "Doom Patrol",
                "/kOAn06LmRCg4YiSStwrGL6UOQ3a.jpg",
                7.7
            )
        )
        tvShow.add(
            TvShowListEntity(
                "1999-01-31",
                1434,
                "Family Guy",
                "/9RBeCo8QSaoJLmmuzlwzVH3Hi12.jpg",
                7.1
            )
        )
        tvShow.add(
            TvShowListEntity(
                "1990-09-20",
                236,
                "The Flash",
                "/fi1GEdCbyWRDHpyJcB25YYK7fh4.jpg",
                7.4
            )
        )
        tvShow.add(
            TvShowListEntity(
                "1990-09-20",
                60708,
                "Gotham",
                "/4XddcRDtnNjYmLRMYpbrhFxsbuq.jpg",
                7.6
            )
        )
        tvShow.add(
            TvShowListEntity(
                "2005-03-27",
                1416,
                "Grey's Anatomy",
                "/zPIug5giU8oug6Xes5K1sTfQJxY.jpg",
                8.2
            )
        )
        tvShow.add(
            TvShowListEntity(
                "2019-03-28",
                54155,
                "Hanna",
                "/pe10EUjgO2jgwiu01MAv9l3IjxG.jpg",
                7.6
            )
        )
        tvShow.add(
            TvShowListEntity(
                "2017-03-17",
                62127,
                "Marvel's Iron Fist",
                "/4l6KD9HhtD6nCDEfg10Lp6C6zah.jpg",
                6.6
            )
        )
        tvShow.add(
            TvShowListEntity(
                "2003-09-23",
                4614,
                "NCIS",
                "/lSTchtc26YNdOjdKvZtLs22SokL.jpg",
                7.6
            )
        )
        tvShow.add(
            TvShowListEntity(
                "2017-01-26",
                69050,
                "Riverdale",
                "/xBaeUYKNJfX8VhIFvvgPpFSYxBZ.jpg",
                8.6
            )
        )

        return tvShow
    }

    fun generateSelectedMovieLocal(): MovieDetailEntity {

        return MovieDetailEntity(
            "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            "en",
            "A Star Is Born",
            "Drama",
            "Romance",
            433888866,
            "2018-10-03",
            48.76,
            7.5,
            332562,
            "A Star Is Born",
            "/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
            "Thunder Road",
            "22 & Green",
            "/cCzCClIzIh81Fa79hpW5nXoUsHK.png",
            "null",
            "US",
            "US",
        )
    }

    fun generateSelectedTvShowLocal(): TvShowDetailEntity {

        return TvShowDetailEntity(
            "2012-10-10",
            1412,
            "Arrow",
            170,
            8,
            "en",
            "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
            "/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg",
            6.7,
            "321.082",
            "Crime",
            "Drama",
            "Berlanti Productions",
            "DC Entertainment",
            "/3e294jszfE6cE8TOogmj0zNd6pL.png",
            "/2Tc1P3Ac8M479naPp1kYT3izLS5.png",
            "US",
            "US",
        )
    }

    fun generateSelectedFavMovie(): List<MovieDetailEntity> {
        val movie = ArrayList<MovieDetailEntity>()

        movie.add(
            MovieDetailEntity(
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                "en",
                "A Star Is Born",
                "Drama",
                "Romance",
                433888866,
                "2018-10-03",
                48.76,
                7.5,
                332562,
                "A Star Is Born",
                "/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                "Thunder Road",
                "22 & Green",
                "/cCzCClIzIh81Fa79hpW5nXoUsHK.png",
                "null",
                "US",
                "US",
            )
        )

        return movie
    }

    fun generateSelectedFavTvShow(): List<TvShowDetailEntity> {
        val tvShow = ArrayList<TvShowDetailEntity>()

        tvShow.add(
            TvShowDetailEntity(
                "2012-10-10",
                1412,
                "Arrow",
                170,
                8,
                "en",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg",
                6.7,
                "321.082",
                "Crime",
                "Drama",
                "Berlanti Productions",
                "DC Entertainment",
                "/3e294jszfE6cE8TOogmj0zNd6pL.png",
                "/2Tc1P3Ac8M479naPp1kYT3izLS5.png",
                "US",
                "US",
            )
        )

        return tvShow
    }

    fun generateSusMovieLocal(): List<MovieDetailEntity> {

        val susMovie = ArrayList<MovieDetailEntity>()

        susMovie.add(
            MovieDetailEntity(
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                "en",
                "A Star Is Born",
                "Drama",
                "Romance",
                433888866,
                "2018-10-03",
                48.76,
                7.5,
                332562,
                "A Star Is Born",
                "/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                "Thunder Road",
                "22 & Green",
                "/cCzCClIzIh81Fa79hpW5nXoUsHK.png",
                "null",
                "US",
                "US",
            )
        )

        return susMovie
    }

    fun generateSusTvShowLocal(): List<TvShowDetailEntity> {

        val susTvShow = ArrayList<TvShowDetailEntity>()

        susTvShow.add(
            TvShowDetailEntity(
                "2012-10-10",
                1412,
                "Arrow",
                170,
                8,
                "en",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg",
                6.7,
                "321.082",
                "Crime",
                "Drama",
                "Berlanti Productions",
                "DC Entertainment",
                "/3e294jszfE6cE8TOogmj0zNd6pL.png",
                "/2Tc1P3Ac8M479naPp1kYT3izLS5.png",
                "US",
                "US",
            )
        )

        return susTvShow
    }
}