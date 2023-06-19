package com.ykbintang.moviz.data

import com.ykbintang.moviz.data.remote.MovieService
import com.ykbintang.moviz.model.Movie
import com.ykbintang.moviz.model.toMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class MovieRepository @Inject constructor( private val movieService: MovieService) {
    suspend fun getMovieNowPlaying(): Flow<List<Movie>> {
        return flowOf(movieService.getMovieNowPlaying().map {
            it.toMovie()
        })
    }

}