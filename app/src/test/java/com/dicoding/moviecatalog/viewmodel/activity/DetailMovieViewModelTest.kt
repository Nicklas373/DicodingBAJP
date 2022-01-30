package com.dicoding.moviecatalog.viewmodel.activity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.moviecatalog.data.source.Repository
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieDetailEntity
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowDetailEntity
import com.dicoding.moviecatalog.utils.CatalogDatabase
import com.dicoding.moviecatalog.viewmodel.DetailMovieViewModel
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
class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
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
        viewModel = DetailMovieViewModel(movieRepository)
        viewModel.nSetSelectedMovie(movieId)
        viewModel.nSetSelectedTvShow(tvShowId)
    }

    @Test
    fun getMovie() {
        val dummyDetailMovie = Resource.success(CatalogDatabase.generateSelectedMovieLocal())
        val movie = MutableLiveData<Resource<MovieDetailEntity>>()
        movie.value = dummyDetailMovie

        `when`(movieRepository.getSelectedMovies(movieId)).thenReturn(movie)
        val movieEntity = viewModel.nGetSelectedMovie(movieId).value?.data
        verify(movieRepository).getSelectedMovies(movieId)

        assertNotNull(movieEntity)
        assertEquals(dummyMovie.movieId, movieEntity?.movieId)
        assertEquals(dummyMovie.title, movieEntity?.title)
        assertEquals(dummyMovie.overview, movieEntity?.overview)
        assertEquals(dummyMovie.revenue, movieEntity?.revenue)
        assertEquals(dummyMovie.releaseDate, movieEntity?.releaseDate)
        assertEquals(dummyMovie.originalLanguage, movieEntity?.originalLanguage)
        assertEquals(dummyMovie.posterPath, movieEntity?.posterPath)
        movieEntity?.popularity?.let { assertEquals(dummyMovie.popularity, it, 0.0001) }
        movieEntity?.voteAverage?.let { assertEquals(dummyMovie.voteAverage, it, 0.0001) }
        assertEquals(dummyMovie.compName_1, movieEntity?.compName_1)
        assertEquals(dummyMovie.compName_2, movieEntity?.compName_2)
        assertEquals(dummyMovie.compOrigin_1, movieEntity?.compOrigin_1)
        assertEquals(dummyMovie.compOrigin_2, movieEntity?.compOrigin_2)
        assertEquals(dummyMovie.compLogo_1, movieEntity?.compLogo_1)
        assertEquals(dummyMovie.compLogo_2, movieEntity?.compLogo_2)

        viewModel.nGetSelectedMovie(movieId).observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyDetailMovie)
    }

    @Test
    fun getTvShow() {
        val dummyDetailTvShow = Resource.success(CatalogDatabase.generateSelectedTvShowLocal())
        val tvShow = MutableLiveData<Resource<TvShowDetailEntity>>()
        tvShow.value = dummyDetailTvShow

        `when`(movieRepository.getSelectedTvShow(tvShowId)).thenReturn(tvShow)
        val tvShowEntity = viewModel.nGetSelectedTvShow(tvShowId).value?.data
        verify(movieRepository).getSelectedTvShow(tvShowId)

        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.tvShowId, tvShowEntity?.tvShowId)
        assertEquals(dummyTvShow.tvShowName, tvShowEntity?.tvShowName)
        assertEquals(dummyTvShow.tvShowOverview, tvShowEntity?.tvShowOverview)
        assertEquals(dummyTvShow.tvShowFirstAirDate, tvShowEntity?.tvShowFirstAirDate)
        assertEquals(dummyTvShow.tvShowEpisodes, tvShowEntity?.tvShowEpisodes)
        assertEquals(dummyTvShow.tvShowSeasons, tvShowEntity?.tvShowSeasons)
        assertEquals(dummyTvShow.tvShowLanguage, tvShowEntity?.tvShowLanguage)
        tvShowEntity?.tvShowVote?.let { assertEquals(dummyTvShow.tvShowVote, it, 0.0001) }
        assertEquals(dummyTvShow.tvShowPopularity, tvShowEntity?.tvShowPopularity)
        assertEquals(dummyTvShow.compName_1, tvShowEntity?.compName_1)
        assertEquals(dummyTvShow.compName_2, tvShowEntity?.compName_2)
        assertEquals(dummyTvShow.compOrigin_1, tvShowEntity?.compOrigin_1)
        assertEquals(dummyTvShow.compOrigin_2, tvShowEntity?.compOrigin_2)
        assertEquals(dummyTvShow.compLogo_1, tvShowEntity?.compLogo_1)
        assertEquals(dummyTvShow.compLogo_2, tvShowEntity?.compLogo_2)

        viewModel.nGetSelectedTvShow(tvShowId).observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyDetailTvShow)
    }
}