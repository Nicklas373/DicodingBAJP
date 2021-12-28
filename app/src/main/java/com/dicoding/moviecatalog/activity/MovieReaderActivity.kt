package com.dicoding.moviecatalog.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.callback.MovieReaderCallback
import com.dicoding.moviecatalog.fragment.MovieContentFragment
import com.dicoding.moviecatalog.fragment.MovieListFragment

class MovieReaderActivity : AppCompatActivity(), MovieReaderCallback {

    companion object {
        const val EXTRA_MOVIE_ID = "extra_movie_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reader)

        val bundle = intent.extras
        if (bundle != null) {
            val courseId = bundle.getString(EXTRA_MOVIE_ID)
            if (courseId != null) {
                populateFragment()
            }
        }
    }

    override fun moveTo(position: Int, moduleId: String) {
        val fragment = MovieContentFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.frame_container, fragment, MovieContentFragment.TAG)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    private fun populateFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        var fragment = supportFragmentManager.findFragmentByTag(MovieListFragment.TAG)
        if (fragment == null) {
            fragment = MovieListFragment.newInstance()
            fragmentTransaction.add(R.id.frame_container, fragment, MovieListFragment.TAG)
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commit()
    }
}