package com.dicoding.moviecatalog.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.utils.CatalogDatabase
import org.junit.Rule
import org.junit.Test


class MainActivityTest {
    private val dummyMovie = CatalogDatabase.generateMovieDatabase()
    private val dummyCastMovie1 = CatalogDatabase.generateCastListMovie1()
    private val dummyCastTvShow1 = CatalogDatabase.generateCastListTvShow1()
    private val dummyTvShow = CatalogDatabase.generateTvShowDatabase()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadMovie() {
        onView(withText("MOVIE")).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovie.size
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withText("MOVIE")).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.movie_title_text)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_title_text)).check(matches(withText(dummyMovie[0].title)))
        onView(withId(R.id.movie_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_release_date)).check(matches(withText(dummyMovie[0].releaseDate)))
        onView(withId(R.id.rv_cast)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_cast)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyCastMovie1.size
            )
        )
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.movie_title_text)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_title_text)).check(matches(withText(dummyTvShow[0].title)))
        onView(withId(R.id.movie_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_release_date)).check(matches(withText(dummyTvShow[0].releaseDate)))
        onView(withId(R.id.rv_cast)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_cast)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyCastTvShow1.size
            )
        )
    }

    @Test
    fun loadTvShow() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShow.size
            )
        )
    }

    @Test
    fun loadShareMovie() {
        onView(withText("MOVIE")).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.img_share)).check(matches(isDisplayed()))
        onView(withId(R.id.img_share)).check(matches(isClickable()))
        onView(withId(R.id.img_share)).perform(click())
    }

    @Test
    fun loadShareTvShow() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.img_share)).check(matches(isDisplayed()))
        onView(withId(R.id.img_share)).check(matches(isClickable()))
        onView(withId(R.id.img_share)).perform(click())
    }
}