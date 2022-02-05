package com.dicoding.moviecatalog.viewmodel.activity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.moviecatalog.data.source.Repository
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieDetailEntity
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowDetailEntity
import com.dicoding.moviecatalog.utils.CatalogDatabase
import com.dicoding.moviecatalog.viewmodel.DetailViewModel
import com.dicoding.moviecatalog.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = CatalogDatabase.generateSelectedMovieLocal()
    private val dummyTvShow = CatalogDatabase.generateSelectedTvShowLocal()
    private val movieId = dummyMovie.movieId
    private val tvShowId = dummyTvShow.tvShowId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: Repository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieDetailEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvShowDetailEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieRepository)
        viewModel.nGetSelectedMovie(movieId)
        viewModel.nGetSelectedTvShow(tvShowId)
    }

    @Test
    fun setMovie() {
        val dummyDetailMovie = Resource.success(CatalogDatabase.generateSelectedMovieLocal())
        val movie = MutableLiveData<Resource<MovieDetailEntity>>()
        movie.value = dummyDetailMovie

        `when`(movieRepository.getSelectedMovies(movieId)).thenReturn(movie)

        viewModel.nGetSelectedMovie(movieId)
        viewModel.nUpdateFavMovie()
        verify(movieRepository).updateFavMovies(movie.value!!.data as MovieDetailEntity, true)
        verifyNoMoreInteractions(movieObserver)
    }

    @Test
    fun getMovie() {
        val dummyDetailMovie = Resource.success(CatalogDatabase.generateSelectedMovieLocal())
        val movie = MutableLiveData<Resource<MovieDetailEntity>>()
        movie.value = dummyDetailMovie

        `when`(movieRepository.getSelectedMovies(movieId)).thenReturn(movie)

        viewModel.nGetSelectedMovie(movieId)
        viewModel.nGetSusMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyDetailMovie)
    }

    @Test
    fun getTvShow() {
        val dummyDetailTvShow = Resource.success(CatalogDatabase.generateSelectedTvShowLocal())
        val tvShow = MutableLiveData<Resource<TvShowDetailEntity>>()
        tvShow.value = dummyDetailTvShow

        `when`(movieRepository.getSelectedTvShow(tvShowId)).thenReturn(tvShow)

        viewModel.nGetSelectedTvShow(tvShowId)
        viewModel.nGetSusTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyDetailTvShow)
    }

    @Test
    fun setTvShow() {
        val dummyDetailTvShow = Resource.success(CatalogDatabase.generateSelectedTvShowLocal())
        val tvShow = MutableLiveData<Resource<TvShowDetailEntity>>()
        tvShow.value = dummyDetailTvShow

        `when`(movieRepository.getSelectedTvShow(tvShowId)).thenReturn(tvShow)

        viewModel.nGetSelectedTvShow(tvShowId)
        viewModel.nUpdateFavTvShow()
        verify(movieRepository).updateFavTvShow(tvShow.value!!.data as TvShowDetailEntity, true)
        verifyNoMoreInteractions(tvShowObserver)
    }
}