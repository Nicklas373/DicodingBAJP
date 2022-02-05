package com.dicoding.moviecatalog.viewmodel.activity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.moviecatalog.data.source.Repository
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowListEntity
import com.dicoding.moviecatalog.utils.SortUtils.NEWEST
import com.dicoding.moviecatalog.viewmodel.TvShowViewModel
import com.dicoding.moviecatalog.vo.Resource
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
    private lateinit var observer: Observer<Resource<PagedList<TvShowListEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowListEntity>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(tvShowRepository)
    }

    @Test
    fun getTvShow() {
        val tvShowDatabase = Resource.success(pagedList)
        Mockito.`when`(tvShowDatabase.data?.size).thenReturn(5)
        val tvShow = MutableLiveData<Resource<PagedList<TvShowListEntity>>>()
        tvShow.value = tvShowDatabase

        Mockito.`when`(tvShowRepository.getAllTvShow(listId, NEWEST)).thenReturn(tvShow)
        val tvShowEntities = viewModel.getTvShow(NEWEST).value?.data
        Mockito.verify(tvShowRepository).getAllTvShow(listId, NEWEST)
        Assert.assertNotNull(tvShowEntities)
        Assert.assertEquals(5, tvShowEntities?.size)

        viewModel.getTvShow(NEWEST).observeForever(observer)
        verify(observer).onChanged(tvShowDatabase)
    }

    companion object {
        private const val listId = "8174957"
    }
}