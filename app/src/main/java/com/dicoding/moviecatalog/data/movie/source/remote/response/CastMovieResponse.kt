package com.dicoding.moviecatalog.data.movie.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CastMovieResponse(
    var castId: String,
    var castMovieName: String,
    var castRealName: String,
    var castImage: String
) : Parcelable