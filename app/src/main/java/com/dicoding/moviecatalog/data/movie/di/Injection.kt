package com.dicoding.moviecatalog.data.movie.di

import android.content.Context
import com.dicoding.moviecatalog.data.movie.source.Repository
import com.dicoding.moviecatalog.data.movie.source.remote.RemoteDataSource
import com.dicoding.moviecatalog.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): Repository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return Repository.getInstance(remoteDataSource)
    }
}