package com.yashovardhan99.bobblebigtext

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.Dimension
import androidx.compose.foundation.layout.RowScope.gravity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import androidx.ui.tooling.preview.PreviewParameter
import com.yashovardhan99.bobblebigtext.ui.BobbleBigTextTheme
import timber.log.Timber

@ExperimentalFoundationApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BobbleBigTextTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(topBar = {
                        TopAppBar(title = {
                            Text(text = "BigText")
                        })
                    }) {
                        MainPage(list = getFakeMessages(), modifier = Modifier.padding(it))
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MainPage(modifier: Modifier = Modifier, list: List<Message>) {
    ConstraintLayout(modifier.gravity(Alignment.Bottom)) {
        val convList = createRef()
        val sendBar = createRef()
        ConversationList(list = list,
            modifier = Modifier.constrainAs(convList) {
                top.linkTo(parent.top)
                bottom.linkTo(sendBar.top)
                width = Dimension.fillToConstraints
                height = Dimension.preferredWrapContent
            })
        SendBar(modifier = Modifier.constrainAs(sendBar) {
            bottom.linkTo(parent.bottom)
            width = Dimension.fillToConstraints
            height = Dimension.preferredValue(50.dp)
        }) {
            Timber.d("Message posted = $it")
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
                MainPage(list = getFakeMessages(), modifier = Modifier.padding(it))
            }
        }
    }

}