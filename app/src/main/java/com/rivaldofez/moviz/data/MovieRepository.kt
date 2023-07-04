package com.rivaldofez.moviz.data

import com.rivaldofez.moviz.data.local.model.MovieDatabase
import com.rivaldofez.moviz.data.local.model.toMovieDetailEntity
import com.rivaldofez.moviz.data.remote.MovieService
import com.rivaldofez.moviz.model.Movie
import com.rivaldofez.moviz.model.MovieDetail
import com.rivaldofez.moviz.model.toMovie
import com.rivaldofez.moviz.model.toMovieDetail
import com.rivaldofez.moviz.ui.helper.wrapEspressoIdlingResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieService: MovieService,
    private val movieDatabase: MovieDatabase
) {
    suspend fun getMovieNowPlaying(): Flow<List<Movie>> =
        wrapEspressoIdlingResource {
            flowOf(movieService.getMovieNowPlaying().map {
                it.toMovie()
            })
        }


    suspend fun getMovieDetail(movieId: Int): Flow<MovieDetail?> =
        wrapEspressoIdlingResource {
            flowOf(movieService.getMovieDetail(movieId)?.toMovieDetail())
        }


    suspend fun getSearchMovie(query: String): Flow<List<Movie>> =
        wrapEspressoIdlingResource {
            flowOf(movieService.getSearchMovie(query).map {
                it.toMovie()
            })
        }


    suspend fun insertFavoriteMovie(movieDetail: MovieDetail) =
        movieDatabase.movieDao()
            .insertFavoriteMovie(movieDetail.toMovieDetailEntity())


    fun getFavoriteMovieById(movieId: Int): Flow<MovieDetail?> =

        wrapEspressoIdlingResource {
            movieDatabase.movieDao().getFavoriteMovieById(movieId).map {
                it?.toMovieDetail()
            }
        }


    fun getFavoriteMovies(): Flow<List<MovieDetail>> =
        wrapEspressoIdlingResource {
            movieDatabase.movieDao().getFavoriteMovies().map { listMovieDetailEntity ->
                listMovieDetailEntity.map {
                    it.toMovieDetail()
                }
            }
        }

}