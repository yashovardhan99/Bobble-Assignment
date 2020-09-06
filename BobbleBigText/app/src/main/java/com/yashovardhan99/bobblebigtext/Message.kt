package com.yashovardhan99.bobblebigtext

import androidx.compose.ui.unit.TextUnit
import java.util.*

data class Message(
    val text: String,
    val size: TextUnit,
    val timestamp: Date = Date()
)