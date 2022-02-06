package com.dicoding.moviecatalog.viewmodel.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.moviecatalog.data.source.local.LocalDataSource
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieDetailEntity
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieListEntity
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowDetailEntity
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowListEntity
import com.dicoding.moviecatalog.data.source.remote.RemoteDataSource
import com.dicoding.moviecatalog.utils.AppExecutors
import com.dicoding.moviecatalog.utils.CatalogDatabase
import com.dicoding.moviecatalog.utils.SortUtils.NEWEST
import com.dicoding.moviecatalog.viewmodel.utils.LiveDataTestUtil
import com.dicoding.moviecatalog.viewmodel.utils.PagedListUtil
import com.dicoding.moviecatalog.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class RepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val fakeRepository = FakeRepositoryTest(remote, local, appExecutors)

    private val movieResponses =
        CatalogDatabase.generateMovieLocal()
    private val tvShowResponses =
        CatalogDatabase.generateTvShowLocal()
    private val movieSelected = CatalogDatabase.generateSelectedMovieLocal()
    private val tvShowSelected = CatalogDatabase.generateSelectedTvShowLocal()
    private val susMovie = CatalogDatabase.generateSusMovieLocal()
    private val susTvShow = CatalogDatabase.generateSusTvShowLocal()
    private val movieId = movieSelected.movieId
    private val tvShowId = tvShowSelected.tvShowId

    private val movieListId = "8174952"
    private val tvShowListId = "8174957"

    @Test
    fun getAllMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieListEntity>
        `when`(local.getAllMovies(NEWEST)).thenReturn(dataSourceFactory)
        fakeRepository.getAllMovies(movieListId, NEWEST)
        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(CatalogDatabase.generateMovieLocal()))
        verify(local).getAllMovies(NEWEST)
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailMovie() {
        val dummyDetailMovie = MutableLiveData<MovieDetailEntity>()
        dummyDetailMovie.value = CatalogDatabase.generateSelectedMovieLocal()
        `when`(local.getSelectedMovies(movieId)).thenReturn(dummyDetailMovie)

        val movieDetailEntity = LiveDataTestUtil.getValue(fakeRepository.getSelectedMovies(movieId))
        verify(local).getSelectedMovies(movieId)
        assertNotNull(movieDetailEntity)
        assertEquals(movieSelected.movieId, movieDetailEntity.data?.movieId)
        assertEquals(movieSelected.title, movieDetailEntity.data?.title)
        assertEquals(movieSelected.overview, movieDetailEntity.data?.overview)
        assertEquals(movieSelected.revenue, movieDetailEntity.data?.revenue)
        assertEquals(movieSelected.releaseDate, movieDetailEntity.data?.releaseDate)
        assertEquals(movieSelected.originalLanguage, movieDetailEntity.data?.originalLanguage)
        assertEquals(movieSelected.posterPath, movieDetailEntity.data?.posterPath)
        movieDetailEntity.data?.popularity?.let {
            assertEquals(
                movieSelected.popularity,
                it,
                0.0001
            )
        }
        movieDetailEntity.data?.voteAverage?.let {
            assertEquals(
                movieSelected.voteAverage,
                it,
                0.0001
            )
        }
        assertEquals(movieSelected.compName_1, movieDetailEntity.data?.compName_1)
        assertEquals(movieSelected.compName_2, movieDetailEntity.data?.compName_2)
        assertEquals(movieSelected.compOrigin_1, movieDetailEntity.data?.compOrigin_1)
        assertEquals(movieSelected.compOrigin_2, movieDetailEntity.data?.compOrigin_2)
        assertEquals(movieSelected.compLogo_1, movieDetailEntity.data?.compLogo_1)
        assertEquals(movieSelected.compLogo_2, movieDetailEntity.data?.compLogo_2)
    }

    @Test
    fun updateFavoriteMovie() {
        fakeRepository.updateFavMovies(CatalogDatabase.generateSelectedMovieLocal(), true)
        verify(local).updateFavMovies(CatalogDatabase.generateSelectedMovieLocal(), true)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun getFavoriteMovie() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieDetailEntity>
        `when`(local.getFavMovies(NEWEST)).thenReturn(dataSourceFactory)
        fakeRepository.getFavoriteMovies(NEWEST)

        val movieSusEntities =
            Resource.success(PagedListUtil.mockPagedList(CatalogDatabase.generateSelectedFavMovie()))
        verify(local).getFavMovies(NEWEST)
        assertNotNull(movieSusEntities)
        assertEquals(susMovie.size, movieSusEntities.data?.size)
    }

    @Test
    fun getAllTvShow() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowListEntity>
        `when`(local.getAllTvShow(NEWEST)).thenReturn(dataSourceFactory)
        fakeRepository.getAllTvShow(tvShowListId, NEWEST)
        val tvShowEntities =
            Resource.success(PagedListUtil.mockPagedList(CatalogDatabase.generateTvShowLocal()))
        verify(local).getAllTvShow(NEWEST)
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailTvShow() {
        val dummyDetailTvShow = MutableLiveData<TvShowDetailEntity>()
        dummyDetailTvShow.value = CatalogDatabase.generateSelectedTvShowLocal()
        `when`(local.getSelectedTvShow(tvShowId)).thenReturn(dummyDetailTvShow)

        val tvShowDetailEntity =
            LiveDataTestUtil.getValue(fakeRepository.getSelectedTvShow(tvShowId))
        verify(local).getSelectedTvShow(tvShowId)
        assertNotNull(tvShowDetailEntity)
        assertEquals(tvShowSelected.tvShowId, tvShowDetailEntity.data?.tvShowId)
        assertEquals(tvShowSelected.tvShowName, tvShowDetailEntity.data?.tvShowName)
        assertEquals(tvShowSelected.tvShowOverview, tvShowDetailEntity.data?.tvShowOverview)
        assertEquals(tvShowSelected.tvShowFirstAirDate, tvShowDetailEntity.data?.tvShowFirstAirDate)
        assertEquals(tvShowSelected.tvShowEpisodes, tvShowDetailEntity.data?.tvShowEpisodes)
        assertEquals(tvShowSelected.tvShowSeasons, tvShowDetailEntity.data?.tvShowSeasons)
        assertEquals(tvShowSelected.tvShowLanguage, tvShowDetailEntity.data?.tvShowLanguage)
        tvShowDetailEntity.data?.tvShowVote?.let {
            assertEquals(
                tvShowSelected.tvShowVote,
                it,
                0.0001
            )
        }
        assertEquals(tvShowSelected.compName_1, tvShowDetailEntity.data?.compName_1)
        assertEquals(tvShowSelected.compName_2, tvShowDetailEntity.data?.compName_2)
        assertEquals(tvShowSelected.compOrigin_1, tvShowDetailEntity.data?.compOrigin_1)
        assertEquals(tvShowSelected.compOrigin_2, tvShowDetailEntity.data?.compOrigin_2)
        assertEquals(tvShowSelected.compLogo_1, tvShowDetailEntity.data?.compLogo_1)
        assertEquals(tvShowSelected.compLogo_2, tvShowDetailEntity.data?.compLogo_2)
    }

    @Test
    fun updateFavoriteTvShow() {
        fakeRepository.updateFavTvShow(CatalogDatabase.generateSelectedTvShowLocal(), true)
        verify(local).updateFavTvShow(CatalogDatabase.generateSelectedTvShowLocal(), true)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun getFavoriteTvShow() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowDetailEntity>
        `when`(local.getFavTvShow(NEWEST)).thenReturn(dataSourceFactory)
        fakeRepository.getFavoriteTvShow(NEWEST)

        val tvShowSusEntities =
            Resource.success(PagedListUtil.mockPagedList(CatalogDatabase.generateSelectedFavTvShow()))
        verify(local).getFavTvShow(NEWEST)
        assertNotNull(tvShowSusEntities)
        assertEquals(susTvShow.size, tvShowSusEntities.data?.size)
    }
}