package com.rivaldofez.moviz.model

import com.rivaldofez.moviz.BuildConfig
import com.rivaldofez.moviz.data.remote.model.MovieListItem

data class Movie(
    val originalTitle: String,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val id: Int,
)

fun MovieListItem.toMovie() = Movie(
    originalTitle,
    title,
    if (posterPath == null) "" else BuildConfig.API_PATH_IMAGE + posterPath,
    if (backdropPath == null) "" else BuildConfig.API_PATH_IMAGE + backdropPath,
    releaseDate,
    id
)