package com.dicoding.moviecatalog.utils

import com.dicoding.moviecatalog.data.source.remote.response.ProductionCompaniesListResponse
import com.dicoding.moviecatalog.data.source.remote.response.movie.MovieGenreListResponse
import com.dicoding.moviecatalog.data.source.remote.response.movie.MovieListResponse
import com.dicoding.moviecatalog.data.source.remote.response.tvshow.TvShowGenreListResponse
import com.dicoding.moviecatalog.data.source.remote.response.tvshow.TvShowListResponse

object CatalogDatabase {
    fun generateMovieLocal(): List<MovieListResponse> {

        val movie = ArrayList<MovieListResponse>()

        movie.add(
            MovieListResponse(
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                "en",
                433888866,
                "A Star Is Born",
                "2018-10-03",
                43.088,
                7.5,
                332562,
                "A Star Is Born",
                "/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg"
            )
        )
        movie.add(
            MovieListResponse(
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "en",
                404852543,
                "Alita: Battle Angel",
                "2019-01-31",
                61.289,
                7.2,
                399579,
                "Alita: Battle Angel",
                "/xRWht48C2V8XNfzvPehyClOvDni.jpg"
            )
        )
        movie.add(
            MovieListResponse(
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "en",
                1148461807,
                "Aquaman",
                "2018-07-06",
                90.991,
                6.9,
                297802,
                "Aquaman",
                "/xLPffWMhMj1l50ND3KchMjYoKmE.jpg",
            )
        )
        movie.add(
            MovieListResponse(
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "en",
                894027543,
                "Bohemian Rhapsody",
                "2018-10-24",
                64.657,
                8.0,
                424694,
                "Bohemian Rhapsody",
                "/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg",
            )
        )
        movie.add(
            MovieListResponse(
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "en",
                76419755,
                "Cold Pursuit",
                "2019-02-07",
                48.224,
                5.7,
                438650,
                "Cold Pursuit",
                "/hXgmWPd1SuujRZ4QnKLzrj79PAw.jpg"
            )
        )
        movie.add(
            MovieListResponse(
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "en",
                214215889,
                "Creed II",
                "2018-11-21",
                88.43,
                6.9,
                480530,
                "Creed II",
                "/v3QyboWRoA4O9RbcsqH8tJMe8EB.jpg",
            )
        )
        movie.add(
            MovieListResponse(
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                "en",
                653355901,
                "Fantastic Beasts: The Crimes of Grindelwald",
                "2018-11-14",
                82.875,
                6.9,
                338952,
                "Fantastic Beasts: The Crimes of Grindelwald",
                "/fMMrl8fD9gRCFJvsx0SuFwkEOop.jpg",
            )
        )
        movie.add(
            MovieListResponse(
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "en",
                246941965,
                "Glass",
                "2019-01-16",
                67.093,
                6.7,
                450465,
                "Glass",
                "/svIDTNUoajS8dLEo7EosxvyAsgJ.jpg",
            )
        )
        movie.add(
            MovieListResponse(
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "en",
                517526875,
                "How to Train Your Dragon: The Hidden World",
                "2019-01-03",
                85.586,
                6.7,
                166428,
                "How to Train Your Dragon: The Hidden World",
                "/xvx4Yhf0DVH8G4LzNISpMfFBDy2.jpg"
            )
        )
        movie.add(
            MovieListResponse(
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "en",
                2046239637,
                "Avengers: Infinity War",
                "2018-04-25",
                547.223,
                8.3,
                299536,
                "Avengers: Infinity War",
                "/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",

                )
        )

        return movie
    }

    fun generateTvShowLocal(): List<TvShowListResponse> {

        val tvShow = ArrayList<TvShowListResponse>()

        tvShow.add(
            TvShowListResponse(
                "2012-10-10",
                1412,
                "Arrow",
                170,
                8,
                "en",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg",
                6.7,
                "202.115"
            )
        )
        tvShow.add(
            TvShowListResponse(
                "2019-02-15",
                79501,
                "Doom Patrol",
                34,
                3,
                "en",
                "As the Brotherhood of Evil closes in, the Doom Patrol try to set aside past issues in hopes of becoming a true superhero team – or risk being taken out for good.",
                "/kOAn06LmRCg4YiSStwrGL6UOQ3a.jpg",
                7.7,
                "111.967"
            )
        )
        tvShow.add(
            TvShowListResponse(
                "1999-01-31",
                1434,
                "Family Guy",
                388,
                20,
                "en",
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                "/9RBeCo8QSaoJLmmuzlwzVH3Hi12.jpg",
                7.1,
                "307.642"
            )
        )
        tvShow.add(
            TvShowListResponse(
                "1990-09-20",
                236,
                "The Flash",
                22,
                1,
                "en",
                "When a bolt of lightening crashes through a police crime lab, a mix of electrically charged substances bathes chemist Barry Allen, transforming him into the fastest man alive--The Flash.",
                "/fi1GEdCbyWRDHpyJcB25YYK7fh4.jpg",
                7.4,
                "58.38"
            )
        )
        tvShow.add(
            TvShowListResponse(
                "1990-09-20",
                60708,
                "Gotham",
                100,
                5,
                "en",
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                "/4XddcRDtnNjYmLRMYpbrhFxsbuq.jpg",
                7.6,
                "182.639"
            )
        )
        tvShow.add(
            TvShowListResponse(
                "2005-03-27",
                1416,
                "Grey's Anatomy",
                390,
                18,
                "en",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "/zPIug5giU8oug6Xes5K1sTfQJxY.jpg",
                8.2,
                "1039.397"
            )
        )
        tvShow.add(
            TvShowListResponse(
                "2019-03-28",
                54155,
                "Hanna",
                390,
                18,
                "en",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                "/pe10EUjgO2jgwiu01MAv9l3IjxG.jpg",
                7.6,
                "48.43"
            )
        )
        tvShow.add(
            TvShowListResponse(
                "2017-03-17",
                62127,
                "Marvel's Iron Fist",
                23,
                2,
                "en",
                "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                "/4l6KD9HhtD6nCDEfg10Lp6C6zah.jpg",
                6.6,
                "27.361"
            )
        )
        tvShow.add(
            TvShowListResponse(
                "2003-09-23",
                4614,
                "NCIS",
                430,
                19,
                "en",
                "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                "/lSTchtc26YNdOjdKvZtLs22SokL.jpg",
                7.6,
                "318.413"
            )
        )
        tvShow.add(
            TvShowListResponse(
                "2017-01-26",
                69050,
                "Riverdale",
                102,
                6,
                "en",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "/xBaeUYKNJfX8VhIFvvgPpFSYxBZ.jpg",
                8.6,
                "856.993"
            )
        )

        return tvShow
    }

    fun generateGenreMovies(): List<MovieGenreListResponse> {

        val movieGenre = ArrayList<MovieGenreListResponse>()

        movieGenre.add(
            MovieGenreListResponse(
                18,
                "drama"
            )
        )

        movieGenre.add(
            MovieGenreListResponse(
                10749,
                "Romance"
            )
        )

        return movieGenre
    }

    fun generateGenreTvShow(): List<TvShowGenreListResponse> {

        val tvShowGenre = ArrayList<TvShowGenreListResponse>()

        tvShowGenre.add(
            TvShowGenreListResponse(
                80,
                "Crime"
            )
        )

        tvShowGenre.add(
            TvShowGenreListResponse(
                18,
                "Drama"
            )
        )

        tvShowGenre.add(
            TvShowGenreListResponse(
                9648,
                "Mystery"
            )
        )

        tvShowGenre.add(
            TvShowGenreListResponse(
                10759,
                "Action & Adventure"
            )
        )

        return tvShowGenre
    }

    fun generateCompaniesMovies(): List<ProductionCompaniesListResponse> {

        val movieCompanies = ArrayList<ProductionCompaniesListResponse>()

        movieCompanies.add(
            ProductionCompaniesListResponse(
                "/cCzCClIzIh81Fa79hpW5nXoUsHK.png",
                "Thunder Road",
                3528,
                "US"
            )
        )

        movieCompanies.add(
            ProductionCompaniesListResponse(
                null,
                "22 & Green",
                83035,
                "US"
            )
        )

        movieCompanies.add(
            ProductionCompaniesListResponse(
                null,
                "Gerber Pictures",
                975,
                "US"
            )
        )

        movieCompanies.add(
            ProductionCompaniesListResponse(
                null,
                "Joint Effort",
                83036,
                "US"
            )
        )

        movieCompanies.add(
            ProductionCompaniesListResponse(
                null,
                "Malpaso Productions",
                171,
                "US"
            )
        )

        movieCompanies.add(
            ProductionCompaniesListResponse(
                "/IuAlhI9eVC9Z8UQWOIDdWRKSEJ.png",
                "Warner Bros. Pictures",
                174,
                "US"
            )
        )

        movieCompanies.add(
            ProductionCompaniesListResponse(
                "/srN5L2dTtbsb48VUHvpNG71pT7q.png",
                "Live Nation Productions",
                81368,
                "US"
            )
        )

        return movieCompanies
    }

    fun generateCompaniesTvShow(): List<ProductionCompaniesListResponse> {

        val tvShowCompanies = ArrayList<ProductionCompaniesListResponse>()

        tvShowCompanies.add(
            ProductionCompaniesListResponse(
                "/3e294jszfE6cE8TOogmj0zNd6pL.png",
                "Berlanti Productions",
                27711,
                "US"
            )
        )

        tvShowCompanies.add(
            ProductionCompaniesListResponse(
                "/2Tc1P3Ac8M479naPp1kYT3izLS5.png",
                "DC Entertainment",
                9993,
                "US"
            )
        )

        tvShowCompanies.add(
            ProductionCompaniesListResponse(
                "/IuAlhI9eVC9Z8UQWOIDdWRKSEJ.png",
                "Warner Bros. Pictures",
                174,
                "US"
            )
        )

        return tvShowCompanies
    }
}