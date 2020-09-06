package com.yashovardhan99.bobblebigtext

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import com.yashovardhan99.bobblebigtext.ui.BobbleBigTextTheme

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
                        ConversationList(list = getFakeMessages(), modifier = Modifier.padding(it))
                    }
                }
            }
        }
    }
}