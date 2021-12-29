package com.dicoding.moviecatalog.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.adapter.DetailMovieAdapter
import com.dicoding.moviecatalog.data.movie.MovieEntity
import com.dicoding.moviecatalog.databinding.ActivityDetailMovieBinding
import com.dicoding.moviecatalog.databinding.ContentDetailMovieBinding
import com.dicoding.moviecatalog.viewmodel.DetailMovieViewModel

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var movieDetailBinding: ContentDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val activityMovieDetailBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        movieDetailBinding = activityMovieDetailBinding.detailContent

        setContentView(activityMovieDetailBinding.root)

        setSupportActionBar(activityMovieDetailBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = DetailMovieAdapter()
        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailMovieViewModel::class.java]
        val extras = intent.extras
        if (extras != null) {
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
        }

        movieDetailBinding.rvCast.isNestedScrollingEnabled = false
        movieDetailBinding.rvCast.layoutManager = LinearLayoutManager(this)
        movieDetailBinding.rvCast.setHasFixedSize(true)
        movieDetailBinding.rvCast.adapter = adapter
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

        Glide.with(this)
            .load(movieEntity.imagePath)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(movieDetailBinding.imagePoster)
    }
}