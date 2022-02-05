package com.dicoding.moviecatalog.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val NEWEST = "Newest"
    const val OLDEST = "Oldest"
    const val RANDOM = "Random"
    const val MOVIE_ENTITIES = "movie_list_entity"
    const val MOVIE_FAV_ENTITIES = "movie_detail_entity"
    const val TVSHOW_ENTITIES = "tvShow_list_entity"
    const val TVSHOW_FAV_ENTITIES = "tvShow_detail_entity"

    fun getSortedQuery(filter: String, entity_name: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM $entity_name ")
        when (filter) {
            NEWEST -> simpleQuery.append("ORDER BY title ASC")
            OLDEST -> simpleQuery.append("ORDER BY title DESC")
            RANDOM -> simpleQuery.append("ORDER BY RANDOM()")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSortedQueryFav(filter: String, entity_name: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM $entity_name WHERE is_sus = 1 ")
        when (filter) {
            NEWEST -> simpleQuery.append("ORDER BY title ASC")
            OLDEST -> simpleQuery.append("ORDER BY title DESC")
            RANDOM -> simpleQuery.append("ORDER BY RANDOM()")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}