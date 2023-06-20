package com.ykbintang.moviz.model

import com.google.gson.annotations.SerializedName
import com.ykbintang.moviz.data.local.model.MovieDetailEntity
import com.ykbintang.moviz.data.remote.model.MovieDetailResponse
import com.ykbintang.moviz.data.remote.model.MovieListItem

data class MovieDetail(
    val originalLanguage: String? = null,
    val title: String,
    val backdropPath: String,
    val genres: String,
    val id: Int,
    val voteCount: Int? = null,
    val overview: String,
    val originalTitle: String,
    val runtime: Int,
    val posterPath: String?,
    val spokenLanguages: String,
    val releaseDate: String,
    val tagline: String? = null,
    val adult: Boolean? = null,
    val homepage: String? = null,
    val status: String? = null,
    var isFavorite: Boolean = false
)

fun MovieDetailResponse.toMovieDetail(): MovieDetail {
    val languages = spokenLanguages.joinToString { it.englishName }
    val genres = genres.joinToString { it.name }

    return MovieDetail(
        originalLanguage = originalLanguage,
        title = title,
        backdropPath = backdropPath,
        genres = genres,
        id = id,
        voteCount = voteCount,
        overview = overview,
        originalTitle = originalTitle,
        runtime = runtime,
        posterPath = posterPath,
        spokenLanguages = languages,
        releaseDate = releaseDate,
        tagline = tagline,
        adult = adult,
        homepage = homepage,
        status = status
    )
}

fun MovieDetailEntity.toMovieDetail() = MovieDetail(
    originalLanguage, title, backdropPath, genres, id, voteCount, overview, originalTitle, runtime, posterPath, spokenLanguages, releaseDate, tagline, adult, homepage,status, isFavorite
)

