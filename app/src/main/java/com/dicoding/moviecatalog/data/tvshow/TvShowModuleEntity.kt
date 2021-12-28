package com.dicoding.moviecatalog.data.tvshow

data class TvShowModuleEntity(
    var moduleId: String,
    var courseId: String,
    var title: String,
    var position: Int,
    var read: Boolean = false
) {
    var TvShowContentEntity: TvShowContentEntity? = null
}