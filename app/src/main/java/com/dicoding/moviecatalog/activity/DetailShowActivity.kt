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
                    if (movieId == "1") {
                        val cast = viewModel.getCastMovie1()
                        adapter.setMovieModule(cast)
                    } else if (movieId == "2") {
                        val cast = viewModel.getCastMovie2()
                        adapter.setMovieModule(cast)
                    } else if (movieId == "3") {
                        val cast = viewModel.getCastMovie3()
                        adapter.setMovieModule(cast)
                    } else if (movieId == "4") {
                        val cast = viewModel.getCastMovie4()
                        adapter.setMovieModule(cast)
                    } else if (movieId == "5") {
                        val cast = viewModel.getCastMovie5()
                        adapter.setMovieModule(cast)
                    } else if (movieId == "5") {
                        val cast = viewModel.getCastMovie5()
                        adapter.setMovieModule(cast)
                    } else if (movieId == "6") {
                        val cast = viewModel.getCastMovie6()
                        adapter.setMovieModule(cast)
                    } else if (movieId == "7") {
                        val cast = viewModel.getCastMovie7()
                        adapter.setMovieModule(cast)
                    } else if (movieId == "8") {
                        val cast = viewModel.getCastMovie8()
                        adapter.setMovieModule(cast)
                    } else if (movieId == "9") {
                        val cast = viewModel.getCastMovie9()
                        adapter.setMovieModule(cast)
                    } else if (movieId == "10") {
                        val cast = viewModel.getCastMovie10()
                        adapter.setMovieModule(cast)
                    }
                    viewModel.setSelectedMovie(movieId)
                    populateMovie(viewModel.getMovie())
                }
            } else if (showId.equals("TvShow")) {
                val tvShowId = extras.getString(EXTRA_TV_SHOW)
                if (tvShowId != null) {
                    if (tvShowId == "1") {
                        val cast = viewModel.getCastTvShow1()
                        adapter2.setTvShowCastList(cast)
                    } else if (tvShowId == "2") {
                        val cast = viewModel.getCastTvShow2()
                        adapter2.setTvShowCastList(cast)
                    } else if (tvShowId == "3") {
                        val cast = viewModel.getCastTvShow3()
                        adapter2.setTvShowCastList(cast)
                    } else if (tvShowId == "4") {
                        val cast = viewModel.getCastTvShow4()
                        adapter2.setTvShowCastList(cast)
                    } else if (tvShowId == "5") {
                        val cast = viewModel.getCastTvShow5()
                        adapter2.setTvShowCastList(cast)
                    } else if (tvShowId == "5") {
                        val cast = viewModel.getCastTvShow5()
                        adapter2.setTvShowCastList(cast)
                    } else if (tvShowId == "6") {
                        val cast = viewModel.getCastTvShow6()
                        adapter2.setTvShowCastList(cast)
                    } else if (tvShowId == "7") {
                        val cast = viewModel.getCastTvShow7()
                        adapter2.setTvShowCastList(cast)
                    } else if (tvShowId == "8") {
                        val cast = viewModel.getCastTvShow8()
                        adapter2.setTvShowCastList(cast)
                    } else if (tvShowId == "9") {
                        val cast = viewModel.getCastTvShow9()
                        adapter2.setTvShowCastList(cast)
                    } else if (tvShowId == "10") {
                        val cast = viewModel.getCastTvShow10()
                        adapter2.setTvShowCastList(cast)
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
        if (movieEntity.genre1.equals("Null")) {
            movieDetailBinding.cvGenre1.visibility = View.INVISIBLE
        } else {
            movieDetailBinding.movieGenre1Text.text = movieEntity.genre1
        }
        if (movieEntity.genre2.equals("Null")) {
            movieDetailBinding.cvGenre2.visibility = View.INVISIBLE
        } else {
            movieDetailBinding.movieGenre2Text.text = movieEntity.genre2
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
            onShareClick(movieEntity)
        }
    }

    private fun populateTvShow(tvShowEntity: TvShowEntity) {
        movieDetailBinding.movieTitleText.text = tvShowEntity.title
        movieDetailBinding.movieReleaseDate.text = tvShowEntity.releaseDate
        movieDetailBinding.movieDurationText.text = tvShowEntity.duration
        movieDetailBinding.movieRatingText.text = tvShowEntity.rating
        movieDetailBinding.descText.text = tvShowEntity.description
        movieDetailBinding.movieEpisodeText.text = tvShowEntity.episode + " " + tvShowEntity.season

        if (tvShowEntity.genre1.equals("Null")) {
            movieDetailBinding.cvGenre1.visibility = View.INVISIBLE
        } else {
            movieDetailBinding.movieGenre1Text.text = tvShowEntity.genre1
        }
        if (tvShowEntity.genre2.equals("Null")) {
            movieDetailBinding.cvGenre2.visibility = View.INVISIBLE
        } else {
            movieDetailBinding.movieGenre2Text.text = tvShowEntity.genre2
        }

        Glide.with(this)
            .load(tvShowEntity.imagePath)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(movieDetailBinding.imagePoster)
    }

    override fun onShareClick(movie: MovieEntity) {
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
            .setChooserTitle("Bagikan movie ini sekarang.")
            .setText(resources.getString(R.string.share) + " " + movieDetailBinding.movieTitleText.text)
            .startChooser()
    }
}