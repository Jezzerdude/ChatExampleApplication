package com.example.chatexampleapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChatViewModel: ViewModel() {
    private val state = MutableLiveData<State>()
    val viewModelState: LiveData<State> = state
    private val messageList: MutableList<ChatMessage> = arrayListOf()

    fun createMessageFromInput(fileDir: String, msg: String) {
        if(msg.isNotEmpty()){
            val timeStamp: String = SimpleDateFormat("HH:mm ", Locale.ENGLISH).format(Date())
            messageList.add(ChatMessage(timeStamp, msg))
            saveToFile(fileDir, timeStamp, msg)
            state.postValue(State.Content(messageList))
        }
    }

    private fun saveToFile(fileDir:String, timestamp: String, message: String){
        try {
            val fo = FileWriter(fileDir)
            fo.write(timestamp + message)
            fo.close()
        } catch (e: Exception) {
            println(e)
        }
    }

    sealed class State {
        data class Content(
            val listOfMessages: List<ChatMessage>
        ) : State()
    }
}