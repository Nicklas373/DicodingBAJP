package com.dicoding.moviecatalog.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.adapter.SectionPagerAdapter
import com.dicoding.moviecatalog.databinding.ActivitySusBinding
import com.dicoding.moviecatalog.fragment.MovieFavFragment
import com.dicoding.moviecatalog.fragment.TvShowFavFragment
import com.google.android.material.tabs.TabLayoutMediator

class SusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sus)

        val activitySusBinding = ActivitySusBinding.inflate(layoutInflater)
        setContentView(activitySusBinding.root)
        setTitle(R.string.list_favorite)

        val fragmentList = listOf(MovieFavFragment(), TvShowFavFragment())
        val tabTitle =
            listOf(resources.getString(R.string.movie), resources.getString(R.string.tv_show))

        activitySusBinding.viewpager.adapter =
            SectionPagerAdapter(fragmentList, this.supportFragmentManager, lifecycle)

        TabLayoutMediator(
            activitySusBinding.tabs,
            activitySusBinding.viewpager
        ) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()
        supportActionBar?.elevation = 0f
    }
}