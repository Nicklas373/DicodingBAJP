package com.dicoding.moviecatalog.activity

import android.os.Bundle
import android.view.View
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
import com.dicoding.moviecatalog.callback.ShareCallback
import com.dicoding.moviecatalog.data.movie.MovieEntity
import com.dicoding.moviecatalog.data.tvshow.TvShowEntity
import com.dicoding.moviecatalog.databinding.ActivityDetailShowBinding
import com.dicoding.moviecatalog.databinding.ContentDetailShowBinding
import com.dicoding.moviecatalog.viewmodel.DetailMovieViewModel
import com.dicoding.moviecatalog.viewmodel.ViewModelFactory

class DetailShowActivity : AppCompatActivity(), ShareCallback {

    private lateinit var movieDetailBinding: ContentDetailShowBinding

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
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(
            this, factory
        )[DetailMovieViewModel::class.java]
        val extras = intent.extras
        if (extras != null) {
            val showId = extras.getString(SHOW_ID)
            if (showId.equals("Movie")) {
                val movieId = extras.getString(EXTRA_MOVIE)
                if (movieId != null) {
                    hideStaticUI()
                    movieDetailBinding.progressBar.visibility = View.VISIBLE
                    viewModel.setSelectedMovie(movieId)
                    viewModel.getCastMovie(movieId).observe(this, { movie ->
                        movieDetailBinding.progressBar.visibility = View.GONE
                        showStaticUI()
                        adapter.setMovieModule(movie)
                        adapter.notifyDataSetChanged()
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
                        showStaticUI()
                        adapter2.setTvShowCastList(tvShow)
                        adapter2.notifyDataSetChanged()
                    })
                    viewModel.getTvShow().observe(this, { tvShow -> populateTvShow(tvShow) })
                }
            }
        }

        movieDetailBinding.rvCast.isNestedScrollingEnabled = false
        movieDetailBinding.rvCast.layoutManager = LinearLayoutManager(this)
        movieDetailBinding.rvCast.setHasFixedSize(true)
        if (extras != null) {
            val showId = extras.getString(SHOW_ID)
            if (showId.equals("Movie")) {
                movieDetailBinding.rvCast.adapter = adapter
            } else if (showId.equals("TvShow")) {
                movieDetailBinding.rvCast.adapter = adapter2
            }
        }
        val dividerItemDecoration =
            DividerItemDecoration(movieDetailBinding.rvCast.context, DividerItemDecoration.VERTICAL)
        movieDetailBinding.rvCast.addItemDecoration(dividerItemDecoration)
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
            movieEpisodeImg.visibility = View.INVISIBLE
            movieEpisodeText.visibility = View.INVISIBLE
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
            calendarImg.visibility = View.GONE
            movieDurationImg.visibility = View.GONE
            movieRating.visibility = View.GONE
            descTitle.visibility = View.GONE
            castListModule.visibility = View.GONE
        }
    }

    private fun showStaticUI() {
        movieDetailBinding.apply {
            imagePoster.visibility = View.VISIBLE
            movieEpisodeImg.visibility = View.VISIBLE
            view.visibility = View.VISIBLE
            imgShare.visibility = View.VISIBLE
            cvGenre1.visibility = View.VISIBLE
            cvGenre2.visibility = View.VISIBLE
            calendarImg.visibility = View.VISIBLE
            movieDurationImg.visibility = View.VISIBLE
            movieRating.visibility = View.VISIBLE
            descTitle.visibility = View.VISIBLE
            castListModule.visibility = View.VISIBLE
        }
    }

    companion object {
        const val SHOW_ID = "show_id"
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_MOVIE_API = "extra_movie_api"
        const val EXTRA_TV_SHOW = "extra_tvshow"
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