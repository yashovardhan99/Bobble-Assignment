package com.yashovardhan99.bobblebigtext

import androidx.compose.animation.animatedFloat
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ColumnScope.gravity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.gesture.LongPressDragObserver
import androidx.compose.ui.gesture.longPressDragGestureFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.TextUnit
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
    val scrollState = rememberScrollState()
    var value by remember { mutableStateOf(TextFieldValue("")) }
    BaseTextField(
        value = value, onValueChange = { updated ->
            value = updated
        }, textStyle = TextStyle(fontSize = size), modifier = modifier.verticalScroll(scrollState)
            .background(MaterialTheme.colors.surface, RectangleShape)
            .padding(4.dp)
    )
}

@ExperimentalFoundationApi
@Composable
fun SendBar(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val size = animatedFloat(initVal = 12f)
    size.setBounds(12f, 100f)
    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val edit = createRef()
        val button = createRef()
        BigTextField(size = size.value.sp, modifier = Modifier.constrainAs(edit) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            end.linkTo(button.start)
            width = Dimension.fillToConstraints
            height = Dimension.preferredWrapContent.atLeast(24.dp)
        }.gravity(Alignment.Start))
        SendButton(onClick = {
            size.animateTo(12f)
        }, onLongStart = {
            text = true
            scope.launch {
                while (text) {
                    size.animateTo(size.value + 1f)
                    delay(100)
                }
            }
        }, onLongEnd = {
            text = false
        }, modifier = Modifier.constrainAs(button) {
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
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