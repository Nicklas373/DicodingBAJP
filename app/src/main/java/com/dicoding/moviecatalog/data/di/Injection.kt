package com.dicoding.moviecatalog.data.di

import android.content.Context
import com.dicoding.moviecatalog.data.source.Repository
import com.dicoding.moviecatalog.data.source.local.LocalDataSource
import com.dicoding.moviecatalog.data.source.local.room.CatalogLocalDatabase
import com.dicoding.moviecatalog.data.source.remote.RemoteDataSource
import com.dicoding.moviecatalog.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): Repository {

        val database = CatalogLocalDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.catalogDao())
        val appExecutors = AppExecutors()

        return Repository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}