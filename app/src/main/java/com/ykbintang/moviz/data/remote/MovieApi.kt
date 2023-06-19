package com.ykbintang.moviz.data.remote

import com.ykbintang.moviz.data.remote.model.MovieDetailResponse
import com.ykbintang.moviz.data.remote.model.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/now_playing?language=en-US&page=1")
    suspend fun getMovieNowPlaying(): Response<MovieListResponse>

    @GET("https://api.themoviedb.org/3/search/movie")
    suspend fun getSearchMovie(
        @Query("query", encoded = true) query: String
    ): Response<MovieListResponse>

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: Int
    ): Response<MovieDetailResponse>
}