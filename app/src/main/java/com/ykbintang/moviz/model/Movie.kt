package com.ykbintang.moviz.model

import com.google.gson.annotations.SerializedName
import com.ykbintang.moviz.data.remote.model.MovieListItem

data class Movie(
    val originalTitle: String,
    val title: String,
    val posterPath: String,
    val backdropPath: String? = null,
    val releaseDate: String,
    val id: Int,
)


fun MovieListItem.toMovie() = Movie(
    originalTitle, title, posterPath, backdropPath, releaseDate, id
)