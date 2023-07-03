package com.example.chatexampleapplication

import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@RunWith(AndroidJUnit4::class)
class ChatViewTest {
    private lateinit var sc: ActivityScenario<ChatView>

    @Rule
    @JvmField
    public var activityRule = ActivityTestRule(ChatView::class.java)

    @Before
    fun setUp(){
        sc = launchActivity()
        sc.moveToState(Lifecycle.State.RESUMED)
    }

    fun getTimeStamp()= SimpleDateFormat("HH:mm ", Locale.ENGLISH).format(Date())

    @Test
    fun testFlow(){
        // Given
        val testMessage = "Test Message"
        val testMessage2 = "Test 2nd Message"
        val testMessage3 = "Test 3rd Message"

        // When
        onView(withId(R.id.enterText)).perform(typeText(testMessage))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.submitButton)).perform(click())

        // Then
        onView(withId(R.id.messageList)).check(matches(isDisplayed()))
        val recyclerView = activityRule.activity.findViewById<RecyclerView>(R.id.messageList)
        var itemcount = recyclerView.adapter?.itemCount
        assertEquals(1, itemcount)

        // When
        onView(withId(R.id.enterText)).perform(typeText(testMessage2))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.submitButton)).perform(click())

        // Then
        itemcount = recyclerView.adapter?.itemCount
        assertEquals(2, itemcount)

        // When
        onView(withId(R.id.enterText)).perform(typeText(testMessage3))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.submitButton)).perform(click())

        // Then
        itemcount = recyclerView.adapter?.itemCount
        assertEquals(3, itemcount)
    }

}