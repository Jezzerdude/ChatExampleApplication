package com.example.chatexampleapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MessageAdaptor(private val messageList: List<ChatMessage>):
    RecyclerView.Adapter<MessageAdaptor.messageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): messageViewHolder {
        val messageViewHolder = LayoutInflater.from(parent.context).inflate(
            R.layout.messageviewholder,
            parent, false
        )
        return messageViewHolder(messageViewHolder)
    }

    override fun getItemCount() = messageList.size

    override fun onBindViewHolder(holder: messageViewHolder, position: Int) {
        val currentItem = messageList[position]
        holder.timestamp.text = currentItem.timeStamp
        holder.message.text = currentItem.message
    }

    class messageViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val timestamp: TextView = item.findViewById(R.id.timestamp)
        val message: TextView = item.findViewById(R.id.message)
    }
}