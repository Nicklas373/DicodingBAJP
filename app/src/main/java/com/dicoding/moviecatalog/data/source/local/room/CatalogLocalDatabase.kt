package com.dicoding.moviecatalog.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieDetailEntity
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieListEntity
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowDetailEntity
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowListEntity

@Database(
    entities = [MovieListEntity::class, MovieDetailEntity::class, TvShowListEntity::class, TvShowDetailEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CatalogLocalDatabase : RoomDatabase() {
    abstract fun catalogDao(): CatalogDao

    companion object {

        @Volatile
        private var INSTANCE: CatalogLocalDatabase? = null

        fun getInstance(context: Context): CatalogLocalDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    CatalogLocalDatabase::class.java,
                    "Catalog.db"
                ).fallbackToDestructiveMigration().build().apply {
                    INSTANCE = this
                }
            }
    }
}