package com.dicoding.moviecatalog.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
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
import org.junit.*
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MainActivityTest {
    private val dummyMovie = CatalogDatabase.generateMovieLocal()
    private val dummySelectedMovie = CatalogDatabase.generateSelectedMovieLocal()
    private val dummySelectedFavMovie = CatalogDatabase.generateSelectedFavMovie()
    private val dummyTvShow = CatalogDatabase.generateTvShowLocal()
    private val dummySelectedTvShow = CatalogDatabase.generateSelectedTvShowLocal()
    private val dummySelectedFavTvShow = CatalogDatabase.generateSelectedFavTvShow()
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
    fun load_A_Movie() {
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
    fun load_B_DetailMovie() {
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
    fun load_C_ShareMovie() {
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
        onView(withId(R.id.menu_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_fab)).check(matches(isClickable()))
        onView(withId(R.id.menu_fab)).perform(click())
        onView(withId(R.id.share_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.share_fab)).check(matches(isClickable()))
        onView(withId(R.id.share_fab)).perform(click())
    }

    @Test
    fun load_D_addFavMovies() {
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
        onView(withId(R.id.menu_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_fab)).check(matches(isClickable()))
        onView(withId(R.id.menu_fab)).perform(click())
        onView(withId(R.id.sus_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_fab)).perform(click())
        Espresso.pressBackUnconditionally()
        inlineVariable.delayTwoSecond()
        onView(withText("MOVIE")).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        inlineVariable.delayTwoSecond()
        onView(withId(R.id.menu_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_fab)).check(matches(isClickable()))
        onView(withId(R.id.menu_fab)).perform(click())
        onView(withId(R.id.sus_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_fab)).perform(click())
        Espresso.pressBackUnconditionally()
        inlineVariable.delayTwoSecond()
        onView(withId(R.id.sus_list_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_list_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_list_fab)).perform(click())
        onView(withId(R.id.sus_fav_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_fav_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_fav_fab)).perform(click())
        onView(withText("MOVIE")).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummySelectedFavMovie.size
            )
        )
    }

    @Test
    fun load_F_sortFavMoviesAsc() {
        inlineVariable.delayTwoSecond()
        onView(withText("MOVIE")).perform(click())
        onView(withId(R.id.sus_list_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_list_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_list_fab)).perform(click())
        onView(withId(R.id.sus_fav_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_fav_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_fav_fab)).perform(click())
        inlineVariable.delayTwoSecond()
        onView(withText("MOVIE")).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_list_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_list_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_list_fab)).perform(click())
        onView(withId(R.id.sus_order_asc)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_order_asc)).check(matches(isClickable()))
        onView(withId(R.id.sus_order_asc)).perform(click())
        inlineVariable.delayTwoSecond()
        Espresso.pressBackUnconditionally()
    }

    @Test
    fun load_E_sortFavMoviesDesc() {
        inlineVariable.delayTwoSecond()
        onView(withText("MOVIE")).perform(click())
        onView(withId(R.id.sus_list_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_list_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_list_fab)).perform(click())
        onView(withId(R.id.sus_fav_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_fav_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_fav_fab)).perform(click())
        inlineVariable.delayTwoSecond()
        onView(withText("MOVIE")).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_list_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_list_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_list_fab)).perform(click())
        onView(withId(R.id.sus_order_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_order_desc)).check(matches(isClickable()))
        onView(withId(R.id.sus_order_desc)).perform(click())
        inlineVariable.delayTwoSecond()
        Espresso.pressBackUnconditionally()
    }

    @Test
    fun load_G_removeFavMovies() {
        inlineVariable.delayTwoSecond()
        onView(withText("MOVIE")).perform(click())
        onView(withId(R.id.sus_list_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_list_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_list_fab)).perform(click())
        onView(withId(R.id.sus_fav_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_fav_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_fav_fab)).perform(click())
        inlineVariable.delayTwoSecond()
        onView(withText("MOVIE")).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
        onView(withId(R.id.menu_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_fab)).check(matches(isClickable()))
        onView(withId(R.id.menu_fab)).perform(click())
        onView(withId(R.id.sus_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_fab)).perform(click())
        Espresso.pressBackUnconditionally()
        inlineVariable.delayTwoSecond()
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
        onView(withId(R.id.menu_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_fab)).check(matches(isClickable()))
        onView(withId(R.id.menu_fab)).perform(click())
        onView(withId(R.id.sus_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_fab)).perform(click())
        inlineVariable.delayTwoSecond()
        Espresso.pressBackUnconditionally()
    }

    @Test
    fun load_H_TvShow() {
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
    fun load_I_DetailTvShow() {
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
    fun load_J_ShareTvShow() {
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
        onView(withId(R.id.menu_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_fab)).check(matches(isClickable()))
        onView(withId(R.id.menu_fab)).perform(click())
        onView(withId(R.id.share_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.share_fab)).check(matches(isClickable()))
        onView(withId(R.id.share_fab)).perform(click())
    }

    @Test
    fun load_K_addFavTvShow() {
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
        onView(withId(R.id.menu_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_fab)).check(matches(isClickable()))
        onView(withId(R.id.menu_fab)).perform(click())
        onView(withId(R.id.sus_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_fab)).perform(click())
        Espresso.pressBackUnconditionally()
        inlineVariable.delayTwoSecond()
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        inlineVariable.delayTwoSecond()
        onView(withId(R.id.menu_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_fab)).check(matches(isClickable()))
        onView(withId(R.id.menu_fab)).perform(click())
        onView(withId(R.id.sus_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_fab)).perform(click())
        Espresso.pressBackUnconditionally()
        inlineVariable.delayTwoSecond()
        onView(withId(R.id.sus_list_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_list_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_list_fab)).perform(click())
        onView(withId(R.id.sus_fav_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_fav_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_fav_fab)).perform(click())
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummySelectedFavTvShow.size
            )
        )
    }

    @Test
    fun load_M_sortFavTvShowAsc() {
        inlineVariable.delayTwoSecond()
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.sus_list_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_list_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_list_fab)).perform(click())
        onView(withId(R.id.sus_fav_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_fav_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_fav_fab)).perform(click())
        inlineVariable.delayTwoSecond()
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_list_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_list_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_list_fab)).perform(click())
        onView(withId(R.id.sus_order_asc)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_order_asc)).check(matches(isClickable()))
        onView(withId(R.id.sus_order_asc)).perform(click())
        inlineVariable.delayTwoSecond()
        Espresso.pressBackUnconditionally()
    }

    @Test
    fun load_L_sortFavTvShowDesc() {
        inlineVariable.delayTwoSecond()
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.sus_list_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_list_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_list_fab)).perform(click())
        onView(withId(R.id.sus_fav_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_fav_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_fav_fab)).perform(click())
        inlineVariable.delayTwoSecond()
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_list_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_list_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_list_fab)).perform(click())
        onView(withId(R.id.sus_order_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_order_desc)).check(matches(isClickable()))
        onView(withId(R.id.sus_order_desc)).perform(click())
        inlineVariable.delayTwoSecond()
        Espresso.pressBackUnconditionally()
    }

    @Test
    fun load_N_removeFavTvShow() {
        inlineVariable.delayTwoSecond()
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.sus_list_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_list_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_list_fab)).perform(click())
        onView(withId(R.id.sus_fav_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_fav_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_fav_fab)).perform(click())
        inlineVariable.delayTwoSecond()
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
        onView(withId(R.id.menu_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_fab)).check(matches(isClickable()))
        onView(withId(R.id.menu_fab)).perform(click())
        onView(withId(R.id.sus_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_fab)).perform(click())
        Espresso.pressBackUnconditionally()
        inlineVariable.delayTwoSecond()
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
        onView(withId(R.id.menu_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_fab)).check(matches(isClickable()))
        onView(withId(R.id.menu_fab)).perform(click())
        onView(withId(R.id.sus_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_fab)).perform(click())
        Espresso.pressBackUnconditionally()
        inlineVariable.delayTwoSecond()
        onView(withText("TV SHOW")).perform(click())
        Espresso.pressBackUnconditionally()
    }

    @Test
    fun load_P_sortMovieAsc() {
        inlineVariable.delayTwoSecond()
        onView(withText("MOVIE")).perform(click())
        onView(withId(R.id.sus_list_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_list_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_list_fab)).perform(click())
        onView(withId(R.id.sus_order_asc)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_order_asc)).check(matches(isClickable()))
        onView(withId(R.id.sus_order_asc)).perform(click())
    }

    @Test
    fun load_O_sortMovieDesc() {
        inlineVariable.delayTwoSecond()
        onView(withText("MOVIE")).perform(click())
        onView(withId(R.id.sus_list_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_list_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_list_fab)).perform(click())
        onView(withId(R.id.sus_order_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_order_desc)).check(matches(isClickable()))
        onView(withId(R.id.sus_order_desc)).perform(click())
    }

    @Test
    fun load_R_sortTvShowAsc() {
        inlineVariable.delayTwoSecond()
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.sus_list_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_list_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_list_fab)).perform(click())
        onView(withId(R.id.sus_order_asc)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_order_asc)).check(matches(isClickable()))
        onView(withId(R.id.sus_order_asc)).perform(click())
    }

    @Test
    fun load_Q_sortTvShowDesc() {
        inlineVariable.delayTwoSecond()
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.sus_list_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_list_fab)).check(matches(isClickable()))
        onView(withId(R.id.sus_list_fab)).perform(click())
        onView(withId(R.id.sus_order_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.sus_order_desc)).check(matches(isClickable()))
        onView(withId(R.id.sus_order_desc)).perform(click())
    }
}