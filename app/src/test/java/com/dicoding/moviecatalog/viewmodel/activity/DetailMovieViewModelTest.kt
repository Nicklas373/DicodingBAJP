package com.dicoding.moviecatalog.viewmodel.activity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.moviecatalog.data.source.Repository
import com.dicoding.moviecatalog.data.source.remote.response.ProductionCompaniesListResponse
import com.dicoding.moviecatalog.data.source.remote.response.movie.MovieGenreListResponse
import com.dicoding.moviecatalog.data.source.remote.response.movie.MovieListResponse
import com.dicoding.moviecatalog.data.source.remote.response.tvshow.TvShowGenreListResponse
import com.dicoding.moviecatalog.data.source.remote.response.tvshow.TvShowListResponse
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
    private val dummyMovie = CatalogDatabase.generateMovieLocal()[0]
    private val dummyTvShow = CatalogDatabase.generateTvShowLocal()[0]
    private val dummyGenreMovie = CatalogDatabase.generateGenreMovies()
    private val dummyGenreTvShow = CatalogDatabase.generateGenreTvShow()
    private val dummyCompaniesMovie = CatalogDatabase.generateCompaniesMovies()
    private val dummyCompaniesTvShow = CatalogDatabase.generateCompaniesTvShow()
    private val movieId = dummyMovie.id
    private val tvShowId = dummyTvShow.tvShowId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: Repository

    @Mock
    private lateinit var movieObserver: Observer<MovieListResponse>

    @Mock
    private lateinit var tvShowObserver: Observer<TvShowListResponse>

    @Mock
    private lateinit var movieGenreObserver: Observer<ArrayList<MovieGenreListResponse>>

    @Mock
    private lateinit var tvShowGenreObserver: Observer<ArrayList<TvShowGenreListResponse>>

    @Mock
    private lateinit var movieCompaniesObserver: Observer<ArrayList<ProductionCompaniesListResponse>>

    @Mock
    private lateinit var tvShowCompaniesObserver: Observer<ArrayList<ProductionCompaniesListResponse>>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(movieRepository)
        viewModel.setSelectedMovie(movieId.toString())
        viewModel.setSelectedTvShow(tvShowId.toString())
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<MovieListResponse>()
        movie.value = dummyMovie

        `when`(movieRepository.getSelectedMovies(movieId.toString())).thenReturn(movie)
        val movieEntity = viewModel.getSelectedMovie().value as MovieListResponse
        verify(movieRepository).getSelectedMovies(movieId.toString())

        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity.id)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.overview, movieEntity.overview)
        assertEquals(dummyMovie.revenue, movieEntity.revenue)
        assertEquals(dummyMovie.releaseDate, movieEntity.releaseDate)
        assertEquals(dummyMovie.originalLanguage, movieEntity.originalLanguage)
        assertEquals(dummyMovie.posterPath, movieEntity.posterPath)
        assertEquals(dummyMovie.popularity, movieEntity.popularity, 0.0001)
        assertEquals(dummyMovie.voteAverage, movieEntity.voteAverage, 0.0001)

        viewModel.getSelectedMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getGenreMovie() {
        val genreMovie = MutableLiveData<ArrayList<MovieGenreListResponse>>()
        genreMovie.value = dummyGenreMovie as ArrayList<MovieGenreListResponse>

        `when`(movieRepository.getGenresFromMovies(movieId.toString())).thenReturn(genreMovie)
        val genreEntities = viewModel.getMovieGenres().value
        verify(movieRepository).getGenresFromMovies(movieId.toString())
        assertNotNull(genreEntities)
        assertEquals(2, genreEntities?.size)

        viewModel.getMovieGenres().observeForever(movieGenreObserver)
        verify(movieGenreObserver).onChanged(dummyGenreMovie)
    }

    @Test
    fun getCompaniesMovie() {
        val companiesMovie = MutableLiveData<ArrayList<ProductionCompaniesListResponse>>()
        companiesMovie.value = dummyCompaniesMovie as ArrayList<ProductionCompaniesListResponse>

        `when`(movieRepository.getCompaniesFromMovies(movieId.toString())).thenReturn(companiesMovie)
        val companiesEntities = viewModel.getMovieCompanies().value
        verify(movieRepository).getCompaniesFromMovies(movieId.toString())
        assertNotNull(companiesEntities)
        assertEquals(7, companiesEntities?.size)

        viewModel.getMovieCompanies().observeForever(movieCompaniesObserver)
        verify(movieCompaniesObserver).onChanged(dummyCompaniesMovie)
    }

    @Test
    fun getGenreTvShow() {
        val genreTvShow = MutableLiveData<ArrayList<TvShowGenreListResponse>>()
        genreTvShow.value = dummyGenreTvShow as ArrayList<TvShowGenreListResponse>

        `when`(movieRepository.getGenresFromTvShow(tvShowId.toString())).thenReturn(genreTvShow)
        val genreEntities = viewModel.getTvShowGenres().value
        verify(movieRepository).getGenresFromTvShow(tvShowId.toString())
        assertNotNull(genreEntities)
        assertEquals(4, genreEntities?.size)

        viewModel.getTvShowGenres().observeForever(tvShowGenreObserver)
        verify(tvShowGenreObserver).onChanged(dummyGenreTvShow)
    }

    @Test
    fun getCompaniesTvShow() {
        val companiesTvShow = MutableLiveData<ArrayList<ProductionCompaniesListResponse>>()
        companiesTvShow.value = dummyCompaniesTvShow as ArrayList<ProductionCompaniesListResponse>

        `when`(movieRepository.getCompaniesFromTvShow(tvShowId.toString())).thenReturn(
            companiesTvShow
        )
        val companiesEntities = viewModel.getTvShowCompanies().value
        verify(movieRepository).getCompaniesFromTvShow(tvShowId.toString())
        assertNotNull(companiesEntities)
        assertEquals(3, companiesEntities?.size)

        viewModel.getTvShowCompanies().observeForever(tvShowCompaniesObserver)
        verify(tvShowCompaniesObserver).onChanged(dummyCompaniesTvShow)
    }

    @Test
    fun getTvShow() {
        val tvShow = MutableLiveData<TvShowListResponse>()
        tvShow.value = dummyTvShow

        `when`(movieRepository.getSelectedTvShow(tvShowId.toString())).thenReturn(tvShow)
        val tvShowEntity = viewModel.getSelectedTvShow().value as TvShowListResponse
        verify(movieRepository).getSelectedTvShow(tvShowId.toString())

        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.tvShowId, tvShowEntity.tvShowId)
        assertEquals(dummyTvShow.tvShowName, tvShowEntity.tvShowName)
        assertEquals(dummyTvShow.tvShowOverview, tvShowEntity.tvShowOverview)
        assertEquals(dummyTvShow.tvShowFirstAirDate, tvShowEntity.tvShowFirstAirDate)
        assertEquals(dummyTvShow.tvShowEpisodes, tvShowEntity.tvShowEpisodes)
        assertEquals(dummyTvShow.tvShowSeasons, tvShowEntity.tvShowSeasons)
        assertEquals(dummyTvShow.tvShowLanguage, tvShowEntity.tvShowLanguage)
        assertEquals(dummyTvShow.tvShowVote, tvShowEntity.tvShowVote, 0.0001)
        assertEquals(dummyTvShow.tvShowPopularity, tvShowEntity.tvShowPopularity)

        viewModel.getSelectedTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShow)
    }
}