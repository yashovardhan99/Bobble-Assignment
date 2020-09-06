package com.yashovardhan99.bobblebigtext

import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.PreviewParameterProvider

class BooleanPreviewProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean>
        get() = sequenceOf(false, true)
}

fun getFakeMessages(): List<Message> {
    val list = mutableListOf<Message>()
    for (i in 1..20)
        list.add(Message(text = "Hello how are you, $i", size = (i * 2f)))
    return list
}