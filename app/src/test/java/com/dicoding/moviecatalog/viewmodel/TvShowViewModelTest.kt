package com.dicoding.moviecatalog.viewmodel

import com.dicoding.moviecatalog.data.movie.MovieEntity
import com.dicoding.moviecatalog.data.movie.source.Repository
import com.dicoding.moviecatalog.data.tvshow.TvShowEntity
import com.dicoding.moviecatalog.utils.CatalogDatabase
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @Mock
    private lateinit var tvShowRepository: Repository

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(tvShowRepository)
    }

    @Test
    fun getTvShow() {
        Mockito.`when`(tvShowRepository.getAllTvShow()).thenReturn(CatalogDatabase.generateTvShowDatabase() as (ArrayList<TvShowEntity>))
        val tvShowEntities = viewModel.getTvShow()
        Mockito.verify(tvShowRepository).getAllTvShow()
        Assert.assertNotNull(tvShowEntities)
        Assert.assertEquals(10, tvShowEntities.size)
    }
}