package com.ykbintang.moviz.ui.screen.home

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.ykbintang.moviz.MainActivity
import com.ykbintang.moviz.MovizApp
import com.ykbintang.moviz.ui.helper.EspressoIdlingResource
import com.ykbintang.moviz.ui.theme.MovizTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
@HiltAndroidTest
class HomeScreenTest  {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        composeTestRule.activity.setContent {
            MovizTheme {
                MovizApp()
            }
        }
    }

    @After
    fun teardown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun search_movie_get_result(){
        composeTestRule.onNodeWithTag("textfield_search").performTextInput("Harry Potter")
        composeTestRule.onNodeWithTag("icon_search").performClick()
        composeTestRule.onNodeWithTag("icon_search").performClick()
        composeTestRule.onNodeWithTag("lvg_movie").assertExists()
    }

    @Test
    fun search_movie_get_empty_result(){
        composeTestRule.onNodeWithTag("textfield_search").performTextInput("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
        composeTestRule.onNodeWithTag("icon_search").performClick()
        composeTestRule.onNodeWithTag("icon_search").performClick()
        composeTestRule.onNodeWithTag("error_message").assertExists()
    }

    @Test
    fun add_to_favorite(){
        composeTestRule.onNodeWithTag("textfield_search").performTextInput("Harry Potter")
        composeTestRule.onNodeWithTag("icon_search").performClick()
        composeTestRule.onNodeWithTag("lvg_movie").onChildren().onFirst().performClick()
        composeTestRule.onNodeWithTag("favorite_button").performClick()
        composeTestRule.onNodeWithTag("back_button").performClick()
        composeTestRule.onNodeWithText("Favorite").performClick()
        composeTestRule.onNodeWithTag("error_message").assertDoesNotExist()
    }
}
