package com.dicoding.moviecatalog.viewmodel.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.dicoding.moviecatalog.data.source.local.LocalDataSource
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieListEntity
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowListEntity
import com.dicoding.moviecatalog.data.source.remote.RemoteDataSource
import com.dicoding.moviecatalog.utils.AppExecutors
import com.dicoding.moviecatalog.utils.CatalogDatabase
import com.dicoding.moviecatalog.viewmodel.utils.PagedListUtil
import com.dicoding.moviecatalog.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class RepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val fakeRepository = FakeRepositoryTest(remote, local, appExecutors)

    private val movieResponses =
        CatalogDatabase.generateMovieLocal()
    private val tvShowResponses =
        CatalogDatabase.generateTvShowLocal()

    private val movieListId = "8174952"
    private val tvShowListId = "8174957"

    @Test
    fun getAllMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieListEntity>
        `when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        fakeRepository.getAllMovies(movieListId)
        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(CatalogDatabase.generateMovieLocal()))
        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTvShow() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowListEntity>
        `when`(local.getAllTvShow()).thenReturn(dataSourceFactory)
        fakeRepository.getAllTvShow(tvShowListId)
        val tvShowEntities =
            Resource.success(PagedListUtil.mockPagedList(CatalogDatabase.generateTvShowLocal()))
        verify(local).getAllTvShow()
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }
}