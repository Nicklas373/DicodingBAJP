package com.dicoding.moviecatalog.viewmodel.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.dicoding.moviecatalog.data.source.local.LocalDataSource
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieListEntity
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowListEntity
import com.dicoding.moviecatalog.data.source.remote.RemoteDataSource
import com.dicoding.moviecatalog.utils.AppExecutors
import com.dicoding.moviecatalog.utils.CatalogDatabase
import com.dicoding.moviecatalog.viewmodel.utils.LiveDataTestUtil
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
        val dummyMovies = MutableLiveData<List<MovieListEntity>>()
        dummyMovies.value = CatalogDatabase.generateMovieLocal()
        `when`(local.getAllMovies()).thenReturn(dummyMovies)
        val movieEntities = LiveDataTestUtil.getValue(fakeRepository.getAllMovies(movieListId))
        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTvShow() {
        val dummyTvShow = MutableLiveData<List<TvShowListEntity>>()
        dummyTvShow.value = CatalogDatabase.generateTvShowLocal()
        `when`(local.getAllTvShow()).thenReturn(dummyTvShow)
        val tvShowEntities = LiveDataTestUtil.getValue(fakeRepository.getAllTvShow(tvShowListId))
        verify(local).getAllTvShow()
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }
}