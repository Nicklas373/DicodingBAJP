package com.dicoding.moviecatalog.viewmodel.activity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.moviecatalog.data.source.Repository
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieListEntity
import com.dicoding.moviecatalog.utils.SortUtils.NEWEST
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
    private lateinit var observer: Observer<Resource<PagedList<MovieListEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MovieListEntity>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMovie() {
        val movieDatabase = Resource.success(pagedList)
        `when`(movieDatabase.data?.size).thenReturn(5)
        val movie = MutableLiveData<Resource<PagedList<MovieListEntity>>>()
        movie.value = movieDatabase

        `when`(movieRepository.getAllMovies(listId, NEWEST)).thenReturn(movie)
        val movieEntities = viewModel.getMovie(NEWEST).value?.data
        verify(movieRepository).getAllMovies(listId, NEWEST)
        assertNotNull(movieEntities)
        assertEquals(5, movieEntities?.size)

        viewModel.getMovie(NEWEST).observeForever(observer)
        verify(observer).onChanged(movieDatabase)
    }

    companion object {
        private const val listId = "8174952"
    }
}