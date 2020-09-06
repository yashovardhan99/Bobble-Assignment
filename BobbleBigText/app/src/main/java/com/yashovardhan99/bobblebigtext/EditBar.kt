package com.yashovardhan99.bobblebigtext

import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ColumnScope.gravity
import androidx.compose.foundation.layout.RowScope.gravity
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.yashovardhan99.bobblebigtext.ui.BobbleBigTextTheme

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
fun BigTextField(
    textFieldValue: TextFieldValue,
    updateTextFieldValue: (TextFieldValue) -> Unit,
    size: TextUnit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    BaseTextField(
        value = textFieldValue, onValueChange = { updated ->
            updateTextFieldValue(updated)
        }, textStyle = TextStyle(fontSize = size), modifier = modifier.verticalScroll(scrollState)
            .padding(4.dp)
            .background(MaterialTheme.colors.surface, RoundedCornerShape(8.dp))
            .padding(16.dp).gravity(Alignment.CenterVertically)
    )
}

@ExperimentalFoundationApi
@Composable
fun SendBar(modifier: Modifier = Modifier, postMessage: (Message) -> Unit) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }
    val size = animatedFloat(initVal = 12f)
    size.setBounds(12f, 64f)
    Surface(elevation = 4.dp, modifier = modifier.fillMaxWidth()) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            val edit = createRef()
            val button = createRef()
            BigTextField(
                textFieldValue = textFieldValue,
                updateTextFieldValue = { value -> textFieldValue = value },
                size = size.value.sp,
                modifier = Modifier.constrainAs(edit) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(button.start)
                    width = Dimension.fillToConstraints
                    height = Dimension.preferredWrapContent.atLeast(50.dp)
                }.gravity(Alignment.Start)
            )
            SendButton(onClick = {
                if (textFieldValue.text.isNotBlank()) {
                    postMessage(Message(textFieldValue.text, size.value.sp))
                    textFieldValue = TextFieldValue("")
                    size.stop()
                    size.animateTo(12f)
                }
            }, onLongStart = {
                if (textFieldValue.text.isNotBlank())
                    size.animateTo(size.max, FloatTweenSpec(duration = 2000, easing = LinearEasing))
            }, onLongEnd = {
                if (textFieldValue.text.isNotBlank()) {
                    postMessage(Message(textFieldValue.text, size.value.sp))
                    textFieldValue = TextFieldValue("")
                    size.stop()
                    size.animateTo(12f)
                }
            }, modifier = Modifier.constrainAs(button) {
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            })
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun SendBarPreview() {
    BobbleBigTextTheme {
        SendBar {}
    }
}