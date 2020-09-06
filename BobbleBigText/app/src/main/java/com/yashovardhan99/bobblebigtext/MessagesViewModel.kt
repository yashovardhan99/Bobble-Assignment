package com.yashovardhan99.bobblebigtext

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import timber.log.Timber

class MessagesViewModel : ViewModel() {
    private val _messageList = mutableListOf<Message>()
    val messages: Flow<List<Message>> = flowOf(_messageList)
    fun insertMessage(message: Message) {
        _messageList.add(message)
        Timber.d("Inserted message = $message")
    }
}