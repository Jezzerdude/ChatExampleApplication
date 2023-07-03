package com.example.chatexampleapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChatViewModel: ViewModel() {
    private val state = MutableLiveData<State>()
    val viewModelState: LiveData<State> = state
    private val messageList: MutableList<ChatMessage> = arrayListOf()

    fun createMessageFromInput(msg: String) {
        if(msg.isNotEmpty()){
            val timeStamp: String = SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(Date())
            messageList.add(ChatMessage(timeStamp, msg))
            state.postValue(State.Content(messageList))
        }
    }


    sealed class State {
        data class Content(
            val listOfMessages: List<ChatMessage>
        ) : State()
    }
}