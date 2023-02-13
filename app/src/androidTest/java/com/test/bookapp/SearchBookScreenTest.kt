package com.test.bookapp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.test.bookapp.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SearchBookScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()


    @Test
    fun testSearchBookScreenVisibility() {
        composeRule.onNodeWithTag(BOOK_NAME_TAG).assertIsDisplayed()
        composeRule.onNodeWithTag(AUTHOR_TAG).assertIsDisplayed()
        composeRule.onNodeWithTag(SUBMIT_BUTTON_TAG).assertIsDisplayed()
    }


    @Test
    fun alertDialogIsDisplayedWithInvalidInput() {
        composeRule.onNodeWithText("Submit").performClick()
        composeRule.onNodeWithText("Book Name Can't be Empty").assertIsDisplayed()
    }


}

const val BOOK_NAME_TAG = "BOOK_NAME_TAG"
const val AUTHOR_TAG = "AUTHOR_TAG"
const val SUBMIT_BUTTON_TAG = "SUBMIT_BUTTON_TAG"
