package com.dicoding.moviecatalog.viewmodel.activity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.moviecatalog.data.movie.MovieCastEntity
import com.dicoding.moviecatalog.data.movie.MovieEntity
import com.dicoding.moviecatalog.data.movie.source.Repository
import com.dicoding.moviecatalog.data.tvshow.TvShowCastEntity
import com.dicoding.moviecatalog.data.tvshow.TvShowEntity
import com.dicoding.moviecatalog.utils.CatalogDatabase
import com.dicoding.moviecatalog.viewmodel.DetailMovieViewModel
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
class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = CatalogDatabase.generateMovieDatabase()[0]
    private val dummyTvShow = CatalogDatabase.generateTvShowDatabase()[0]
    private val dummyCastMovie = CatalogDatabase.generateCastListMovie1()
    private val dummyCastTvShow = CatalogDatabase.generateCastListTvShow1()
    private val movieId = dummyMovie.movieId
    private val tvShowId = dummyTvShow.tvShowId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: Repository

    @Mock
    private lateinit var movieObserver: Observer<MovieEntity>

    @Mock
    private lateinit var tvShowObserver: Observer<TvShowEntity>

    @Mock
    private lateinit var movieCastObserver: Observer<List<MovieCastEntity>>

    @Mock
    private lateinit var tvShowCastObserver: Observer<List<TvShowCastEntity>>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(movieRepository)
        viewModel.setSelectedMovie(movieId)
        viewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie
        `when`(movieRepository.getMovieWithCast(movieId)).thenReturn(movie)
        val movieEntity = viewModel.getMovie().value as MovieEntity
        verify(movieRepository).getMovieWithCast(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.movieId, movieEntity.movieId)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.description, movieEntity.description)
        assertEquals(dummyMovie.duration, movieEntity.duration)
        assertEquals(dummyMovie.genre1, movieEntity.genre1)
        assertEquals(dummyMovie.genre2, movieEntity.genre2)
        assertEquals(dummyMovie.rating, movieEntity.rating)
        assertEquals(dummyMovie.imagePath, movieEntity.imagePath)
        assertEquals(dummyMovie.releaseDate, movieEntity.releaseDate)

        viewModel.getMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getCastMovie() {
        val castMovie = MutableLiveData<List<MovieCastEntity>>()
        castMovie.value = dummyCastMovie

        `when`(movieRepository.getAllMoviesByCast(movieId)).thenReturn(castMovie)
        val castEntities = viewModel.getCastMovie(movieId).value
        verify(movieRepository).getAllMoviesByCast(movieId)
        assertNotNull(castEntities)
        assertEquals(3, castEntities?.size)

        viewModel.getCastMovie(movieId).observeForever(movieCastObserver)
        verify(movieCastObserver).onChanged(dummyCastMovie)
    }

    @Test
    fun getTvShow() {
        val tvShow = MutableLiveData<TvShowEntity>()
        tvShow.value = dummyTvShow

        `when`(movieRepository.getTvShowWithCast(tvShowId)).thenReturn(tvShow)
        val tvShowEntity = viewModel.getTvShow().value as TvShowEntity
        verify(movieRepository).getTvShowWithCast(tvShowId)

        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.tvShowId, tvShowEntity.tvShowId)
        assertEquals(dummyTvShow.title, tvShowEntity.title)
        assertEquals(dummyTvShow.description, tvShowEntity.description)
        assertEquals(dummyTvShow.duration, tvShowEntity.duration)
        assertEquals(dummyTvShow.episode, tvShowEntity.episode)
        assertEquals(dummyTvShow.genre1, tvShowEntity.genre1)
        assertEquals(dummyTvShow.genre2, tvShowEntity.genre2)
        assertEquals(dummyTvShow.season, tvShowEntity.season)
        assertEquals(dummyTvShow.imagePath, tvShowEntity.imagePath)
        assertEquals(dummyTvShow.rating, tvShowEntity.rating)
        assertEquals(dummyTvShow.releaseDate, tvShowEntity.releaseDate)

        viewModel.getTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShow)
    }

    @Test
    fun getCastTvShow1() {
        val castTvShow = MutableLiveData<List<TvShowCastEntity>>()
        castTvShow.value = dummyCastTvShow

        `when`(movieRepository.getAllTvShowByCast(tvShowId)).thenReturn(castTvShow)
        val castEntities = viewModel.getCastTvShow(tvShowId).value
        verify(movieRepository).getAllTvShowByCast(tvShowId)
        assertNotNull(castEntities)
        assertEquals(3, castEntities?.size)

        viewModel.getCastTvShow(tvShowId).observeForever(tvShowCastObserver)
        verify(tvShowCastObserver).onChanged(dummyCastTvShow)
    }
}