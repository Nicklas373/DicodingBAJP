package com.dicoding.moviecatalog.viewmodel.activity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.moviecatalog.data.source.Repository
import com.dicoding.moviecatalog.data.source.remote.response.tvshow.TvShowListResponse
import com.dicoding.moviecatalog.utils.CatalogDatabase
import com.dicoding.moviecatalog.viewmodel.TvShowViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvShowRepository: Repository

    @Mock
    private lateinit var observer: Observer<ArrayList<TvShowListResponse>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(tvShowRepository)
    }

    @Test
    fun getTvShow() {
        val tvShowDatabase = CatalogDatabase.generateTvShowLocal() as ArrayList<TvShowListResponse>
        val tvShow = MutableLiveData<ArrayList<TvShowListResponse>>()
        tvShow.value = tvShowDatabase

        Mockito.`when`(tvShowRepository.getAllTvShowApi(listId)).thenReturn(tvShow)
        val tvShowEntities = viewModel.getTvShow().value
        Mockito.verify(tvShowRepository).getAllTvShowApi(listId)
        Assert.assertNotNull(tvShowEntities)
        Assert.assertEquals(10, tvShowEntities?.size)

        viewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(tvShowDatabase)
    }

    companion object {
        private const val listId = "8174957"
    }
}