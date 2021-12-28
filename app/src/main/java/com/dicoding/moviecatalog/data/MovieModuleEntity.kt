package com.dicoding.moviecatalog.data

data class MovieModuleEntity(
    var moduleId: String,
    var courseId: String,
    var title: String,
    var position: Int,
    var read: Boolean = false
) {
    var MovieContentEntity: MovieContentEntity? = null
}