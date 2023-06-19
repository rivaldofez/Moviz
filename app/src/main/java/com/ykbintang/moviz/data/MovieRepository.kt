package com.ykbintang.moviz.data

import com.ykbintang.moviz.data.remote.MovieService
import com.ykbintang.moviz.model.Movie
import com.ykbintang.moviz.model.MovieDetail
import com.ykbintang.moviz.model.toMovie
import com.ykbintang.moviz.model.toMovieDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class MovieRepository @Inject constructor( private val movieService: MovieService) {
    suspend fun getMovieNowPlaying(): Flow<List<Movie>> {
        return flowOf(movieService.getMovieNowPlaying().map {
            it.toMovie()
        })
    }

    suspend fun getMovieDetail(movieId: Int): Flow<MovieDetail?>{
        return flowOf(movieService.getMovieDetail(movieId)?.toMovieDetail())
    }

}