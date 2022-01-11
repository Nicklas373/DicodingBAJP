package com.dicoding.moviecatalog.viewmodel.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.moviecatalog.data.movie.source.remote.RemoteDataSource
import com.dicoding.moviecatalog.utils.CatalogDatabase
import com.dicoding.moviecatalog.viewmodel.utils.LiveDataTestUtil
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.doAnswer

class RepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val fakeRepository = FakeRepositoryTest(remote)

    private val movieResponses = CatalogDatabase.generateRemoteMovieDatabase()
    private val movieId = movieResponses[0].movieId
    private val tvShowResponses = CatalogDatabase.generateRemoteTvShowDatabase()
    private val tvShowId = tvShowResponses[0].tvShowId
    private val castMovieResponses = CatalogDatabase.generateRemoteCastListMovie1()
    private val castTvShowResponses = CatalogDatabase.generateRemoteCastListTvShow1()

    @Test
    fun getAllMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(movieResponses)
            null
        }.`when`(remote).getAllMovies(any())
        val movieEntities = LiveDataTestUtil.getValue(fakeRepository.getAllMovies())
        verify(remote).getAllMovies(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getAllTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowCallback)
                .onAllTvShowReceived(tvShowResponses)
            null
        }.`when`(remote).getAllTvShow(any())
        val tvShowEntities = LiveDataTestUtil.getValue(fakeRepository.getAllTvShow())
        verify(remote).getAllTvShow(any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.size.toLong())
    }

    @Test
    fun getCastTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadCastTvShowCallback)
                .onAllCastTvShowReceived(castTvShowResponses)
            null
        }.`when`(remote).getCastTvShow(eq(tvShowId), any())
        val castTvShowEntities = LiveDataTestUtil.getValue(fakeRepository.getCastTvShow(tvShowId))
        verify(remote).getCastTvShow(eq(tvShowId), any())
        assertNotNull(castTvShowEntities)
        assertEquals(castTvShowResponses.size.toLong(), castTvShowEntities.size.toLong())
    }

    @Test
    fun getCastMovie() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadCastMoviesCallback)
                .onAllCastMoviesReceived(castMovieResponses)
            null
        }.`when`(remote).getCastMovies(eq(movieId), any())
        val castMovieEntities = LiveDataTestUtil.getValue(fakeRepository.getCastMovies(movieId))
        verify(remote).getCastMovies(eq(tvShowId), any())
        assertNotNull(castMovieEntities)
        assertEquals(castMovieResponses.size.toLong(), castMovieEntities.size.toLong())
    }
}