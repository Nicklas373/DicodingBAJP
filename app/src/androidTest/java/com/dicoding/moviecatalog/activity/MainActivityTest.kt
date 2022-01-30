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
    private val dummySelectedMovie = CatalogDatabase.generateSelectedMovieLocal()
    private val dummyTvShow = CatalogDatabase.generateTvShowLocal()
    private val dummySelectedTvShow = CatalogDatabase.generateSelectedTvShowLocal()
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
        onView(withId(R.id.movie_title_text)).check(matches(withText(dummySelectedMovie.title)))
        onView(withId(R.id.movie_release_date)).check(matches(isDisplayed()))
        val releaseDate = inlineVariable.setReleaseDate(dummySelectedMovie.releaseDate)
        onView(withId(R.id.movie_release_date)).check(matches(withText(releaseDate)))
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.image_poster)).check(matches(withContentDescription(dummySelectedMovie.posterPath)))
        onView(withId(R.id.movie_rating_text)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_rating_text)).check(matches(withText(dummySelectedMovie.voteAverage.toString())))
        onView(withId(R.id.movie_revenue_text)).check(matches(isDisplayed()))
        val revenue = inlineVariable.setRevenue(dummySelectedMovie.revenue.toString())
        onView(withId(R.id.movie_revenue_text)).check(matches(withText(revenue)))
        onView(withId(R.id.desc_text)).check(matches(isDisplayed()))
        onView(withId(R.id.desc_text)).check(matches(withText(dummySelectedMovie.overview)))
        inlineVariable.delayTwoSecond()
        onView(withId(R.id.cv_genre_1)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_genre_text_1)).check(matches(withText(dummySelectedMovie.genres_1)))
        onView(withId(R.id.cv_genre_2)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_genre_text_2)).check(matches(withText(dummySelectedMovie.genres_2)))
        onView(withId(R.id.cv_item_companies_1)).check(matches(isDisplayed()))
        onView(withId(R.id.name_companies_txt_1)).check(matches(withText(dummySelectedMovie.compName_1)))
        onView(withId(R.id.origin_txt_1)).check(matches(withText(dummySelectedMovie.compOrigin_1)))
        onView(withId(R.id.img_companies_1)).check(matches(withContentDescription(dummySelectedMovie.compLogo_1)))
        onView(withId(R.id.cv_item_companies_2)).check(matches(isDisplayed()))
        onView(withId(R.id.name_companies_txt_2)).check(matches(withText(dummySelectedMovie.compName_2)))
        onView(withId(R.id.origin_txt_2)).check(matches(withText(dummySelectedMovie.compOrigin_2)))
        onView(withId(R.id.img_companies_2)).check(matches(withContentDescription(dummySelectedMovie.compLogo_2)))
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
        onView(withId(R.id.movie_title_text)).check(matches(withText(dummySelectedTvShow.tvShowName)))
        onView(withId(R.id.movie_release_date)).check(matches(isDisplayed()))
        val releaseDate = dummySelectedTvShow.tvShowFirstAirDate
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
        onView(withId(R.id.image_poster)).check(matches(withContentDescription(dummySelectedTvShow.tvShowPoster)))
        onView(withId(R.id.movie_rating_text)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_rating_text)).check(matches(withText(dummySelectedTvShow.tvShowVote.toString())))
        onView(withId(R.id.movie_episode_text)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_episode_text)).check(matches(withText(dummySelectedTvShow.tvShowEpisodes.toString() + " Episode | " + dummySelectedTvShow.tvShowSeasons.toString() + " Season")))
        onView(withId(R.id.tvShow_language_text)).check(matches(isDisplayed()))
        onView(withId(R.id.tvShow_language_text)).check(matches(withText(dummySelectedTvShow.tvShowLanguage)))
        onView(withId(R.id.desc_text)).check(matches(isDisplayed()))
        onView(withId(R.id.desc_text)).check(matches(withText(dummySelectedTvShow.tvShowOverview)))
        inlineVariable.delayTwoSecond()
        onView(withId(R.id.cv_genre_1)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_genre_text_1)).check(matches(withText(dummySelectedTvShow.tvShowGenres_1)))
        onView(withId(R.id.cv_genre_2)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_genre_text_2)).check(matches(withText(dummySelectedTvShow.tvShowGenres_2)))
        onView(withId(R.id.cv_item_companies_1)).check(matches(isDisplayed()))
        onView(withId(R.id.name_companies_txt_1)).check(matches(withText(dummySelectedTvShow.compName_1)))
        onView(withId(R.id.origin_txt_1)).check(matches(withText(dummySelectedTvShow.compOrigin_1)))
        onView(withId(R.id.img_companies_1)).check(
            matches(
                withContentDescription(
                    dummySelectedTvShow.compLogo_1
                )
            )
        )
        onView(withId(R.id.cv_item_companies_2)).check(matches(isDisplayed()))
        onView(withId(R.id.name_companies_txt_2)).check(matches(withText(dummySelectedTvShow.compName_2)))
        onView(withId(R.id.origin_txt_2)).check(matches(withText(dummySelectedTvShow.compOrigin_2)))
        onView(withId(R.id.img_companies_2)).check(
            matches(
                withContentDescription(
                    dummySelectedTvShow.compLogo_2
                )
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