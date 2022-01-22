package com.dicoding.moviecatalog.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.adapter.CompaniesMovieAdapter
import com.dicoding.moviecatalog.adapter.api.MovieGenreApiAdapter
import com.dicoding.moviecatalog.adapter.api.TvShowGenreApiAdapter
import com.dicoding.moviecatalog.callback.ShareCallback
import com.dicoding.moviecatalog.data.source.remote.response.ProductionCompaniesListResponse
import com.dicoding.moviecatalog.data.source.remote.response.movie.MovieGenreListResponse
import com.dicoding.moviecatalog.data.source.remote.response.movie.MovieListResponse
import com.dicoding.moviecatalog.data.source.remote.response.tvshow.TvShowGenreListResponse
import com.dicoding.moviecatalog.data.source.remote.response.tvshow.TvShowListResponse
import com.dicoding.moviecatalog.databinding.ActivityDetailShowBinding
import com.dicoding.moviecatalog.databinding.ContentDetailShowBinding
import com.dicoding.moviecatalog.utils.InlineVariable
import com.dicoding.moviecatalog.viewmodel.DetailMovieViewModel
import com.dicoding.moviecatalog.viewmodel.ViewModelFactory

class DetailShowActivity : AppCompatActivity(), ShareCallback {

    private lateinit var movieDetailBinding: ContentDetailShowBinding
    private var inlineVariable = InlineVariable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_show)

        val activityMovieDetailBinding = ActivityDetailShowBinding.inflate(layoutInflater)
        movieDetailBinding = activityMovieDetailBinding.detailContent

        setContentView(activityMovieDetailBinding.root)

        setSupportActionBar(activityMovieDetailBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(
            this, factory
        )[DetailMovieViewModel::class.java]
        val extras = intent.extras
        if (extras != null) {
            val showId = extras.getString(SHOW_ID)
            val movieId = extras.getInt(EXTRA_MOVIE)
            val tvShowId = extras.getInt(EXTRA_TV_SHOW)
            if (showId.equals("Movie")) {
                hideStaticUI()
                if (movieId.toString().isNotBlank()) {
                    movieDetailBinding.progressBar.visibility = View.VISIBLE
                    viewModel.setSelectedMovie(movieId.toString())
                    viewModel.getSelectedMovie()
                        .observe(this, { selectedMovieId -> setMovieData(selectedMovieId) })
                    viewModel.getMovieCompanies().observe(this, { selectedMovieId ->
                        movieDetailBinding.progressBar.visibility = View.GONE
                        showStaticUIMovie()
                        setCompaniesData(selectedMovieId)
                    })
                    viewModel.getMovieGenres().observe(this, { selectedMovieId ->
                        movieDetailBinding.progressBar.visibility = View.GONE
                        showStaticUIMovie()
                        setMovieGenreData(selectedMovieId)
                    })
                }
            } else if (showId.equals("TvShow")) {
                hideStaticUI()
                if (tvShowId.toString().isNotBlank()) {
                    movieDetailBinding.progressBar.visibility = View.VISIBLE
                    viewModel.setSelectedTvShow(tvShowId.toString())
                    viewModel.getSelectedTvShow()
                        .observe(this, { selectedTvShowId -> setTvShowData(selectedTvShowId) })
                    viewModel.getTvShowCompanies().observe(this, { selectedTvShowId ->
                        movieDetailBinding.progressBar.visibility = View.GONE
                        showStaticUITvShow()
                        setCompaniesData(selectedTvShowId)
                    })
                    viewModel.getTvShowGenres().observe(this, { selectedTvShowId ->
                        movieDetailBinding.progressBar.visibility = View.GONE
                        showStaticUITvShow()
                        setTvShowGenreData(selectedTvShowId)
                    })
                }
            }
        }

        viewModel.isToast.observe(this, { isToast ->
            showToast(isToast, viewModel.toastReason.value.toString())
        })

        viewModel.isLoading.observe(this, {
            showLoading(it)
        })

        configAdapterCompanies()
        configAdapterGenres()
    }

    private fun setMovieData(movieId: MovieListResponse) {
        if (movieId.title.isBlank()) {
            movieDetailBinding.movieTitleText.text = "-"
        } else {
            movieDetailBinding.movieTitleText.text = movieId.title
        }

        if (movieId.overview.isBlank()) {
            movieDetailBinding.descText.text = "-"
        } else {
            movieDetailBinding.descText.text = movieId.overview
        }

        if (movieId.releaseDate.isBlank()) {
            movieDetailBinding.movieReleaseDate.text = "-"
        } else {
            val setDate = inlineVariable.setReleaseDate(movieId.releaseDate)
            movieDetailBinding.movieReleaseDate.text = setDate
        }

        if (movieId.voteAverage.equals(null)) {
            movieDetailBinding.movieRatingText.text = "-"
        } else {
            movieDetailBinding.movieRatingText.text = movieId.voteAverage.toString()
        }

        val movieImage = getString(R.string.movieDb_static_image) + movieId.posterPath

        if (movieId.posterPath.isBlank()) {
            Glide.with(this)
                .load("-")
                .transform(RoundedCorners(20))
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(movieDetailBinding.imagePoster)
        } else {
            Glide.with(this)
                .load(movieImage)
                .transform(RoundedCorners(20))
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(movieDetailBinding.imagePoster)
        }

        if (movieId.revenue.toString().isBlank()) {
            movieDetailBinding.movieRevenueText.text = "-"
        } else {
            movieDetailBinding.movieRevenueText.text =
                inlineVariable.setRevenue(movieId.revenue.toString())
        }

        if (movieId.originalLanguage.isBlank()) {
            movieDetailBinding.movieLanguageText.text = "-"
        } else {
            movieDetailBinding.movieLanguageText.text = movieId.originalLanguage
        }

        movieDetailBinding.imagePoster.contentDescription = movieId.posterPath
        movieDetailBinding.imgShare.setOnClickListener {
            onShareClickMovie()
        }
    }

    private fun setTvShowData(tvShowId: TvShowListResponse) {
        if (tvShowId.tvShowName.isBlank()) {
            movieDetailBinding.movieTitleText.text = "-"
        } else {
            movieDetailBinding.movieTitleText.text = tvShowId.tvShowName
        }

        if (tvShowId.tvShowOverview.isBlank()) {
            movieDetailBinding.descText.text = "-"
        } else {
            movieDetailBinding.descText.text = tvShowId.tvShowOverview
        }

        if (tvShowId.tvShowFirstAirDate.isBlank()) {
            movieDetailBinding.movieReleaseDate.text = "-"
        } else {
            val setDate = inlineVariable.setReleaseDate(tvShowId.tvShowFirstAirDate)
            movieDetailBinding.movieReleaseDate.text = setDate
        }

        if (tvShowId.tvShowVote.equals(null)) {
            movieDetailBinding.movieRatingText.text = "-"
        } else {
            movieDetailBinding.movieRatingText.text = tvShowId.tvShowVote.toString()
        }

        if (tvShowId.tvShowLanguage.isBlank()) {
            movieDetailBinding.tvShowLanguageText.text = "-"
        } else {
            movieDetailBinding.tvShowLanguageText.text = tvShowId.tvShowLanguage
        }

        if (tvShowId.tvShowPopularity.isBlank()) {
            movieDetailBinding.moviePopularityTxt.text = "-"
        } else {
            movieDetailBinding.moviePopularityTxt.text = tvShowId.tvShowPopularity
        }

        val movieImage = getString(R.string.movieDb_static_image) + tvShowId.tvShowPoster

        if (tvShowId.tvShowPoster.isBlank()) {
            Glide.with(this)
                .load("-")
                .transform(RoundedCorners(20))
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(movieDetailBinding.imagePoster)
        } else {
            Glide.with(this)
                .load(movieImage)
                .transform(RoundedCorners(20))
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(movieDetailBinding.imagePoster)
        }

        val season: String =
            tvShowId.tvShowEpisodes.toString() + " " + resources.getString(R.string.episode) + " | " + tvShowId.tvShowSeasons + " " + resources.getString(
                R.string.season
            )
        movieDetailBinding.movieEpisodeText.text = season

        movieDetailBinding.imagePoster.contentDescription = tvShowId.tvShowPoster
        movieDetailBinding.imgShare.setOnClickListener {
            onShareClickTvShow()
        }
    }

    private fun setMovieGenreData(movieId: ArrayList<MovieGenreListResponse>) {
        val listReview = ArrayList<MovieGenreListResponse>()
        for (movieGenre in movieId) {
            val movieGenreApi = MovieGenreListResponse(
                movieGenre.id,
                movieGenre.name
            )
            listReview.add(movieGenreApi)
        }
        val adapter = MovieGenreApiAdapter(listReview)
        movieDetailBinding.rvGenreApi.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun setTvShowGenreData(tvShowId: ArrayList<TvShowGenreListResponse>) {
        val listReview = ArrayList<TvShowGenreListResponse>()
        for (tvShowGenre in tvShowId) {
            val movieGenreApi = TvShowGenreListResponse(
                tvShowGenre.id,
                tvShowGenre.name
            )
            listReview.add(movieGenreApi)
        }
        val adapter = TvShowGenreApiAdapter(listReview)
        movieDetailBinding.rvGenreApi.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun setCompaniesData(movieId: ArrayList<ProductionCompaniesListResponse>) {
        val listReview = ArrayList<ProductionCompaniesListResponse>()
        for (movieGenre in movieId) {
            val movieGenreApi = ProductionCompaniesListResponse(
                movieGenre.logoPath,
                movieGenre.name,
                movieGenre.id,
                movieGenre.originCountry
            )
            listReview.add(movieGenreApi)
        }
        val adapter = CompaniesMovieAdapter(listReview)
        movieDetailBinding.rvCompanies.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onShareClickMovie() {
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
            .setChooserTitle("Share this Movie")
            .setText(
                resources.getString(R.string.share_title) + " " + movieDetailBinding.movieTitleText.text + " " + resources.getString(
                    R.string.separate
                ) + " " + movieDetailBinding.movieReleaseDate.text
            )
            .startChooser()
    }

    override fun onShareClickTvShow() {
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
            .setChooserTitle("Share this TV Show")
            .setText(
                resources.getString(R.string.share_title) + " " + movieDetailBinding.movieTitleText.text + " " + resources.getString(
                    R.string.separate
                ) + " " + movieDetailBinding.movieReleaseDate.text
            )
            .startChooser()
    }

    private fun showToast(isToast: Boolean, toastReason: String) {
        if (!isToast) {
            Toast.makeText(this, toastReason, Toast.LENGTH_LONG).show()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            hideStaticUI()
        }
    }

    private fun hideStaticUI() {
        movieDetailBinding.apply {
            imagePoster.visibility = View.GONE
            view.visibility = View.GONE
            imgShare.visibility = View.GONE
            movieTitleText.visibility = View.GONE
            descText.visibility = View.GONE
            movieReleaseDate.visibility = View.GONE
            rvGenreApi.visibility = View.GONE
            rvCompanies.visibility = View.GONE
            companiesListModule.visibility = View.GONE
            calendarImg.visibility = View.GONE
            movieRating.visibility = View.GONE
            movieRatingText.visibility = View.GONE
            movieEpisodeText.visibility = View.GONE
            movieEpisodeImg.visibility = View.GONE
            descTitle.visibility = View.GONE
            movieRevenueText.visibility = View.GONE
            movieRevenueImg.visibility = View.GONE
            movieLanguageText.visibility = View.GONE
            movieLanguageImg.visibility = View.GONE
            moviePopularityTxt.visibility = View.GONE
            moviePopularityImg.visibility = View.GONE
            tvShowLanguageImg.visibility = View.GONE
            tvShowLanguageText.visibility = View.GONE
        }
    }

    private fun showStaticUIMovie() {
        movieDetailBinding.apply {
            imagePoster.visibility = View.VISIBLE
            view.visibility = View.VISIBLE
            imgShare.visibility = View.VISIBLE
            movieReleaseDate.visibility = View.VISIBLE
            movieTitleText.visibility = View.VISIBLE
            descText.visibility = View.VISIBLE
            descTitle.visibility = View.VISIBLE
            rvGenreApi.visibility = View.VISIBLE
            rvCompanies.visibility = View.VISIBLE
            companiesListModule.visibility = View.VISIBLE
            calendarImg.visibility = View.VISIBLE
            movieRating.visibility = View.VISIBLE
            movieRatingText.visibility = View.VISIBLE
            movieEpisodeText.visibility = View.INVISIBLE
            movieEpisodeImg.visibility = View.INVISIBLE
            movieRevenueImg.visibility = View.VISIBLE
            movieRevenueText.visibility = View.VISIBLE
            movieLanguageText.visibility = View.VISIBLE
            movieLanguageImg.visibility = View.VISIBLE
            moviePopularityTxt.visibility = View.INVISIBLE
            moviePopularityImg.visibility = View.INVISIBLE
            tvShowLanguageImg.visibility = View.INVISIBLE
            tvShowLanguageText.visibility = View.INVISIBLE
        }
    }

    private fun showStaticUITvShow() {
        movieDetailBinding.apply {
            imagePoster.visibility = View.VISIBLE
            view.visibility = View.VISIBLE
            imgShare.visibility = View.VISIBLE
            movieReleaseDate.visibility = View.VISIBLE
            movieTitleText.visibility = View.VISIBLE
            descText.visibility = View.VISIBLE
            rvGenreApi.visibility = View.VISIBLE
            rvCompanies.visibility = View.VISIBLE
            companiesListModule.visibility = View.VISIBLE
            calendarImg.visibility = View.VISIBLE
            movieRating.visibility = View.VISIBLE
            movieRatingText.visibility = View.VISIBLE
            descTitle.visibility = View.VISIBLE
            movieRevenueImg.visibility = View.INVISIBLE
            movieRevenueText.visibility = View.INVISIBLE
            movieLanguageText.visibility = View.INVISIBLE
            movieLanguageImg.visibility = View.INVISIBLE
            movieEpisodeImg.visibility = View.VISIBLE
            movieEpisodeText.visibility = View.VISIBLE
            moviePopularityTxt.visibility = View.INVISIBLE
            moviePopularityImg.visibility = View.INVISIBLE
            tvShowLanguageImg.visibility = View.VISIBLE
            tvShowLanguageText.visibility = View.VISIBLE
        }
    }

    private fun configAdapterGenres() {
        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val dividerItemDecoration =
            DividerItemDecoration(
                movieDetailBinding.rvGenreApi.context,
                DividerItemDecoration.HORIZONTAL
            )
        movieDetailBinding.rvGenreApi.isNestedScrollingEnabled = false
        movieDetailBinding.rvGenreApi.setHasFixedSize(true)
        movieDetailBinding.rvGenreApi.layoutManager = linearLayoutManager
        movieDetailBinding.rvGenreApi.addItemDecoration(dividerItemDecoration)
    }

    private fun configAdapterCompanies() {
        movieDetailBinding.rvCompanies.isNestedScrollingEnabled = false
        movieDetailBinding.rvCompanies.layoutManager = LinearLayoutManager(this)
        movieDetailBinding.rvCompanies.setHasFixedSize(true)
        val dividerItemDecoration =
            DividerItemDecoration(
                movieDetailBinding.rvCompanies.context,
                DividerItemDecoration.VERTICAL
            )
        movieDetailBinding.rvCompanies.isNestedScrollingEnabled = false
        movieDetailBinding.rvCompanies.setHasFixedSize(true)
        movieDetailBinding.rvCompanies.addItemDecoration(dividerItemDecoration)
    }

    companion object {
        const val SHOW_ID = "show_id"
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV_SHOW = "extra_tvShow"
    }
}