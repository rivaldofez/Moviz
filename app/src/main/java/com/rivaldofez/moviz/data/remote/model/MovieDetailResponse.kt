package com.rivaldofez.moviz.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(

    @field:SerializedName("original_language")
    val originalLanguage: String? = null,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("backdrop_path")
    val backdropPath: String?,

    @field:SerializedName("genres")
    val genres: List<GenresItem>,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("vote_count")
    val voteCount: Int? = null,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("original_title")
    val originalTitle: String,

    @field:SerializedName("runtime")
    val runtime: Int,

    @field:SerializedName("poster_path")
    val posterPath: String?,

    @field:SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguagesItem>,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("tagline")
    val tagline: String? = null,

    @field:SerializedName("adult")
    val adult: Boolean? = null,

    @field:SerializedName("homepage")
    val homepage: String? = null,

    @field:SerializedName("status")
    val status: String? = null
)

data class SpokenLanguagesItem(
    @field:SerializedName("english_name")
    val englishName: String
)

data class GenresItem(
    @field:SerializedName("name")
    val name: String
)
