package com.dicoding.moviecatalog.viewmodel.activity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.moviecatalog.data.source.Repository
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieListEntity
import com.dicoding.moviecatalog.utils.CatalogDatabase
import com.dicoding.moviecatalog.viewmodel.MovieViewModel
import com.dicoding.moviecatalog.vo.Resource
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
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: Repository

    @Mock
    private lateinit var observer: Observer<Resource<List<MovieListEntity>>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMovie() {
        val movieDatabase = Resource.success(CatalogDatabase.generateMovieLocal())
        val movie = MutableLiveData<Resource<List<MovieListEntity>>>()
        movie.value = movieDatabase

        `when`(movieRepository.getAllMovies(listId)).thenReturn(movie)
        val movieEntities = viewModel.getMovie().value?.data
        verify(movieRepository).getAllMovies(listId)
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.size)

        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(movieDatabase)
    }

    companion object {
        private const val listId = "8174952"
    }
}