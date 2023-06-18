package com.ykbintang.moviz.data.remote

import com.ykbintang.moviz.data.remote.model.MovieListItem
import com.ykbintang.moviz.data.remote.model.MovieListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieService @Inject constructor(private val movieApi: MovieApi) {


    suspend fun getMovieNowPlaying(): List<MovieListItem> {
        return withContext(Dispatchers.IO){
            val movies = movieApi.getMovieNowPlaying()
            movies.body()?.movies ?: emptyList()
        }
    }

}