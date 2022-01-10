package com.dicoding.moviecatalog.viewmodel

import com.dicoding.moviecatalog.data.movie.MovieEntity
import com.dicoding.moviecatalog.data.movie.source.Repository
import com.dicoding.moviecatalog.utils.CatalogDatabase
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Mock
    private lateinit var movieRepository: Repository

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMovie() {
        `when`(movieRepository.getAllMovies()).thenReturn(CatalogDatabase.generateMovieDatabase() as (ArrayList<MovieEntity>))
        val movieEntities = viewModel.getMovie()
        verify(movieRepository).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities.size)
    }
}