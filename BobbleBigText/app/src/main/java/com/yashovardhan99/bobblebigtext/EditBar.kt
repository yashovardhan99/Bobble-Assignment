package com.yashovardhan99.bobblebigtext

import androidx.compose.foundation.BaseTextField
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ColumnScope.gravity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.gesture.LongPressDragObserver
import androidx.compose.ui.gesture.longPressDragGestureFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.coerceAtMost
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.yashovardhan99.bobblebigtext.ui.BobbleBigTextTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SendButton(
    onClick: () -> Unit,
    onLongStart: () -> Unit,
    onLongEnd: (Offset) -> Unit,
    modifier: Modifier = Modifier
) {
    Icon(
        asset = Icons.Default.Send,
        modifier = modifier
            .clickable(
                onClick = onClick
            )
            .longPressDragGestureFilter(object : LongPressDragObserver {
                override fun onLongPress(pxPosition: Offset) {
                    super.onLongPress(pxPosition)
                    onLongStart()
                }

                override fun onCancel() {
                    super.onCancel()
                    onLongEnd(Offset(0f, 0f))
                }

                override fun onStop(velocity: Offset) {
                    super.onStop(velocity)
                    onLongEnd(velocity)
                }
            })
            .padding(16.dp)
    )
}

@ExperimentalFoundationApi
@Composable
fun BigTextField(size: TextUnit, modifier: Modifier = Modifier) {
    var value by remember { mutableStateOf(TextFieldValue("")) }
    BaseTextField(
        value = value, onValueChange = { updated ->
            value = updated
        }, textStyle = TextStyle(fontSize = size), modifier = modifier
    )
}

@ExperimentalFoundationApi
@Composable
fun SendBar(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf(false) }
    var size by remember { mutableStateOf(12.sp) }
    val scope = rememberCoroutineScope()
    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val edit = createRef()
        val button = createRef()
        BigTextField(size = size, modifier = Modifier.constrainAs(edit) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            end.linkTo(button.start)
            width = Dimension.fillToConstraints
            height = Dimension.preferredWrapContent.atLeast(24.dp)
        }.gravity(Alignment.Start))
        SendButton(onClick = {
            size = 12.sp
        }, onLongStart = {
            text = true
            scope.launch {
                while (text) {
                    size = (size + 0.2.sp).coerceAtMost(100.sp)
                    delay(20)
                }
            }
        }, onLongEnd = {
            text = false
        }, modifier = Modifier.constrainAs(button) {
            end.linkTo(parent.end)
        })
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun SendBarPreview() {
    BobbleBigTextTheme() {
        SendBar()
    }
}