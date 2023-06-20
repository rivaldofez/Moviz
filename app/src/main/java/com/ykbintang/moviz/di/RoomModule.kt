package com.ykbintang.moviz.di

import android.content.Context
import androidx.room.Room
import com.ykbintang.moviz.data.local.MovieDao
import com.ykbintang.moviz.data.local.model.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    fun providesMovieDao(movieDatabase: MovieDatabase): MovieDao = movieDatabase.movieDao()

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MovieDatabase::class.java,
            "movie_database"
        ).build()
    }
}