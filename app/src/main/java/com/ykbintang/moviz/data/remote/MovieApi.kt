package com.ykbintang.moviz.data.remote

import com.ykbintang.moviz.data.remote.model.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {
    @GET("movie/now_playing?language=en-US&page=1")
    suspend fun getMovieNowPlaying(): Response<MovieListResponse>
}