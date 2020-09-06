package com.yashovardhan99.bobblebigtext

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.Dimension
import androidx.compose.foundation.layout.RowScope.gravity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.ui.tooling.preview.Preview
import androidx.ui.tooling.preview.PreviewParameter
import com.yashovardhan99.bobblebigtext.ui.BobbleBigTextTheme

@ExperimentalFoundationApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[MessagesViewModel::class.java]
        setContent {
            BobbleBigTextTheme {
                val messages by viewModel.messages.collectAsState(initial = listOf())
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(topBar = {
                        TopAppBar(title = {
                            Text(text = "BigText")
                        })
                    }) {
                        MainPage(list = messages, modifier = Modifier.padding(it)) { message ->
                            viewModel.insertMessage(message)
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MainPage(modifier: Modifier = Modifier, list: List<Message>, insertMessage: (Message) -> Unit) {
    ConstraintLayout(modifier.fillMaxSize().gravity(Alignment.Bottom)) {
        val convList = createRef()
        val sendBar = createRef()
        ConversationList(list = list,
            modifier = Modifier.constrainAs(convList) {
                top.linkTo(parent.top)
                bottom.linkTo(sendBar.top)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            })
        SendBar(modifier = Modifier.constrainAs(sendBar) {
            bottom.linkTo(parent.bottom)
            width = Dimension.fillToConstraints
            height = Dimension.preferredValue(50.dp)
        }) {
            insertMessage(it)
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun MainPagePreview(@PreviewParameter(BooleanPreviewProvider::class) isDark: Boolean) {
    BobbleBigTextTheme(isDark) {
        Surface() {
            Scaffold(topBar = {
                TopAppBar(title = {
                    Text(text = "BigText")
                })
            }) {
                MainPage(list = getFakeMessages(), modifier = Modifier.padding(it)) {}
            }
        }
    }

}