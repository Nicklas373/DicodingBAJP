package com.dicoding.moviecatalog.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.adapter.DetailMovieAdapter
import com.dicoding.moviecatalog.data.MovieEntity
import com.dicoding.moviecatalog.databinding.ActivityDetailMovieBinding
import com.dicoding.moviecatalog.databinding.ContentDetailMovieBinding
import com.dicoding.moviecatalog.utils.MovieDatabase

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

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(EXTRA_MOVIE)
            if (movieId != null) {
                val movieModule = MovieDatabase.generateMovieDetails(movieId)
                adapter.setMovieModule(movieModule)
                for (movie in MovieDatabase.generateMovieDatabase()) {
                    if (movie.movieId == movieId) {
                        populateMovie(movie)
                    }
                }
            }
        }

        with(movieDetailBinding.rvModule) {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@DetailMovieActivity)
            setHasFixedSize(true)
            this.adapter = adapter
            val dividerItemDecoration =
                DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun populateMovie(movieEntity: MovieEntity) {
        movieDetailBinding.textTitle.text = movieEntity.title
        movieDetailBinding.textDescription.text = movieEntity.description
        movieDetailBinding.textDate.text =
            resources.getString(R.string.deadline_date, movieEntity.duration)

        Glide.with(this)
            .load(movieEntity.imagePath)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(movieDetailBinding.imagePoster)

        movieDetailBinding.btnStart.setOnClickListener {
            val intent = Intent(this@DetailMovieActivity, MovieReaderActivity::class.java)
            intent.putExtra(MovieReaderActivity.EXTRA_MOVIE_ID, movieEntity.movieId)
            startActivity(intent)
        }
    }
}