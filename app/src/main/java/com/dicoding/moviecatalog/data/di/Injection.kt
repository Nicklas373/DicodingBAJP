package com.dicoding.moviecatalog.data.di

import com.dicoding.moviecatalog.data.source.Repository
import com.dicoding.moviecatalog.data.source.remote.RemoteDataSource
import com.dicoding.moviecatalog.utils.Helper

object Injection {
    fun provideRepository(): Repository {

        val remoteDataSource = RemoteDataSource.getInstance(Helper())

        return Repository.getInstance(remoteDataSource)
    }
}