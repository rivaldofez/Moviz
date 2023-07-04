package com.rivaldofez.moviz.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieListResponse(

    @field:SerializedName("dates")
    val dates: Dates? = null,

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("results")
    val movies: List<MovieListItem>? = null,

    @field:SerializedName("total_results")
    val totalResults: Int? = null
)

data class MovieListItem(

    @field:SerializedName("original_title")
    val originalTitle: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("poster_path")
    val posterPath: String?,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("id")
    val id: Int,

    )

data class Dates(

    @field:SerializedName("maximum")
    val maximum: String? = null,

    @field:SerializedName("minimum")
    val minimum: String? = null
)
