package com.dicoding.moviecatalog.viewmodel

import com.dicoding.moviecatalog.data.movie.MovieCastEntity
import com.dicoding.moviecatalog.data.movie.source.Repository
import com.dicoding.moviecatalog.data.tvshow.TvShowCastEntity
import com.dicoding.moviecatalog.utils.CatalogDatabase
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
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
    private val movieId = dummyMovie.movieId
    private val tvShowId = dummyTvShow.tvShowId

    @Mock
    private lateinit var movieRepository: Repository

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(movieRepository)
        viewModel.setSelectedMovie(movieId)
        viewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getMovie() {
        //`when`(movieRepository.getMovieWithCast(movieId)).thenReturn(CatalogDatabase.generateMovieDatabase()[0])
        viewModel.setSelectedMovie(dummyMovie.movieId)
        //verify(movieRepository).getMovieWithCast(movieId)
        val movieEntity = viewModel.getMovie()
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
    }

    @Test
    fun getCastMovie() {
        `when`(movieRepository.getAllMoviesByCast(movieId)).thenReturn(CatalogDatabase.generateCastListMovie1() as (ArrayList<MovieCastEntity>))
        val castEntities = viewModel.getCastMovie(movieId)
        verify(movieRepository).getAllMoviesByCast(movieId)
        assertNotNull(castEntities)
        assertEquals(3, castEntities.size.toLong())
    }

    @Test
    fun getTvShow() {
        //`when`(movieRepository.getTvShowWithCast(tvShowId)).thenReturn(dummyTvShow)
        viewModel.setSelectedTvShow(dummyTvShow.tvShowId)
        //verify(movieRepository).getTvShowWithCast(tvShowId)
        val tvShowEntity = viewModel.getTvShow()
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
    }

    @Test
    fun getCastTvShow1() {
        `when`(movieRepository.getAllTvShowByCast(tvShowId)).thenReturn(CatalogDatabase.generateCastListTvShow1() as (ArrayList<TvShowCastEntity>))
        val castEntities = viewModel.getCastTvShow(tvShowId)
        verify(movieRepository).getAllTvShowByCast(tvShowId)
        assertNotNull(castEntities)
        assertEquals(3, castEntities.size.toLong())
    }
}