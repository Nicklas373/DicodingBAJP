package com.dicoding.moviecatalog.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.adapter.DetailMovieAdapter
import com.dicoding.moviecatalog.adapter.DetailTvShowAdapter
import com.dicoding.moviecatalog.adapter.MovieGenreApiAdapter
import com.dicoding.moviecatalog.callback.ShareCallback
import com.dicoding.moviecatalog.data.movie.MovieEntity
import com.dicoding.moviecatalog.data.movie.response.MovieGenreListResponse
import com.dicoding.moviecatalog.data.movie.response.MovieListResponse
import com.dicoding.moviecatalog.data.tvshow.TvShowEntity
import com.dicoding.moviecatalog.databinding.ActivityDetailShowBinding
import com.dicoding.moviecatalog.databinding.ContentDetailShowBinding
import com.dicoding.moviecatalog.viewmodel.DetailMovieViewModel
import com.dicoding.moviecatalog.viewmodel.ViewModelFactory
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DetailShowActivity : AppCompatActivity(), ShareCallback {

    private lateinit var movieDetailBinding: ContentDetailShowBinding
    private val movieGenreAdapter = ArrayList<MovieGenreListResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_show)

        val activityMovieDetailBinding = ActivityDetailShowBinding.inflate(layoutInflater)
        movieDetailBinding = activityMovieDetailBinding.detailContent

        setContentView(activityMovieDetailBinding.root)

        setSupportActionBar(activityMovieDetailBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = DetailMovieAdapter()
        val adapter2 = DetailTvShowAdapter()
        val adapter3 = MovieGenreApiAdapter(movieGenreAdapter)
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(
            this, factory
        )[DetailMovieViewModel::class.java]
        val extras = intent.extras
        if (extras != null) {
            val showId = extras.getString(SHOW_ID)
            val movieIdDb = extras.getString(EXTRA_MOVIE_DB)
            val movieId = extras.getString(EXTRA_MOVIE)
            val movieIdApi = extras.getString(EXTRA_MOVIE_API)
            if (showId.equals("Movie")) {
                if (movieId != null) {
                    hideStaticUI()
                    movieDetailBinding.progressBar.visibility = View.VISIBLE
                    viewModel.setSelectedMovie(movieId)
                    viewModel.getCastMovie(movieId).observe(this, { movie ->
                        movieDetailBinding.progressBar.visibility = View.GONE
                        adapter.setMovieModule(movie)
                        adapter.notifyDataSetChanged()
                        showStaticUIDB()
                    })
                    viewModel.getMovie().observe(this, { movie -> populateMovie(movie) })
                }
            } else if (showId.equals("TvShow")) {
                val tvShowId = extras.getString(EXTRA_TV_SHOW)
                if (tvShowId != null) {
                    hideStaticUI()
                    movieDetailBinding.progressBar.visibility = View.VISIBLE
                    viewModel.setSelectedTvShow(tvShowId)
                    viewModel.getCastTvShow(tvShowId).observe(this, { tvShow ->
                        movieDetailBinding.progressBar.visibility = View.GONE
                        adapter2.setTvShowCastList(tvShow)
                        adapter2.notifyDataSetChanged()
                        showStaticUIDB()
                    })
                    viewModel.getTvShow().observe(this, { tvShow -> populateTvShow(tvShow) })
                    movieDetailBinding.rvCast.adapter = adapter2
                }
            }

            viewModel.movieDetailList.observe(this, { movieDetailId ->
                hideStaticUI()
                movieDetailBinding.progressBar.visibility = View.VISIBLE
                if (movieIdApi != null) {
                    movieDetailBinding.progressBar.visibility = View.GONE
                    setMovieData(movieDetailId)
                    showStaticUIAPI()
                }
            })

            viewModel.movieGenreList.observe(this, { movieGenreId ->
                hideStaticUI()
                movieDetailBinding.progressBar.visibility = View.VISIBLE
                if (movieIdApi != null) {
                    movieDetailBinding.progressBar.visibility = View.GONE
                    setMovieGenreData(movieGenreId)
                    adapter3.notifyDataSetChanged()
                    showStaticUIAPI()
                }
            })

            if (movieIdApi != null) {
                viewModel.getMovieListDetails(movieIdApi)
                viewModel.getMovieGenreListDetails(movieIdApi)
                if (showId.equals("Movie")) {
                    if (movieIdDb != null) {
                        hideStaticUI()
                        movieDetailBinding.progressBar.visibility = View.VISIBLE
                        viewModel.getCastMovie(movieIdDb).observe(this, { movie ->
                            movieDetailBinding.progressBar.visibility = View.GONE
                            movieDetailBinding.rvCast.adapter = adapter
                            adapter.setMovieModule(movie)
                            adapter.notifyDataSetChanged()
                            showStaticUIAPI()
                        })
                    }
                } else if (showId.equals("TvShow")) {
                    val tvShowId = extras.getString(EXTRA_TV_SHOW)
                    if (tvShowId != null) {
                        hideStaticUI()
                        movieDetailBinding.progressBar.visibility = View.VISIBLE
                        viewModel.getCastTvShow(tvShowId).observe(this, { tvShow ->
                            movieDetailBinding.progressBar.visibility = View.GONE
                            movieDetailBinding.rvCast.adapter = adapter2
                            adapter2.setTvShowCastList(tvShow)
                            adapter2.notifyDataSetChanged()
                            showStaticUIAPI()
                        })
                    }
                }
            }
        }

        viewModel.isToast.observe(this, { isToast ->
            showToast(isToast, viewModel.toastReason.value.toString())
        })

        viewModel.isLoading.observe(this, {
            showLoading(it)
        })

        configAdapter()
        configAdapterApi()
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
            val setDate = setReleaseDate(movieId.releaseDate)
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
            val formatter: NumberFormat = DecimalFormat("#,###")
            val myNumber: Int = movieId.revenue
            val formattedNumber: String = formatter.format(myNumber)
            movieDetailBinding.movieRevenueText.text = formattedNumber
        }

        if (movieId.originalLanguage.isBlank()) {
            movieDetailBinding.movieLanguageText.text = "-"
        } else {
            movieDetailBinding.movieLanguageText.text = movieId.originalLanguage
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
    }

    private fun populateMovie(movieEntity: MovieEntity) {
        movieDetailBinding.apply {
            movieTitleText.text = movieEntity.title
            movieReleaseDate.text = movieEntity.releaseDate
            movieDurationText.text = movieEntity.duration
            movieRatingText.text = movieEntity.rating
            descText.text = movieEntity.description
            if (movieEntity.genre1 == "Null") {
                cvGenre1.visibility = View.INVISIBLE
            } else {
                movieGenre1Text.text = movieEntity.genre1
                genreColoringGenre1(movieEntity.genre1)
            }
            if (movieEntity.genre2 == "Null") {
                cvGenre2.visibility = View.INVISIBLE
            } else {
                movieGenre2Text.text = movieEntity.genre2
                genreColoringGenre2(movieEntity.genre2)
            }
        }

        Glide.with(this)
            .load(movieEntity.imagePath)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(movieDetailBinding.imagePoster)

        movieDetailBinding.imagePoster.contentDescription = movieEntity.imagePath
        movieDetailBinding.imgShare.setOnClickListener {
            onShareClickMovie(movieEntity)
        }
    }

    private fun populateTvShow(tvShowEntity: TvShowEntity) {
        movieDetailBinding.apply {
            movieTitleText.text = tvShowEntity.title
            movieReleaseDate.text = tvShowEntity.releaseDate
            movieDurationText.text = tvShowEntity.duration
            movieRatingText.text = tvShowEntity.rating
            descText.text = tvShowEntity.description
            val season: String =
                tvShowEntity.episode + " | " + tvShowEntity.season
            movieEpisodeText.text = season

            if (tvShowEntity.genre1 == "Null") {
                cvGenre1.visibility = View.INVISIBLE
            } else {
                movieGenre1Text.text = tvShowEntity.genre1
                genreColoringGenre1(tvShowEntity.genre1)
            }
            if (tvShowEntity.genre2 == "Null") {
                cvGenre2.visibility = View.INVISIBLE
            } else {
                movieGenre2Text.text = tvShowEntity.genre2
                genreColoringGenre2(tvShowEntity.genre2)
            }
        }

        Glide.with(this)
            .load(tvShowEntity.imagePath)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(movieDetailBinding.imagePoster)

        movieDetailBinding.imagePoster.contentDescription = tvShowEntity.imagePath

        movieDetailBinding.imgShare.setOnClickListener {
            onShareClickTvShow(tvShowEntity)
        }
    }

    override fun onShareClickMovie(movie: MovieEntity) {
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

    override fun onShareClickTvShow(tvShow: TvShowEntity) {
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
            movieDetailBinding.apply {
                progressBar.visibility = View.VISIBLE
                imagePoster.visibility = View.GONE
                movieEpisodeImg.visibility = View.GONE
                view.visibility = View.GONE
                imgShare.visibility = View.GONE
                cvGenre1.visibility = View.GONE
                cvGenre2.visibility = View.GONE
                rvGenreApi.visibility = View.GONE
                calendarImg.visibility = View.GONE
                movieDurationImg.visibility = View.GONE
                movieDurationText.visibility = View.GONE
                movieEpisodeText.visibility = View.GONE
                movieEpisodeImg.visibility = View.GONE
                descTitle.visibility = View.GONE
                castListModule.visibility = View.GONE
                movieRevenueText.visibility = View.GONE
                movieRevenueImg.visibility = View.GONE
                movieLanguageText.visibility = View.GONE
                movieLanguageImg.visibility = View.GONE
            }
        } else {
            movieDetailBinding.apply {
                progressBar.visibility = View.GONE
                imagePoster.visibility = View.VISIBLE
                view.visibility = View.VISIBLE
                imgShare.visibility = View.VISIBLE
                cvGenre1.visibility = View.VISIBLE
                cvGenre2.visibility = View.VISIBLE
                rvCast.visibility = View.VISIBLE
                rvGenreApi.visibility = View.VISIBLE
                calendarImg.visibility = View.VISIBLE
                descTitle.visibility = View.VISIBLE
                castListModule.visibility = View.VISIBLE
                movieDurationImg.visibility = View.VISIBLE
                movieDurationText.visibility = View.VISIBLE
                movieEpisodeText.visibility = View.VISIBLE
                movieEpisodeImg.visibility = View.VISIBLE
                movieRevenueImg.visibility = View.VISIBLE
                movieRevenueText.visibility = View.VISIBLE
                movieLanguageText.visibility = View.VISIBLE
                movieLanguageImg.visibility = View.VISIBLE
            }
        }
    }

    private fun genreColoringGenre1(genre1: String) {
        when (genre1) {
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

    private fun genreColoringGenre2(genre2: String) {
        when (genre2) {
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

    private fun hideStaticUI() {
        movieDetailBinding.apply {
            imagePoster.visibility = View.GONE
            movieEpisodeImg.visibility = View.GONE
            view.visibility = View.GONE
            imgShare.visibility = View.GONE
            cvGenre1.visibility = View.GONE
            cvGenre2.visibility = View.GONE
            rvGenreApi.visibility = View.GONE
            calendarImg.visibility = View.GONE
            movieDurationImg.visibility = View.GONE
            movieDurationText.visibility = View.GONE
            movieEpisodeText.visibility = View.GONE
            movieEpisodeImg.visibility = View.GONE
            descTitle.visibility = View.GONE
            movieRevenueText.visibility = View.GONE
            movieRevenueImg.visibility = View.GONE
            movieLanguageText.visibility = View.GONE
            movieLanguageImg.visibility = View.GONE
        }
    }

    private fun showStaticUIDB() {
        movieDetailBinding.apply {
            imagePoster.visibility = View.VISIBLE
            movieEpisodeImg.visibility = View.VISIBLE
            view.visibility = View.VISIBLE
            imgShare.visibility = View.VISIBLE
            cvGenre1.visibility = View.VISIBLE
            cvGenre2.visibility = View.VISIBLE
            rvGenreApi.visibility = View.INVISIBLE
            calendarImg.visibility = View.VISIBLE
            movieDurationImg.visibility = View.VISIBLE
            movieRating.visibility = View.VISIBLE
            movieDurationText.visibility = View.VISIBLE
            movieRatingText.visibility = View.VISIBLE
            descTitle.visibility = View.VISIBLE
            castListModule.visibility = View.VISIBLE
            movieRevenueImg.visibility = View.INVISIBLE
            movieRevenueText.visibility = View.INVISIBLE
            movieLanguageText.visibility = View.INVISIBLE
            movieLanguageImg.visibility = View.INVISIBLE
        }
    }

    private fun showStaticUIAPI() {
        movieDetailBinding.apply {
            imagePoster.visibility = View.VISIBLE
            view.visibility = View.VISIBLE
            imgShare.visibility = View.VISIBLE
            cvGenre1.visibility = View.INVISIBLE
            cvGenre2.visibility = View.INVISIBLE
            rvCast.visibility = View.VISIBLE
            rvGenreApi.visibility = View.VISIBLE
            calendarImg.visibility = View.VISIBLE
            descTitle.visibility = View.VISIBLE
            castListModule.visibility = View.VISIBLE
            movieDurationImg.visibility = View.INVISIBLE
            movieDurationText.visibility = View.INVISIBLE
            movieEpisodeText.visibility = View.INVISIBLE
            movieEpisodeImg.visibility = View.INVISIBLE
            movieRevenueImg.visibility = View.VISIBLE
            movieRevenueText.visibility = View.VISIBLE
            movieLanguageText.visibility = View.VISIBLE
            movieLanguageImg.visibility = View.VISIBLE
        }
    }

    private fun configAdapterApi() {
        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val dividerItemDecoration =
            DividerItemDecoration(
                movieDetailBinding.rvGenreApi.context,
                DividerItemDecoration.HORIZONTAL
            )
        movieDetailBinding.rvGenreApi.layoutManager = linearLayoutManager
        movieDetailBinding.rvGenreApi.addItemDecoration(dividerItemDecoration)
    }

    private fun configAdapter() {
        movieDetailBinding.rvCast.isNestedScrollingEnabled = false
        movieDetailBinding.rvCast.layoutManager = LinearLayoutManager(this)
        movieDetailBinding.rvCast.setHasFixedSize(true)
        val dividerItemDecoration =
            DividerItemDecoration(movieDetailBinding.rvCast.context, DividerItemDecoration.VERTICAL)
        movieDetailBinding.rvCast.addItemDecoration(dividerItemDecoration)
    }

    private fun setReleaseDate(releaseDate: String): String {
        val outputFormat: DateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.US)
        val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val date: Date = inputFormat.parse(releaseDate)

        return outputFormat.format(date)
    }

    companion object {
        const val SHOW_ID = "show_id"
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_MOVIE_DB = "extra_movie_db"
        const val EXTRA_MOVIE_API = "extra_movie_api"
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
    }
}