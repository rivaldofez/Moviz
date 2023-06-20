package com.ykbintang.moviz.data.local.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ykbintang.moviz.data.local.MovieDao

@Database(
    entities = [MovieDetailEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}