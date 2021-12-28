package com.dicoding.moviecatalog.data.movie

data class MovieModuleEntity(
    var moduleId: String,
    var courseId: String,
    var title: String,
    var position: Int,
    var read: Boolean = false
) {
    var MovieContentEntity: MovieContentEntity? = null
}