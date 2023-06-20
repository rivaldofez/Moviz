package com.ykbintang.moviz.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ykbintang.moviz.model.MovieDetail

@Entity(tableName = "movie")
data class MovieDetailEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val originalLanguage: String? = null,
    val title: String,
    val backdropPath: String,
    val genres: String,
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
    var isFavorite: Boolean
)

fun MovieDetail.toMovieDetailEntity() = MovieDetailEntity(
    id, originalLanguage, title, backdropPath, genres, voteCount, overview, originalTitle, runtime, posterPath, spokenLanguages, releaseDate, tagline, adult, homepage, status, isFavorite
)