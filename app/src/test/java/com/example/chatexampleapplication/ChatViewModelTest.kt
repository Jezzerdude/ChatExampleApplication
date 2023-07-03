package com.example.chatexampleapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class ChatViewModelTest {
    lateinit var classUnderTest: ChatViewModel

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        classUnderTest = ChatViewModel()
    }
    fun getTimeStamp()= SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(Date())

    @Test
    fun createTestMessage(){
        // Given
        val testMessage = "Test Message"
        val timeStamp: String = getTimeStamp()

        // When
        classUnderTest.createMessageFromInput(testMessage)

        // Then
        val expected = ChatViewModel.State.Content(listOf(ChatMessage(timeStamp, testMessage)))
        assertEquals(expected, classUnderTest.viewModelState.value)
    }

    @Test
    fun createAdditionalMessages(){
        // Given
        val testMessage1 = "Test Message"
        val testMessage2 = "Test 2nd Message"
        val timeStamp: String = getTimeStamp()

        // When
        classUnderTest.createMessageFromInput(testMessage1)
        classUnderTest.createMessageFromInput(testMessage2)

        // Then
        val expected = ChatViewModel.State.Content(listOf(ChatMessage(timeStamp, testMessage1),
            ChatMessage(timeStamp, testMessage2)
        ))
        assertEquals(expected, classUnderTest.viewModelState.value)
    }

    @Test
    fun noMessageCreatedWhenMessageIsBlank(){
        // Given
        val testMessage = "Test Message"
        val blankMessage = ""
        val timeStamp: String = getTimeStamp()

        // When
        classUnderTest.createMessageFromInput(testMessage)
        classUnderTest.createMessageFromInput(blankMessage)

        // Then
        val expected = ChatViewModel.State.Content(listOf(ChatMessage(timeStamp, testMessage)))
        assertEquals(expected, classUnderTest.viewModelState.value)
    }

    @Test
    fun noMessageCreatedWhenMessageIsBlank2(){
        // Given
        val blankMessage = ""
        val timeStamp: String = getTimeStamp()

        // When
        classUnderTest.createMessageFromInput(blankMessage)

        // Then
        val expected = null
        assertEquals(expected, classUnderTest.viewModelState.value)
    }
}