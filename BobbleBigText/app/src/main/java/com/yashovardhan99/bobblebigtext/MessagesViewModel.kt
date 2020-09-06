package com.yashovardhan99.bobblebigtext

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber

class MessagesViewModel(application: Application) : AndroidViewModel(application) {
    private val messagesDao = MessagesDatabase.getDatabase(application.applicationContext)
        .messageDao()
    private val _messageList = mutableListOf<Message>()
    val messages: Flow<List<Message>> = messagesDao.loadMessages()
        .map { list -> list.sortedBy { it.timestamp } }

    fun insertMessage(message: Message) {
        viewModelScope.launch {
            messagesDao.insertMessage(message)
        }
        Timber.d("Inserted message = $message")
    }
}