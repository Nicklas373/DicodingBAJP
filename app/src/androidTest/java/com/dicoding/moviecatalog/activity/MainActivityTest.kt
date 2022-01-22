package com.dicoding.moviecatalog.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.utils.CatalogDatabase
import com.dicoding.moviecatalog.utils.EspressoIdlingResource
import com.dicoding.moviecatalog.utils.InlineVariable
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val dummyMovie = CatalogDatabase.generateMovieLocal()
    private val dummyGenreMovie = CatalogDatabase.generateGenreMovies()
    private val dummyProductionMovie = CatalogDatabase.generateCompaniesMovies()
    private val dummyProductionTvShow = CatalogDatabase.generateCompaniesTvShow()
    private val dummyGenreTvShow = CatalogDatabase.generateGenreTvShow()
    private val dummyTvShow = CatalogDatabase.generateTvShowLocal()
    private val inlineVariable = InlineVariable()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovie() {
        inlineVariable.delayTwoSecond()
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
        inlineVariable.delayTwoSecond()
        onView(withText("MOVIE")).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        inlineVariable.delayTwoSecond()
        onView(withId(R.id.movie_title_text)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_title_text)).check(matches(withText(dummyMovie[0].title)))
        onView(withId(R.id.movie_release_date)).check(matches(isDisplayed()))
        val releaseDate = inlineVariable.setReleaseDate(dummyMovie[0].releaseDate)
        onView(withId(R.id.movie_release_date)).check(matches(withText(releaseDate)))
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.image_poster)).check(matches(withContentDescription(dummyMovie[0].posterPath)))
        onView(withId(R.id.movie_rating_text)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_rating_text)).check(matches(withText(dummyMovie[0].voteAverage.toString())))
        onView(withId(R.id.movie_revenue_text)).check(matches(isDisplayed()))
        val revenue = inlineVariable.setRevenue(dummyMovie[0].revenue.toString())
        onView(withId(R.id.movie_revenue_text)).check(matches(withText(revenue)))
        onView(withId(R.id.desc_text)).check(matches(isDisplayed()))
        onView(withId(R.id.desc_text)).check(matches(withText(dummyMovie[0].overview)))
        inlineVariable.delayTwoSecond()
        onView(withId(R.id.rv_genre_api)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_genre_api)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyGenreMovie.size
            )
        )
        inlineVariable.delayTwoSecond()
        onView(withId(R.id.rv_companies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_companies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyProductionMovie.size
            )
        )
    }

    @Test
    fun loadDetailTvShow() {
        inlineVariable.delayTwoSecond()
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        inlineVariable.delayTwoSecond()
        onView(withId(R.id.movie_title_text)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_title_text)).check(matches(withText(dummyTvShow[0].tvShowName)))
        onView(withId(R.id.movie_release_date)).check(matches(isDisplayed()))
        val releaseDate = dummyTvShow[0].tvShowFirstAirDate
        onView(withId(R.id.movie_release_date)).check(
            matches(
                withText(
                    inlineVariable.setReleaseDate(
                        releaseDate
                    )
                )
            )
        )
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.image_poster)).check(matches(withContentDescription(dummyTvShow[0].tvShowPoster)))
        onView(withId(R.id.movie_rating_text)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_rating_text)).check(matches(withText(dummyTvShow[0].tvShowVote.toString())))
        onView(withId(R.id.movie_episode_text)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_episode_text)).check(matches(withText(dummyTvShow[0].tvShowEpisodes.toString() + " Episode | " + dummyTvShow[0].tvShowSeasons.toString() + " Season")))
        onView(withId(R.id.tvShow_language_text)).check(matches(isDisplayed()))
        onView(withId(R.id.tvShow_language_text)).check(matches(withText(dummyTvShow[0].tvShowLanguage)))
        onView(withId(R.id.desc_text)).check(matches(isDisplayed()))
        onView(withId(R.id.desc_text)).check(matches(withText(dummyTvShow[0].tvShowOverview)))
        inlineVariable.delayTwoSecond()
        onView(withId(R.id.rv_genre_api)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_genre_api)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyGenreTvShow.size
            )
        )
        inlineVariable.delayTwoSecond()
        onView(withId(R.id.rv_companies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_companies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyProductionTvShow.size
            )
        )
    }

    @Test
    fun loadTvShow() {
        inlineVariable.delayTwoSecond()
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
        inlineVariable.delayTwoSecond()
        onView(withText("MOVIE")).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        inlineVariable.delayTwoSecond()
        onView(withId(R.id.img_share)).check(matches(isDisplayed()))
        onView(withId(R.id.img_share)).check(matches(isClickable()))
        onView(withId(R.id.img_share)).perform(click())
    }

    @Test
    fun loadShareTvShow() {
        inlineVariable.delayTwoSecond()
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        inlineVariable.delayTwoSecond()
        onView(withId(R.id.img_share)).check(matches(isDisplayed()))
        onView(withId(R.id.img_share)).check(matches(isClickable()))
        onView(withId(R.id.img_share)).perform(click())
    }
}