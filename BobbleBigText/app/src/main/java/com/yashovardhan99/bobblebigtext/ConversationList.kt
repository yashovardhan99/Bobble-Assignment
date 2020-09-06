package com.yashovardhan99.bobblebigtext

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.ui.tooling.preview.Preview
import androidx.ui.tooling.preview.PreviewParameter
import com.yashovardhan99.bobblebigtext.ui.BobbleBigTextTheme

@Composable
fun ConversationList(list: List<Message>, modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    ScrollableColumn(
        modifier = modifier,
        scrollState = scrollState,
        reverseScrollDirection = true
    ) {
        for (message in list)
            Surface(modifier = Modifier.fillMaxSize()) {
                Bubble(message = message)
            }
    }
}

@Preview
@Composable
fun ListPreview(@PreviewParameter(BooleanPreviewProvider::class) isDark: Boolean) {
    val list = mutableListOf<Message>()
    for (i in 1..5)
        list.add(Message(text = "Hello how are you, $i", size = i * 2f))
    BobbleBigTextTheme(isDark) {
        Surface(Modifier.fillMaxSize()) {
            ConversationList(list)
        }
    }
}