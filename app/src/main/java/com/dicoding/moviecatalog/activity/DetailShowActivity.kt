package com.dicoding.moviecatalog.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
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

class DetailShowActivity : AppCompatActivity(), ShareCallback {

    companion object {
        const val SHOW_ID = "show_id"
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV_SHOW = "extra_tvshow"
    }

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
        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailMovieViewModel::class.java]
        val extras = intent.extras
        if (extras != null) {
            val showId = extras.getString(SHOW_ID)
            if (showId.equals("Movie")) {
                val movieId = extras.getString(EXTRA_MOVIE)
                if (movieId != null) {
                    when (movieId) {
                        "1" -> adapter.setMovieModule(viewModel.getCastMovie1())
                        "2" -> adapter.setMovieModule(viewModel.getCastMovie2())
                        "3" -> adapter.setMovieModule(viewModel.getCastMovie3())
                        "4" -> adapter.setMovieModule(viewModel.getCastMovie4())
                        "5" -> adapter.setMovieModule(viewModel.getCastMovie5())
                        "6" -> adapter.setMovieModule(viewModel.getCastMovie6())
                        "7" -> adapter.setMovieModule(viewModel.getCastMovie7())
                        "8" -> adapter.setMovieModule(viewModel.getCastMovie8())
                        "9" -> adapter.setMovieModule(viewModel.getCastMovie9())
                        "10" -> adapter.setMovieModule(viewModel.getCastMovie10())
                    }
                    viewModel.setSelectedMovie(movieId)
                    populateMovie(viewModel.getMovie())
                }
            } else if (showId.equals("TvShow")) {
                val tvShowId = extras.getString(EXTRA_TV_SHOW)
                if (tvShowId != null) {
                    when (tvShowId) {
                        "1" -> adapter2.setTvShowCastList(viewModel.getCastTvShow1())
                        "2" -> adapter2.setTvShowCastList(viewModel.getCastTvShow2())
                        "3" -> adapter2.setTvShowCastList(viewModel.getCastTvShow3())
                        "4" -> adapter2.setTvShowCastList(viewModel.getCastTvShow4())
                        "5" -> adapter2.setTvShowCastList(viewModel.getCastTvShow5())
                        "6" -> adapter2.setTvShowCastList(viewModel.getCastTvShow6())
                        "7" -> adapter2.setTvShowCastList(viewModel.getCastTvShow7())
                        "8" -> adapter2.setTvShowCastList(viewModel.getCastTvShow8())
                        "9" -> adapter2.setTvShowCastList(viewModel.getCastTvShow9())
                        "10" -> adapter2.setTvShowCastList(viewModel.getCastTvShow10())
                    }

                    viewModel.setSelectedTvShow(tvShowId)
                    populateTvShow(viewModel.getTvShow())
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
        movieDetailBinding.movieTitleText.text = movieEntity.title
        movieDetailBinding.movieReleaseDate.text = movieEntity.releaseDate
        movieDetailBinding.movieDurationText.text = movieEntity.duration
        movieDetailBinding.movieRatingText.text = movieEntity.rating
        movieDetailBinding.descText.text = movieEntity.description
        if (movieEntity.genre1 == "Null") {
            movieDetailBinding.cvGenre1.visibility = View.INVISIBLE
        } else {
            movieDetailBinding.movieGenre1Text.text = movieEntity.genre1
            genreColoringGenre1(movieEntity.genre1)
        }
        if (movieEntity.genre2 == "Null") {
            movieDetailBinding.cvGenre2.visibility = View.INVISIBLE
        } else {
            movieDetailBinding.movieGenre2Text.text = movieEntity.genre2
            genreColoringGenre2(movieEntity.genre2)
        }
        movieDetailBinding.movieEpisodeImg.visibility = View.INVISIBLE
        movieDetailBinding.movieEpisodeText.visibility = View.INVISIBLE

        Glide.with(this)
            .load(movieEntity.imagePath)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(movieDetailBinding.imagePoster)

        movieDetailBinding.imgShare.setOnClickListener {
            onShareClickMovie(movieEntity)
        }
    }

    private fun populateTvShow(tvShowEntity: TvShowEntity) {
        movieDetailBinding.movieTitleText.text = tvShowEntity.title
        movieDetailBinding.movieReleaseDate.text = tvShowEntity.releaseDate
        movieDetailBinding.movieDurationText.text = tvShowEntity.duration
        movieDetailBinding.movieRatingText.text = tvShowEntity.rating
        movieDetailBinding.descText.text = tvShowEntity.description
        val season: String =
            tvShowEntity.episode + " | " + tvShowEntity.season
        movieDetailBinding.movieEpisodeText.text = season

        if (tvShowEntity.genre1 == "Null") {
            movieDetailBinding.cvGenre1.visibility = View.INVISIBLE
        } else {
            movieDetailBinding.movieGenre1Text.text = tvShowEntity.genre1
            genreColoringGenre1(tvShowEntity.genre1)
        }
        if (tvShowEntity.genre2 == "Null") {
            movieDetailBinding.cvGenre2.visibility = View.INVISIBLE
        } else {
            movieDetailBinding.movieGenre2Text.text = tvShowEntity.genre2
            genreColoringGenre2(tvShowEntity.genre2)
        }

        Glide.with(this)
            .load(tvShowEntity.imagePath)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(movieDetailBinding.imagePoster)

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
            "Drama" -> movieDetailBinding.cvGenre1.setCardBackgroundColor(resources.getColor(R.color.red))
            "Romance" -> movieDetailBinding.cvGenre1.setCardBackgroundColor(resources.getColor(R.color.red))
            "Action" -> movieDetailBinding.cvGenre1.setCardBackgroundColor(resources.getColor(R.color.blue))
            "Adventure" -> movieDetailBinding.cvGenre1.setCardBackgroundColor(resources.getColor(R.color.green))
            "Music" -> movieDetailBinding.cvGenre1.setCardBackgroundColor(resources.getColor(R.color.orange))
            "Crime" -> movieDetailBinding.cvGenre1.setCardBackgroundColor(resources.getColor(R.color.purple))
            "Fantasy" -> movieDetailBinding.cvGenre1.setCardBackgroundColor(resources.getColor(R.color.red))
            "Thriller" -> movieDetailBinding.cvGenre1.setCardBackgroundColor(resources.getColor(R.color.dark_green))
            "Family" -> movieDetailBinding.cvGenre1.setCardBackgroundColor(resources.getColor(R.color.blue))
            "Animation" -> movieDetailBinding.cvGenre1.setCardBackgroundColor(resources.getColor(R.color.red))
            "Sci-Fi" -> movieDetailBinding.cvGenre1.setCardBackgroundColor(resources.getColor(R.color.silver))
            "Comedy" -> movieDetailBinding.cvGenre1.setCardBackgroundColor(resources.getColor(R.color.yellow))
            "Mystery" -> movieDetailBinding.cvGenre1.setCardBackgroundColor(resources.getColor(R.color.green))
        }
    }

    private fun genreColoringGenre2(genre2: String) {
        when (genre2) {
            "Drama" -> movieDetailBinding.cvGenre2.setCardBackgroundColor(resources.getColor(R.color.red))
            "Romance" -> movieDetailBinding.cvGenre2.setCardBackgroundColor(resources.getColor(R.color.red))
            "Action" -> movieDetailBinding.cvGenre2.setCardBackgroundColor(resources.getColor(R.color.blue))
            "Adventure" -> movieDetailBinding.cvGenre2.setCardBackgroundColor(resources.getColor(R.color.green))
            "Music" -> movieDetailBinding.cvGenre2.setCardBackgroundColor(resources.getColor(R.color.orange))
            "Crime" -> movieDetailBinding.cvGenre2.setCardBackgroundColor(resources.getColor(R.color.purple))
            "Fantasy" -> movieDetailBinding.cvGenre2.setCardBackgroundColor(resources.getColor(R.color.red))
            "Thriller" -> movieDetailBinding.cvGenre2.setCardBackgroundColor(resources.getColor(R.color.dark_green))
            "Family" -> movieDetailBinding.cvGenre2.setCardBackgroundColor(resources.getColor(R.color.blue))
            "Animation" -> movieDetailBinding.cvGenre2.setCardBackgroundColor(resources.getColor(R.color.red))
            "Sci-Fi" -> movieDetailBinding.cvGenre2.setCardBackgroundColor(resources.getColor(R.color.silver))
            "Comedy" -> movieDetailBinding.cvGenre2.setCardBackgroundColor(resources.getColor(R.color.yellow))
            "Mystery" -> movieDetailBinding.cvGenre2.setCardBackgroundColor(resources.getColor(R.color.green))
        }
    }
}