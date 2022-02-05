package com.dicoding.moviecatalog.viewmodel.activity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.moviecatalog.data.source.Repository
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowDetailEntity
import com.dicoding.moviecatalog.utils.CatalogDatabase
import com.dicoding.moviecatalog.utils.SortUtils.NEWEST
import com.dicoding.moviecatalog.viewmodel.TvShowFavViewModel
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteTvShowViewModelTest {
    private lateinit var viewModel: TvShowFavViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: Repository

    @Mock
    private lateinit var observer: Observer<PagedList<TvShowDetailEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowDetailEntity>

    @Before
    fun setUp() {
        viewModel = TvShowFavViewModel(movieRepository)
    }

    @Test
    fun getFavMovies() {
        val dummyFavTvShow = pagedList
        Mockito.`when`(dummyFavTvShow.size).thenReturn(1)
        val favTvShow = MutableLiveData<PagedList<TvShowDetailEntity>>()
        favTvShow.value = dummyFavTvShow

        Mockito.`when`(movieRepository.getFavoriteTvShow(NEWEST)).thenReturn(favTvShow)
        val favTvShows = viewModel.getFavTvShow(NEWEST).value
        Mockito.verify(movieRepository).getFavoriteTvShow(NEWEST)
        Assert.assertNotNull(favTvShows)
        Assert.assertEquals(1, favTvShows?.size)

        viewModel.getFavTvShow(NEWEST).observeForever(observer)
        Mockito.verify(observer).onChanged(dummyFavTvShow)
    }

    @Test
    fun updateFavMovie() {
        viewModel.updateFavTvShow(CatalogDatabase.generateSelectedTvShowLocal())
        Mockito.verify(movieRepository)
            .updateFavTvShow(CatalogDatabase.generateSelectedTvShowLocal(), true)
        verifyNoMoreInteractions(movieRepository)
    }
}