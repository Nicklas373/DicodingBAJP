package com.dicoding.moviecatalog.viewmodel.activity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.moviecatalog.data.source.Repository
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieDetailEntity
import com.dicoding.moviecatalog.utils.CatalogDatabase
import com.dicoding.moviecatalog.viewmodel.MovieFavViewModel
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieViewModelTest {
    private lateinit var viewModel: MovieFavViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: Repository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieDetailEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieDetailEntity>

    @Before
    fun setUp() {
        viewModel = MovieFavViewModel(movieRepository)
    }

    @Test
    fun getFavMovies() {
        val dummyFavMovie = pagedList
        `when`(dummyFavMovie.size).thenReturn(1)
        val favMovies = MutableLiveData<PagedList<MovieDetailEntity>>()
        favMovies.value = dummyFavMovie

        `when`(movieRepository.getFavoriteMovies()).thenReturn(favMovies)
        val favMovie = viewModel.getFavMovies().value
        verify(movieRepository).getFavoriteMovies()
        assertNotNull(favMovie)
        assertEquals(1, favMovie?.size)

        viewModel.getFavMovies().observeForever(observer)
        verify(observer).onChanged(dummyFavMovie)
    }

    @Test
    fun updateFavMovie() {
        viewModel.updateFavMovies(CatalogDatabase.generateSelectedMovieLocal())
        verify(movieRepository).updateFavMovies(CatalogDatabase.generateSelectedMovieLocal(), true)
        verifyNoMoreInteractions(movieRepository)
    }
}