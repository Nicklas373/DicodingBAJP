package com.dicoding.moviecatalog.viewmodel

import com.dicoding.moviecatalog.utils.CatalogDatabase
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = CatalogDatabase.generateMovieDatabase()[0]
    private val dummyTvShow = CatalogDatabase.generateTvShowDatabase()[0]
    private val movieId = dummyMovie.movieId
    private val tvShowId = dummyTvShow.tvShowId

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel()
        viewModel.setSelectedMovie(movieId)
        viewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getMovie() {
        viewModel.setSelectedMovie(dummyMovie.movieId)
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
    fun getCastMovie1() {
        val castEntities = viewModel.getCastMovie1()
        assertNotNull(castEntities)
        assertEquals(3, castEntities.size.toLong())
    }

    @Test
    fun getTvShow() {
        viewModel.setSelectedTvShow(dummyTvShow.tvShowId)
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
        val castEntities = viewModel.getCastTvShow1()
        assertNotNull(castEntities)
        assertEquals(3, castEntities.size.toLong())
    }
}