package com.ykbintang.moviz.data.remote

import com.ykbintang.moviz.data.remote.model.MovieDetailResponse
import com.ykbintang.moviz.data.remote.model.MovieListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieService @Inject constructor(private val movieApi: MovieApi) {
    suspend fun getMovieNowPlaying(): List<MovieListItem> {
        return withContext(Dispatchers.IO) {
            val movies = movieApi.getMovieNowPlaying()
            movies.body()?.movies ?: emptyList()
        }
    }

    suspend fun getSearchMovie(query: String): List<MovieListItem> {
        return withContext(Dispatchers.IO) {
            val movies = movieApi.getSearchMovie(query = query)
            movies.body()?.movies ?: emptyList()
        }
    }

    suspend fun getMovieDetail(movieId: Int): MovieDetailResponse? {
        return withContext(Dispatchers.IO) {
            val movieDetail = movieApi.getMovieDetail(id = movieId)
            movieDetail.body()
        }
    }
}