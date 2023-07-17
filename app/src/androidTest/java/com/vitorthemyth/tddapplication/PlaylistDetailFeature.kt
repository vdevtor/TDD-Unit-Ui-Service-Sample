package com.vitorthemyth.tddapplication

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.vitorthemyth.tddapplication.presentation.MainActivity
import com.vitorthemyth.tddapplication.utils.BaseUiTest
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test

class PlaylistDetailFeature : BaseUiTest() {

    val mActivityTestRule = ActivityTestRule(MainActivity::class.java)
        @Rule get

    @Test
    fun checkIfDetailsAreBeingDisplayedOnScreen(){
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.playlist_image),
                ViewMatchers.isDescendantOfA(nthChildOf(ViewMatchers.withId(R.id.rv_playlist), 0))
            )
        )
            .perform(ViewActions.click())


       assertDisplayed("Hard Rock Cafe")
       assertDisplayed(
           "Rock your senses with this timeless signature vibe list. \n\n • Poison \n • You shook me all night \n • Zombie \n • Rock'n Me \n • Thunderstruck \n • I Hate Myself for Loving you \n • Crazy \n • Knockin' on Heavens Door"
       )

    }
}