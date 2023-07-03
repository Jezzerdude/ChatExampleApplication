package com.example.chatexampleapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatexampleapplication.databinding.ChatexamplelayoutBinding

class ChatView: AppCompatActivity() {
    private lateinit var binding: ChatexamplelayoutBinding
    private val viewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ChatexamplelayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpClickListener()
        with(viewModel) {
            viewModelState.observe(this@ChatView, ::onViewStateChanged)
        }
    }

    private fun setUpClickListener() {
        binding.submitButton.setOnClickListener {
            viewModel.createMessageFromInput(binding.enterText.text.toString())
        }
    }

    private fun onViewStateChanged(state: ChatViewModel.State?) {
        when (state) {
            is ChatViewModel.State.Content -> onContentChanged(state)
            else -> {}
        }
    }

    private fun onContentChanged(content: ChatViewModel.State.Content) {
        val reversedList = content.listOfMessages.asReversed()
        binding.messageList.adapter = MessageAdaptor(reversedList)
        binding.messageList.layoutManager = LinearLayoutManager(this)
        binding.enterText.text.clear()
    }
}

data class ChatMessage(
    val timeStamp: String,
    val message: String
)