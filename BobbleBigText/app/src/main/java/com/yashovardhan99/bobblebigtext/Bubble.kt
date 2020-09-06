package com.yashovardhan99.bobblebigtext

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import androidx.ui.tooling.preview.PreviewParameter
import com.yashovardhan99.bobblebigtext.ui.BobbleBigTextTheme

@Composable
fun Bubble(message: Message) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.primarySurface,
        modifier = Modifier
            .wrapContentWidth(Alignment.End)
            .wrapContentHeight(Alignment.CenterVertically)
            .padding(8.dp)
    ) {
        Text(
            text = message.text, fontSize = message.size.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(8.dp)
                .preferredWidthIn(10.dp, 300.dp)
        )
    }
}

@Preview(showDecoration = true)
@Composable
fun BubblePreview(@PreviewParameter(BooleanPreviewProvider::class) isDark: Boolean) {
    val message = Message(text = "Hello, How are you", size = 10f)
    BobbleBigTextTheme(isDark) {
        Surface(Modifier.fillMaxSize()) {
            Bubble(message = message)
        }
    }
}