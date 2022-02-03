package com.dicoding.moviecatalog.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.adapter.SectionPagerAdapter
import com.dicoding.moviecatalog.databinding.ActivityMainBinding
import com.dicoding.moviecatalog.fragment.MovieFragment
import com.dicoding.moviecatalog.fragment.TvShowFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val activityHomeBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)
        val fragmentList = listOf(MovieFragment(), TvShowFragment())
        val tabTitle =
            listOf(resources.getString(R.string.movie), resources.getString(R.string.tv_show))

        activityHomeBinding.viewpager.adapter =
            SectionPagerAdapter(fragmentList, this.supportFragmentManager, lifecycle)

        TabLayoutMediator(
            activityHomeBinding.tabs,
            activityHomeBinding.viewpager
        ) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()
        supportActionBar?.elevation = 0f
    }
}