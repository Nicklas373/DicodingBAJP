package com.dicoding.moviecatalog.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.callback.ShareCallback
import com.dicoding.moviecatalog.data.source.local.entity.movie.MovieDetailEntity
import com.dicoding.moviecatalog.data.source.local.entity.tvshow.TvShowDetailEntity
import com.dicoding.moviecatalog.databinding.ActivityDetailShowBinding
import com.dicoding.moviecatalog.databinding.ContentDetailShowBinding
import com.dicoding.moviecatalog.utils.InlineVariable
import com.dicoding.moviecatalog.viewmodel.DetailViewModel
import com.dicoding.moviecatalog.viewmodel.ViewModelFactory
import com.dicoding.moviecatalog.vo.Status
import com.squareup.picasso.Picasso

class DetailShowActivity : AppCompatActivity(), ShareCallback {

    private lateinit var movieDetailBinding: ContentDetailShowBinding
    private lateinit var activityDetailBinding: ActivityDetailShowBinding
    private var inlineVariable = InlineVariable()
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_show)

        val activityMovieDetailBinding = ActivityDetailShowBinding.inflate(layoutInflater)
        movieDetailBinding = activityMovieDetailBinding.detailContent
        activityDetailBinding = activityMovieDetailBinding

        setContentView(activityMovieDetailBinding.root)

        setSupportActionBar(activityMovieDetailBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(
            this, factory
        )[DetailViewModel::class.java]

        val factoryDetail = ViewModelFactory.getInstance(this)
        this.detailViewModel = ViewModelProvider(this, factoryDetail)[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val showId = extras.getString(SHOW_ID)
            val movieId = extras.getInt(EXTRA_MOVIE)
            val tvShowId = extras.getInt(EXTRA_TV_SHOW)
            if (showId.equals("Movie")) {
                hideStaticUI()
                if (movieId.toString().isNotBlank()) {
                    movieDetailBinding.progressBar.visibility = View.VISIBLE
                    viewModel.nSetSelectedMovie(movieId)
                    viewModel.nGetSelectedMovie(movieId)
                    setSusFavMovie()
                    viewModel.nGetSusMovie().observe(this) { movieWithDetails ->
                        if (movieWithDetails != null) {
                            when (movieWithDetails.status) {
                                Status.LOADING -> movieDetailBinding.progressBar.visibility =
                                    View.VISIBLE
                                Status.SUCCESS -> if (movieWithDetails.data != null) {
                                    movieDetailBinding.progressBar.visibility = View.GONE
                                    showStaticUIMovie()
                                    setMovieData(movieWithDetails.data)
                                    setFavoriteState(movieWithDetails.data.isSus)
                                }
                                Status.ERROR -> {
                                    movieDetailBinding.progressBar.visibility = View.GONE
                                    Toast.makeText(
                                        applicationContext,
                                        resources.getString(R.string.error),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                }
            } else if (showId.equals("TvShow")) {
                hideStaticUI()
                if (tvShowId.toString().isNotBlank()) {
                    viewModel.nSetSelectedTvShow(tvShowId)
                    viewModel.nGetSelectedTvShow(tvShowId)
                    setSusFavTvShow()
                    movieDetailBinding.progressBar.visibility = View.VISIBLE
                    viewModel.nGetSusTvShow().observe(this) { tvShowWithDetails ->
                        if (tvShowWithDetails != null) {
                            when (tvShowWithDetails.status) {
                                Status.LOADING -> movieDetailBinding.progressBar.visibility =
                                    View.VISIBLE
                                Status.SUCCESS -> if (tvShowWithDetails.data != null) {
                                    movieDetailBinding.progressBar.visibility = View.GONE
                                    showStaticUITvShow()
                                    setTvShowData(tvShowWithDetails.data)
                                    setFavoriteState(tvShowWithDetails.data.isSus)
                                }
                                Status.ERROR -> {
                                    movieDetailBinding.progressBar.visibility = View.GONE
                                    Toast.makeText(
                                        applicationContext,
                                        resources.getString(R.string.error),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                }
            }
        }

        viewModel.isToast.observe(this) { isToast ->
            showToast(isToast, viewModel.toastReason.value.toString())
        }

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

        activityDetailBinding.menuFab.setOnClickListener {
            when {
                activityDetailBinding.susFab.visibility == View.VISIBLE -> {
                    activityDetailBinding.menuFab.setIconResource(R.drawable.ic_baseline_menu_24)
                    activityDetailBinding.susFab.visibility = View.GONE
                    activityDetailBinding.shareFab.visibility = View.GONE
                }
                activityDetailBinding.shareFab.visibility == View.VISIBLE -> {
                    activityDetailBinding.menuFab.setIconResource(R.drawable.ic_baseline_menu_24)
                    activityDetailBinding.susFab.visibility = View.GONE
                    activityDetailBinding.shareFab.visibility = View.GONE
                }
                activityDetailBinding.menuFab.visibility == View.VISIBLE -> {
                    activityDetailBinding.menuFab.setIconResource(R.drawable.ic_baseline_menu_open_24)
                    activityDetailBinding.susFab.visibility = View.VISIBLE
                    activityDetailBinding.shareFab.visibility = View.VISIBLE
                }
            }
        }

        activityDetailBinding.susFab.setOnClickListener {
            if (extras != null) {
                val showId = extras.getString(SHOW_ID)
                if (showId.equals("Movie")) {
                    detailViewModel.nUpdateFavMovie()
                } else if (showId.equals("TvShow")) {
                    detailViewModel.nUpdateFavTvShow()
                }
            }
        }

        activityDetailBinding.shareFab.setOnClickListener {
            if (extras != null) {
                val showId = extras.getString(SHOW_ID)
                if (showId.equals("Movie")) {
                    onShareClickMovie()
                } else if (showId.equals("TvShow")) {
                    onShareClickTvShow()
                }
            }
        }
    }

    private fun setSusFavMovie() {
        detailViewModel.nGetSusMovie().observe(this) { susMovie ->
            when (susMovie.status) {
                Status.LOADING -> showLoading(true)
                Status.SUCCESS -> {
                    if (susMovie.data != null) {
                        showLoading(false)
                        val newSus = susMovie.data.isSus
                        if (newSus) {
                            showToast(
                                true,
                                resources.getString(R.string.add_to_favorite)
                            )
                        } else {
                            showToast(
                                true,
                                resources.getString(R.string.add_to_favorite)
                            )
                        }
                        setFavoriteState(newSus)
                    }
                }
                Status.ERROR -> {
                    showLoading(false)
                    Toast.makeText(
                        applicationContext,
                        resources.getString(R.string.error),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

    private fun setSusFavTvShow() {
        detailViewModel.nGetSusTvShow().observe(this) { susTvShow ->
            when (susTvShow.status) {
                Status.LOADING -> showLoading(true)
                Status.SUCCESS -> {
                    if (susTvShow.data != null) {
                        showLoading(false)
                        val newSus = susTvShow.data.isSus
                        if (newSus) {
                            showToast(
                                true,
                                resources.getString(R.string.add_to_favorite)
                            )
                        } else {
                            showToast(
                                true,
                                resources.getString(R.string.remove_from_favorite)
                            )
                        }
                        setFavoriteState(newSus)
                    }
                }
                Status.ERROR -> {
                    showLoading(false)
                    Toast.makeText(
                        applicationContext,
                        resources.getString(R.string.error),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

    private fun setFavoriteState(state: Boolean) {
        val fab = activityDetailBinding.susFab
        if (!state) {
            fab.setIconResource(R.drawable.ic_baseline_favorite_fill)
            fab.text = resources.getString(R.string.add_to_favorite)
        } else {
            fab.setIconResource(R.drawable.ic_baseline_favorite_border_24)
            fab.text = resources.getString(R.string.remove_from_favorite)
        }
        activityDetailBinding.menuFab.setIconResource(R.drawable.ic_baseline_menu_24)
    }

    private fun setMovieData(movieId: MovieDetailEntity) {
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

        if (movieId.compName_1.equals(null)) {
            movieDetailBinding.nameCompaniesTxt1.text = "-"
        } else {
            movieDetailBinding.nameCompaniesTxt1.text = movieId.compName_1
        }

        when (movieId.compName_2) {
            "null" -> movieDetailBinding.cvItemCompanies2.visibility = View.GONE
            else -> movieDetailBinding.nameCompaniesTxt2.text = movieId.compName_2
        }

        if (movieId.compOrigin_1.equals(null)) {
            movieDetailBinding.originTxt1.text = "-"
        } else {
            movieDetailBinding.originTxt1.text = movieId.compOrigin_1
        }

        when (movieId.compOrigin_2) {
            "null" -> movieDetailBinding.cvItemCompanies2.visibility = View.GONE
            else -> movieDetailBinding.originTxt2.text = movieId.compOrigin_2
        }

        val movieImage = getString(R.string.movieDb_static_image) + movieId.posterPath
        val compImage1 = getString(R.string.movieDb_static_image) + movieId.compLogo_1
        val compImage2 = getString(R.string.movieDb_static_image) + movieId.compLogo_2

        if (movieId.posterPath.isBlank()) {
            Picasso.get()
                .load(R.drawable.ic_error)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .into(movieDetailBinding.imagePoster)
        } else {
            Picasso.get()
                .load(movieImage)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .into(movieDetailBinding.imagePoster)
        }

        if (movieId.compLogo_1.isBlank()) {
            Picasso.get()
                .load(R.drawable.ic_error)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .into(movieDetailBinding.imgCompanies1)
        } else {
            Picasso.get()
                .load(compImage1)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .into(movieDetailBinding.imgCompanies1)
        }

        if (movieId.compLogo_2.isBlank()) {
            Picasso.get()
                .load(R.drawable.ic_error)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .into(movieDetailBinding.imgCompanies2)
        } else {
            Picasso.get()
                .load(compImage2)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .into(movieDetailBinding.imgCompanies2)
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

        if (movieId.genres_1.isBlank()) {
            movieDetailBinding.movieGenreText1.text = "-"
        } else {
            if (movieId.genres_1 == science) {
                movieDetailBinding.movieGenreText1.text = sciFi
            } else {
                movieDetailBinding.movieGenreText1.text = movieId.genres_1
            }
            genreColoringApi1(movieId.genres_1)
        }

        if (movieId.genres_2.isBlank()) {
            movieDetailBinding.movieGenreText2.text = "-"
        } else {
            when (movieId.genres_2) {
                science -> movieDetailBinding.movieGenreText2.text = sciFi
                "null" -> movieDetailBinding.cvGenre2.visibility = View.GONE
                else -> movieDetailBinding.movieGenreText2.text = movieId.genres_2
            }
            genreColoringApi2(movieId.genres_2)
        }

        movieDetailBinding.imagePoster.contentDescription = movieId.posterPath
        movieDetailBinding.imgCompanies1.contentDescription = movieId.compLogo_1
        movieDetailBinding.imgCompanies2.contentDescription = movieId.compLogo_2
    }

    private fun setTvShowData(tvShowId: TvShowDetailEntity) {
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

        if (tvShowId.tvShowGenres_1.isBlank()) {
            movieDetailBinding.movieGenreText1.text = "-"
        } else {
            if (tvShowId.tvShowGenres_1 == science) {
                movieDetailBinding.movieGenreText1.text = sciFi
            } else {
                movieDetailBinding.movieGenreText1.text = tvShowId.tvShowGenres_1
            }
            genreColoringApi2(tvShowId.tvShowGenres_1)
        }

        if (tvShowId.tvShowGenres_2.isBlank()) {
            movieDetailBinding.movieGenreText2.text = "-"
        } else {
            when (tvShowId.tvShowGenres_2) {
                science -> movieDetailBinding.movieGenreText2.text = sciFi
                "null" -> movieDetailBinding.cvGenre2.visibility = View.GONE
                else -> movieDetailBinding.movieGenreText2.text = tvShowId.tvShowGenres_2
            }
            genreColoringApi2(tvShowId.tvShowGenres_2)
        }

        if (tvShowId.compName_1.equals(null)) {
            movieDetailBinding.nameCompaniesTxt1.text = "-"
        } else {
            movieDetailBinding.nameCompaniesTxt1.text = tvShowId.compName_1
        }

        when (tvShowId.compName_2) {
            "null" -> movieDetailBinding.cvItemCompanies2.visibility = View.GONE
            else -> movieDetailBinding.nameCompaniesTxt2.text = tvShowId.compName_2
        }

        if (tvShowId.compOrigin_1.equals(null)) {
            movieDetailBinding.originTxt1.text = "-"
        } else {
            movieDetailBinding.originTxt1.text = tvShowId.compOrigin_1
        }

        when (tvShowId.compOrigin_2) {
            "null" -> movieDetailBinding.cvItemCompanies2.visibility = View.GONE
            else -> movieDetailBinding.originTxt2.text = tvShowId.compOrigin_2
        }

        val movieImage = getString(R.string.movieDb_static_image) + tvShowId.tvShowPoster
        val compImage1 = getString(R.string.movieDb_static_image) + tvShowId.compLogo_1
        val compImage2 = getString(R.string.movieDb_static_image) + tvShowId.compLogo_2

        if (tvShowId.tvShowPoster.isBlank()) {
            Picasso.get()
                .load(R.drawable.ic_error)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .into(movieDetailBinding.imagePoster)
        } else {
            Picasso.get()
                .load(movieImage)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .into(movieDetailBinding.imagePoster)
        }

        if (tvShowId.compLogo_1.isBlank()) {
            Picasso.get()
                .load(R.drawable.ic_error)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .into(movieDetailBinding.imgCompanies1)
        } else {
            Picasso.get()
                .load(compImage1)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .into(movieDetailBinding.imgCompanies1)
        }

        if (tvShowId.compLogo_2.isBlank()) {
            Picasso.get()
                .load(R.drawable.ic_error)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .into(movieDetailBinding.imgCompanies2)
        } else {
            Picasso.get()
                .load(compImage2)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .into(movieDetailBinding.imgCompanies2)
        }

        val season: String =
            tvShowId.tvShowEpisodes.toString() + " " + resources.getString(R.string.episode) + " | " + tvShowId.tvShowSeasons + " " + resources.getString(
                R.string.season
            )
        movieDetailBinding.movieEpisodeText.text = season

        movieDetailBinding.imagePoster.contentDescription = tvShowId.tvShowPoster
        movieDetailBinding.imgCompanies1.contentDescription = tvShowId.compLogo_1
        movieDetailBinding.imgCompanies2.contentDescription = tvShowId.compLogo_2
    }

    override fun onShareClickMovie() {
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder(this)
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
        ShareCompat.IntentBuilder(this)
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
            movieTitleText.visibility = View.GONE
            descText.visibility = View.GONE
            movieReleaseDate.visibility = View.GONE
            cvGenre1.visibility = View.GONE
            cvGenre2.visibility = View.GONE
            cvItemCompanies1.visibility = View.GONE
            cvItemCompanies2.visibility = View.GONE
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
        activityDetailBinding.apply {
            menuFab.visibility = View.GONE
            susFab.visibility = View.GONE
            shareFab.visibility = View.GONE
        }
    }

    private fun showStaticUIMovie() {
        movieDetailBinding.apply {
            imagePoster.visibility = View.VISIBLE
            view.visibility = View.VISIBLE
            movieReleaseDate.visibility = View.VISIBLE
            movieTitleText.visibility = View.VISIBLE
            descText.visibility = View.VISIBLE
            descTitle.visibility = View.VISIBLE
            cvGenre1.visibility = View.VISIBLE
            cvGenre2.visibility = View.VISIBLE
            cvItemCompanies1.visibility = View.VISIBLE
            cvItemCompanies2.visibility = View.VISIBLE
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
        activityDetailBinding.apply {
            menuFab.visibility = View.VISIBLE
            susFab.visibility = View.GONE
            shareFab.visibility = View.GONE
        }
    }

    private fun showStaticUITvShow() {
        movieDetailBinding.apply {
            imagePoster.visibility = View.VISIBLE
            view.visibility = View.VISIBLE
            movieReleaseDate.visibility = View.VISIBLE
            movieTitleText.visibility = View.VISIBLE
            descText.visibility = View.VISIBLE
            cvGenre1.visibility = View.VISIBLE
            cvGenre2.visibility = View.VISIBLE
            cvItemCompanies1.visibility = View.VISIBLE
            cvItemCompanies2.visibility = View.VISIBLE
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
        activityDetailBinding.apply {
            menuFab.visibility = View.VISIBLE
            susFab.visibility = View.GONE
            shareFab.visibility = View.GONE
        }
    }

    private fun genreColoringApi1(genre: String) {
        when (genre) {
            drama -> movieDetailBinding.cvGenre1.setCardBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.red
                )
            )
            romance -> movieDetailBinding.cvGenre1.setCardBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.red
                )
            )
            action -> movieDetailBinding.cvGenre1.setCardBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.blue
                )
            )
            adventure -> movieDetailBinding.cvGenre1.setCardBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.green
                )
            )
            music -> movieDetailBinding.cvGenre1.setCardBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.orange
                )
            )
            crime -> movieDetailBinding.cvGenre1.setCardBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.purple
                )
            )
            fantasy -> movieDetailBinding.cvGenre1.setCardBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.red
                )
            )
            thriller -> movieDetailBinding.cvGenre1.setCardBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.dark_green
                )
            )
            family -> movieDetailBinding.cvGenre1.setCardBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.blue
                )
            )
            animation -> movieDetailBinding.cvGenre1.setCardBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.red
                )
            )
            sciFi -> movieDetailBinding.cvGenre1.setCardBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.silver
                )
            )
            comedy -> movieDetailBinding.cvGenre1.setCardBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.yellow
                )
            )
            mystery -> movieDetailBinding.cvGenre1.setCardBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.green
                )
            )
        }
    }

    private fun genreColoringApi2(genre: String) {
        when (genre) {
            drama -> movieDetailBinding.cvGenre2.setCardBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.red
                )
            )
            romance -> movieDetailBinding.cvGenre2.setCardBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.red
                )
            )
            action -> movieDetailBinding.cvGenre2.setCardBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.blue
                )
            )
            adventure -> movieDetailBinding.cvGenre2.setCardBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.green
                )
            )
            music -> movieDetailBinding.cvGenre2.setCardBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.orange
                )
            )
            crime -> movieDetailBinding.cvGenre2.setCardBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.purple
                )
            )
            fantasy -> movieDetailBinding.cvGenre2.setCardBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.red
                )
            )
            thriller -> movieDetailBinding.cvGenre2.setCardBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.dark_green
                )
            )
            family -> movieDetailBinding.cvGenre2.setCardBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.blue
                )
            )
            animation -> movieDetailBinding.cvGenre2.setCardBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.red
                )
            )
            sciFi -> movieDetailBinding.cvGenre2.setCardBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.silver
                )
            )
            comedy -> movieDetailBinding.cvGenre2.setCardBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.yellow
                )
            )
            mystery -> movieDetailBinding.cvGenre2.setCardBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.green
                )
            )
        }
    }

    companion object {
        const val SHOW_ID = "show_id"
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV_SHOW = "extra_tvShow"
        const val drama = "Drama"
        const val romance = "Romance"
        const val action = "Action"
        const val adventure = "Adventure"
        const val music = "Music"
        const val crime = "Crime"
        const val fantasy = "Fantasy"
        const val thriller = "Thriller"
        const val family = "Family"
        const val animation = "Animation"
        const val sciFi = "Sci-Fi"
        const val comedy = "Comedy"
        const val mystery = "Mystery"
        const val science = "Science Fiction"
    }
}