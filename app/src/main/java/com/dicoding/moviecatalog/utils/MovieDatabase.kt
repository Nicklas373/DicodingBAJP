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
        movie.add(
            MovieEntity(
                "6",
                "Creed II",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "2 Hours, 10 Minute",
                "November 21, 2018",
                "https://raw.githubusercontent.com/Nicklas373/DicodingBAJP-Asset/main/movies/poster_creed.jpg",
                "6.9"
            )
        )
        movie.add(
            MovieEntity(
                "7",
                "Fantastic Beasts: The Crimes of Grindelwald",
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                "2 Hours, 14 Minute",
                "November 14, 2018",
                "https://raw.githubusercontent.com/Nicklas373/DicodingBAJP-Asset/main/movies/poster_crimes.jpg",
                "6.9"
            )
        )
        movie.add(
            MovieEntity(
                "8",
                "Glass",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men",
                "2 Hours, 9 Minute",
                "January 16, 2019",
                "https://raw.githubusercontent.com/Nicklas373/DicodingBAJP-Asset/main/movies/poster_glass.jpg",
                "6.7"
            )
        )
        movie.add(
            MovieEntity(
                "9",
                "How to Train Your Dragon: The Hidden World",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "2 Hours, 9 Minute",
                "January 3, 2019",
                "https://raw.githubusercontent.com/Nicklas373/DicodingBAJP-Asset/main/movies/poster_how_to_train.jpg",
                "7.8"
            )
        )
        movie.add(
            MovieEntity(
                "10",
                "Avengers: Infinity War",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "2 Hours, 29 Minute",
                "April 25, 2018",
                "https://raw.githubusercontent.com/Nicklas373/DicodingBAJP-Asset/main/movies/poster_infinity_war.jpg",
                "8.3"
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
        tvShow.add(
            TvShowEntity(
                "2",
                "Doom Patrol",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "49 Minute",
                "February 15, 2019",
                "https://raw.githubusercontent.com/Nicklas373/DicodingBAJP-Asset/main/tv_shows/poster_doom_patrol.jpg",
                "7.7"
            )
        )
        tvShow.add(
            TvShowEntity(
                "3",
                "Family Guy",
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                "49 Minute",
                "January 31, 1999",
                "https://raw.githubusercontent.com/Nicklas373/DicodingBAJP-Asset/main/tv_shows/poster_family_guy.jpg",
                "7.1"
            )
        )
        tvShow.add(
            TvShowEntity(
                "4",
                "The Flash",
                "When a bolt of lightening crashes through a police crime lab, a mix of electrically charged substances bathes chemist Barry Allen, transforming him into the fastest man alive--The Flash.",
                "45 Minute",
                "September 20, 1990",
                "https://raw.githubusercontent.com/Nicklas373/DicodingBAJP-Asset/main/tv_shows/poster_flash.jpg",
                "7.4"
            )
        )
        tvShow.add(
            TvShowEntity(
                "5",
                "Gotham",
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                "43 Minute",
                "September 22, 2014",
                "https://raw.githubusercontent.com/Nicklas373/DicodingBAJP-Asset/main/tv_shows/poster_gotham.jpg",
                "7.6"
            )
        )
        tvShow.add(
            TvShowEntity(
                "6",
                "Grey's Anatomy",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "43 Minute",
                "March 27, 2005",
                "https://raw.githubusercontent.com/Nicklas373/DicodingBAJP-Asset/main/tv_shows/poster_grey_anatomy.jpg",
                "8.2"
            )
        )
        tvShow.add(
            TvShowEntity(
                "7",
                "Hanna",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                "50 Minute",
                "March 28, 2019",
                "https://raw.githubusercontent.com/Nicklas373/DicodingBAJP-Asset/main/tv_shows/poster_hanna.jpg",
                "7.6"
            )
        )
        tvShow.add(
            TvShowEntity(
                "8",
                "Marvel's Iron Fist",
                "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                "55 Minute",
                "March 17, 2017",
                "https://raw.githubusercontent.com/Nicklas373/DicodingBAJP-Asset/main/tv_shows/poster_iron_fist.jpg",
                "7.6"
            )
        )
        tvShow.add(
            TvShowEntity(
                "9",
                "NCIS",
                "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                "55 Minute",
                "September 23, 2003",
                "https://raw.githubusercontent.com/Nicklas373/DicodingBAJP-Asset/main/tv_shows/poster_ncis.jpg",
                "7.5"
            )
        )
        tvShow.add(
            TvShowEntity(
                "10",
                "Riverdale",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "45 Minute",
                "January 26, 2017",
                "https://raw.githubusercontent.com/Nicklas373/DicodingBAJP-Asset/main/tv_shows/poster_riverdale.jpg",
                "8.6"
            )
        )

        return tvShow
    }

}