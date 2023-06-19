package com.ykbintang.moviz.model

import com.google.gson.annotations.SerializedName
import com.ykbintang.moviz.data.remote.model.MovieListItem

data class Movie(
    val overview: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val video: Boolean? = null,
    val title: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val releaseDate: String? = null,
    val popularity: Any? = null,
    val voteAverage: Any? = null,
    val id: Int? = null,
    val adult: Boolean? = null,
    val voteCount: Int? = null
)


fun MovieListItem.toMovie() = Movie(
    overview, originalLanguage, originalTitle, video, title, posterPath, backdropPath, releaseDate, popularity
)