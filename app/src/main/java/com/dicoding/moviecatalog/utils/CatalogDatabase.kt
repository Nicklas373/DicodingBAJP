package com.dicoding.moviecatalog.utils

import com.dicoding.moviecatalog.data.movie.MovieCastEntity
import com.dicoding.moviecatalog.data.movie.MovieEntity
import com.dicoding.moviecatalog.data.tvshow.TvShowCastEntity
import com.dicoding.moviecatalog.data.tvshow.TvShowEntity

object CatalogDatabase {
    fun generateMovieDatabase(): List<MovieEntity> {

        val movie = ArrayList<MovieEntity>()

        movie.add(
            MovieEntity(
                "1",
                "A Star Is Born",
                "Drama",
                "Romance",
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
                "Action",
                "Adventure",
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
                "Action",
                "Adventure",
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
                "Music",
                "Drama",
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
                "Action",
                "Crime",
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
                "Drama",
                "Null",
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
                "Adventure",
                "Fantasy",
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
                "Thriller",
                "Drama",
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
                "Animation",
                "Family",
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
                "Adventure",
                "Action",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "2 Hours, 29 Minute",
                "April 25, 2018",
                "https://raw.githubusercontent.com/Nicklas373/DicodingBAJP-Asset/main/movies/poster_infinity_war.jpg",
                "8.3"
            )
        )

        return movie
    }

    fun generateCastListTvShow1(): List<TvShowCastEntity> {

        val castList = ArrayList<TvShowCastEntity>()

        castList.add(
            TvShowCastEntity(
                "1",
                "Oliver Queen",
                "Stephen Amell",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/gN8cpnUaPIzWhVufahmzV0dwOgp.jpg"
            )
        )
        castList.add(
            TvShowCastEntity(
                "2",
                "John Diggle",
                "David Ramsey",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/bZWO7WfBYI8iojTFozzqN2X3RWL.jpg"
            )
        )
        castList.add(
            TvShowCastEntity(
                "3",
                "Felicity Smoak",
                "Emily Bett Rickards",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/3d9BB4P1hnnTXXxL8v1USCU0Eop.jpg"
            )
        )

        return castList
    }

    fun generateCastListTvShow2(): List<TvShowCastEntity> {

        val castList = ArrayList<TvShowCastEntity>()

        castList.add(
            TvShowCastEntity(
                "1",
                "Crazy Jane",
                "Diane Guerrero",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5LXvRYdxKLWROAD2KDSXzRNNSdE.jpg"
            )
        )
        castList.add(
            TvShowCastEntity(
                "2",
                "Rita Farr",
                "April Bowlby",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/c5Yk2tqApmbmKXHoPbYQauXssED.jpg"
            )
        )
        castList.add(
            TvShowCastEntity(
                "3",
                "Victor Stone",
                "Joivan Wade",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7tPro9hPR54m3zbLIfypsDDUDCI.jpg"
            )
        )

        return castList
    }

    fun generateCastListTvShow3(): List<TvShowCastEntity> {

        val castList = ArrayList<TvShowCastEntity>()

        castList.add(
            TvShowCastEntity(
                "1",
                "Peter Griffin",
                "Seth MacFarlane",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8oQJqM51Z0Qtdb7sE6ZfX1peNCB.jpg"
            )
        )
        castList.add(
            TvShowCastEntity(
                "2",
                "Lois Griffin",
                "Alex Borstein",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/evbCnRe5Yfuy0B41PONLTIcvbem.jpg"
            )
        )
        castList.add(
            TvShowCastEntity(
                "3",
                "Chris Griffin (voice)",
                "Seth Green",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/l4No5Eu6j0U80hCIkaSn17AOWrj.jpg"
            )
        )

        return castList
    }

    fun generateCastListTvShow4(): List<TvShowCastEntity> {

        val castList = ArrayList<TvShowCastEntity>()

        castList.add(
            TvShowCastEntity(
                "1",
                "Barry Allen / Flash",
                "John Wesley Shipp",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pL60jl0QdKx9oESZqRzA3kiXXbs.jpg"
            )
        )
        castList.add(
            TvShowCastEntity(
                "2",
                "Christina 'Tina' McGee",
                "Amanda Pays",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/ryDAKAA5bR8dqB7rDp7pDvTqT7z.jpg"
            )
        )
        castList.add(
            TvShowCastEntity(
                "3",
                "Julio Mendez",
                "Alex Désert",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/iz5fLxrOTKYUkpUmGXgVfCfIZxn.jpg"
            )
        )

        return castList
    }

    fun generateCastListTvShow5(): List<TvShowCastEntity> {

        val castList = ArrayList<TvShowCastEntity>()

        castList.add(
            TvShowCastEntity(
                "1",
                "James Gordon",
                "Ben McKenzie",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2jlwhOXKakL6gk365u4gPkTtHHi.jpg"
            )
        )
        castList.add(
            TvShowCastEntity(
                "2",
                "Harvey Bullock",
                "Donal Logue",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5Rj850yVkIqsgCihtZLbkNSHbFb.jpg"
            )
        )
        castList.add(
            TvShowCastEntity(
                "3",
                "Bruce Wayne",
                "David Mazouz",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/jc9RQJQTRWi5lrob8DCSt1sW5N0.jpg"
            )
        )

        return castList
    }

    fun generateCastListTvShow6(): List<TvShowCastEntity> {

        val castList = ArrayList<TvShowCastEntity>()

        castList.add(
            TvShowCastEntity(
                "1",
                "Meredith Grey",
                "Ellen Pompeo",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/yPd4G2RcjXQPNWkKUPbWh5v1flC.jpg"
            )
        )
        castList.add(
            TvShowCastEntity(
                "2",
                "Richard Webber",
                "James Pickens Jr.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/83Ynxlv3jV4ahkOiAEfunM7Rez2.jpg"
            )
        )
        castList.add(
            TvShowCastEntity(
                "3",
                "Miranda Bailey",
                "Chandra Wilson",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/iddDcaysDQ6IVMUjJ97Bv3lTBCu.jpg"
            )
        )

        return castList
    }

    fun generateCastListTvShow7(): List<TvShowCastEntity> {

        val castList = ArrayList<TvShowCastEntity>()

        castList.add(
            TvShowCastEntity(
                "1",
                "Hanna",
                "Esme Creed-Miles",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4S3D3TqS847CFLdPyKT9WSlhPmz.jpg"
            )
        )
        castList.add(
            TvShowCastEntity(
                "2",
                "Porpentina 'Tina' Goldstein",
                "Mireille Enos",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/zza8XezUz4I5Is55H2totMbGLeG.jpg"
            )
        )
        castList.add(
            TvShowCastEntity(
                "3",
                "John Carmichael",
                "Dermot Mulroney",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5gJszOF45KMPB5tmAbKdK0qgQBx.jpg"
            )
        )

        return castList
    }

    fun generateCastListTvShow8(): List<TvShowCastEntity> {

        val castList = ArrayList<TvShowCastEntity>()

        castList.add(
            TvShowCastEntity(
                "1",
                "Danny Rand",
                "Finn Jones",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rKzzQ7oc80xDYDmZykBjmXWt0VZ.jpg"
            )
        )
        castList.add(
            TvShowCastEntity(
                "2",
                "Colleen Wing",
                "Jessica Henwick",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wVToQAASq6nAcAEBg5qOU9K8JkU.jpg"
            )
        )
        castList.add(
            TvShowCastEntity(
                "3",
                "Joy Meachum",
                "Jessica Stroup",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/sr01O3bY7fjZ3OaR9grC4gJAWeM.jpg"
            )
        )

        return castList
    }

    fun generateCastListTvShow9(): List<TvShowCastEntity> {

        val castList = ArrayList<TvShowCastEntity>()

        castList.add(
            TvShowCastEntity(
                "1",
                "Leroy Jethro Gibbs",
                "Mark Harmon",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lMqKPig7zBoGfou7wWf88sZEGHo.jpg"
            )
        )
        castList.add(
            TvShowCastEntity(
                "2",
                "Donald Mallard",
                "David McCallum",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/47no2X4533Ehk2VO3BvVqUBqMUd.jpg"
            )
        )
        castList.add(
            TvShowCastEntity(
                "3",
                "Timothy McGee",
                "Sean Murray",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/r1kuDqWZbPR35Y2Ab7wQi72fgVk.jpg"
            )
        )

        return castList
    }

    fun generateCastListTvShow10(): List<TvShowCastEntity> {

        val castList = ArrayList<TvShowCastEntity>()

        castList.add(
            TvShowCastEntity(
                "1",
                "Archie Andrews",
                "K.J. Apa",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/idpwm8ZdFnndjeQ3mKleF20hvRo.jpg"
            )
        )
        castList.add(
            TvShowCastEntity(
                "2",
                "Betty Cooper",
                "Lili Reinhart",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/snsJrpxQq019fj3P7FUJJA0izdM.jpg"
            )
        )
        castList.add(
            TvShowCastEntity(
                "3",
                "Veronica Lodge",
                "Camila Mendes",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/a9RYVaY64cGmQoQJC8AcedYwUNR.jpg"
            )
        )

        return castList
    }

    fun generateTvShowDatabase(): List<TvShowEntity> {

        val tvShow = ArrayList<TvShowEntity>()

        tvShow.add(
            TvShowEntity(
                "1",
                "Arrow",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "Crime",
                "Drama",
                "10 Episodes",
                "8 Season",
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
                "Sci-Fi",
                "Drama",
                "10 Episodes",
                "3 Season",
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
                "Animation",
                "Comedy",
                "20 Episodes",
                "20 Season",
                "22 Minute",
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
                "Action",
                "Crime",
                "22 Episodes",
                "1 Season",
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
                "Drama",
                "Crime",
                "12 Episodes",
                "5 Season",
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
                "Drama",
                "Null",
                "11 Episodes",
                "18 Season",
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
                "Action",
                "Adventure",
                "6 Episodes",
                "3 Season",
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
                "Action",
                "Adventure",
                "10 Episodes",
                "2 Season",
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
                "Crime",
                "Action",
                "16 Episodes",
                "19  Season",
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
                "Mystery",
                "Drama",
                "7 Episodes",
                "6 Season",
                "45 Minute",
                "January 26, 2017",
                "https://raw.githubusercontent.com/Nicklas373/DicodingBAJP-Asset/main/tv_shows/poster_riverdale.jpg",
                "8.6"
            )
        )

        return tvShow
    }

    fun generateCastListMovie1(): List<MovieCastEntity> {

        val castList = ArrayList<MovieCastEntity>()

        castList.add(
            MovieCastEntity(
                "1",
                "Jackson Maine",
                "Bradley Cooper",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/DPnessSsWqVXRbKm93PtMjB4Us.jpg"
            )
        )
        castList.add(
            MovieCastEntity(
                "2",
                "Ally Campana",
                "Lady Gaga",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/h1LMnPxOzhgXc9QDCOcd6wdxoBh.jpg"
            )
        )
        castList.add(
            MovieCastEntity(
                "3",
                "Bobby Maine",
                "Sam Elliott",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/1K2IvGXFbKsgkExuUsRvy4F0c9e.jpg"
            )
        )

        return castList
    }

    fun generateCastListMovie2(): List<MovieCastEntity> {

        val castList = ArrayList<MovieCastEntity>()

        castList.add(
            MovieCastEntity(
                "1",
                "Alita",
                "Rosa Salazar",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/iJfsx7u76qWonJm6sKGwYPbNWDR.jpg"
            )
        )
        castList.add(
            MovieCastEntity(
                "2",
                "Dr. Dyson Ido",
                "Christoph Waltz",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2Hhztd4mUEV9Y25rfkXDwzL9QI9.jpg"
            )
        )
        castList.add(
            MovieCastEntity(
                "3",
                "Chiren",
                "Jennifer Connelly",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/3oYDftEbM3YgBiHYcbbIPRNZrTL.jpg"
            )
        )

        return castList
    }

    fun generateCastListMovie3(): List<MovieCastEntity> {

        val castList = ArrayList<MovieCastEntity>()

        castList.add(
            MovieCastEntity(
                "1",
                "Arthur Curry / Aquaman",
                "Jason Momoa",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6dEFBpZH8C8OijsynkSajQT99Pb.jpg"
            )
        )
        castList.add(
            MovieCastEntity(
                "2",
                "Mera",
                "Amber Heard",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/ik0cfqudGTYBBRKF0x4gl2rSUQx.jpg"
            )
        )
        castList.add(
            MovieCastEntity(
                "3",
                "Nuidis Vulko",
                "Willem Dafoe",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/ui8e4sgZAwMPi3hzEO53jyBJF9B.jpg"
            )
        )

        return castList
    }

    fun generateCastListMovie4(): List<MovieCastEntity> {

        val castList = ArrayList<MovieCastEntity>()

        castList.add(
            MovieCastEntity(
                "1",
                "Freddie Mercury",
                "Rami Malek",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2OuFzCbMibXGouG79tG1U4BLPbe.jpg"
            )
        )
        castList.add(
            MovieCastEntity(
                "2",
                "Brian May",
                "Gwilym Lee",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/bHSmjJLJyrg5Q0tC0W2FFreuOnO.jpg"
            )
        )
        castList.add(
            MovieCastEntity(
                "3",
                "Roger Taylor",
                "Ben Hardy",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/s8UEIomgY5AaEmPHkcPAScp68Fw.jpg"
            )
        )

        return castList
    }

    fun generateCastListMovie5(): List<MovieCastEntity> {

        val castList = ArrayList<MovieCastEntity>()

        castList.add(
            MovieCastEntity(
                "1",
                "Nels Coxman",
                "Liam Neeson",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/bboldwqSC6tdw2iL6631c98l2Mn.jpg"
            )
        )
        castList.add(
            MovieCastEntity(
                "2",
                "Grace Coxman",
                "Laura Dern",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2Ryt0SsExqWrLTzBu6sZcbLwoDJ.jpg"
            )
        )
        castList.add(
            MovieCastEntity(
                "3",
                "Kim Dash",
                "Emmy Rossum",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/aJ8bzA1WJOBFx0Ppdo1fLMiNxLh.jpg"
            )
        )

        return castList
    }

    fun generateCastListMovie6(): List<MovieCastEntity> {

        val castList = ArrayList<MovieCastEntity>()

        castList.add(
            MovieCastEntity(
                "1",
                "Adonis Creed",
                "Michael B. Jordan",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hz9AOUWZ2zzS0dpPJ1yQv2grA35.jpg"
            )
        )
        castList.add(
            MovieCastEntity(
                "2",
                "Robert (Rocky) Balboa Sr.",
                "Sylvester Stallone",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qDRGPAcQoW8Wuig9bvoLpHwf1gU.jpg"
            )
        )
        castList.add(
            MovieCastEntity(
                "3",
                "Ivan Drago",
                "Dolph Lundgren",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/jDLOU3Ay59NxLH8QDm5hcVTaKuC.jpg"
            )
        )

        return castList
    }

    fun generateCastListMovie7(): List<MovieCastEntity> {

        val castList = ArrayList<MovieCastEntity>()

        castList.add(
            MovieCastEntity(
                "1",
                "Newt Scamander",
                "Eddie Redmayne",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fSvG7qzoBBnJUmgtIuMgrK3EQPN.jpg"
            )
        )
        castList.add(
            MovieCastEntity(
                "2",
                "Porpentina 'Tina' Goldstein",
                "Katherine Waterston",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5xndFxJuB2QYmtoYwl1MFtFHM24.jpg"
            )
        )
        castList.add(
            MovieCastEntity(
                "3",
                "Jacob Kowalski",
                "Dan Fogler",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/zJWbLEjfbDthBMucq9M6L4GJXL3.jpg"
            )
        )

        return castList
    }

    fun generateCastListMovie8(): List<MovieCastEntity> {

        val castList = ArrayList<MovieCastEntity>()

        castList.add(
            MovieCastEntity(
                "1",
                "Patricia",
                "James McAvoy",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/u4REKbm3zhFI2J5tVyqRovGRYUJ.jpg"
            )
        )
        castList.add(
            MovieCastEntity(
                "2",
                "David Dunn",
                "Bruce Willis",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/caX3KtMU42EP3VLRFFBwqIIrch5.jpg"
            )
        )
        castList.add(
            MovieCastEntity(
                "3",
                "Elijah Price",
                "Samuel L. Jackson",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/yYpPvzdEKud48En8wbtGXs4C9Kk.jpg"
            )
        )

        return castList
    }

    fun generateCastListMovie9(): List<MovieCastEntity> {

        val castList = ArrayList<MovieCastEntity>()

        castList.add(
            MovieCastEntity(
                "1",
                "Hiccup (voice)",
                "Jay Baruchel",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/aDfqAMsPzIZvvj1ymivsKjRAfyv.jpg"
            )
        )
        castList.add(
            MovieCastEntity(
                "2",
                "Astrid (voice)",
                "America Ferrera",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7F84Lh2lKpvkM3EiOvqqvlOmw93.jpg"
            )
        )
        castList.add(
            MovieCastEntity(
                "3",
                "Grimmel (voice)",
                "F. Murray Abraham",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/p2RYVGdrcP0m70BkkiKcwyrDeim.jpg"
            )
        )

        return castList
    }

    fun generateCastListMovie10(): List<MovieCastEntity> {

        val castList = ArrayList<MovieCastEntity>()

        castList.add(
            MovieCastEntity(
                "1",
                "Tony Stark",
                "Robert Downey Jr.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5qHNjhtjMD4YWH3UP0rm4tKwxCL.jpg"
            )
        )
        castList.add(
            MovieCastEntity(
                "2",
                "Thor Odinson",
                "Chris Hemsworth",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/jpurJ9jAcLCYjgHHfYF32m3zJYm.jpg"
            )
        )
        castList.add(
            MovieCastEntity(
                "3",
                "Bruce Banner",
                "Mark Ruffalo",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/z3dvKqMNDQWk3QLxzumloQVR0pv.jpg"
            )
        )

        return castList
    }
}