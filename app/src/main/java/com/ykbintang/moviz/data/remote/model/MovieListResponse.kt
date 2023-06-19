package com.ykbintang.moviz.data.remote.model

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

//	@field:SerializedName("overview")
//	val overview: String? = null,

//	@field:SerializedName("original_language")
//	val originalLanguage: String? = null,

	@field:SerializedName("original_title")
	val originalTitle: String,
//
//	@field:SerializedName("video")
//	val video: Boolean? = null,

	@field:SerializedName("title")
	val title: String,

//	@field:SerializedName("genre_ids")
//	val genreIds: List<Int?>? = null,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@field:SerializedName("release_date")
	val releaseDate: String,

//	@field:SerializedName("popularity")
//	val popularity: Any? = null,
//
//	@field:SerializedName("vote_average")
//	val voteAverage: Any? = null,

	@field:SerializedName("id")
	val id: Int,

//	@field:SerializedName("adult")
//	val adult: Boolean? = null,
//
//	@field:SerializedName("vote_count")
//	val voteCount: Int? = null
)

data class Dates(

	@field:SerializedName("maximum")
	val maximum: String? = null,

	@field:SerializedName("minimum")
	val minimum: String? = null
)
