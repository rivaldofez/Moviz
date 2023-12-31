package com.rivaldofez.moviz.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rivaldofez.moviz.data.local.model.MovieDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(movieDetailEntity: MovieDetailEntity)

    @Query("SELECT * FROM movie WHERE id = :movieId LIMIT 1")
    fun getFavoriteMovieById(movieId: Int): Flow<MovieDetailEntity?>

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieDetailEntity>>
}