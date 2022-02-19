package com.homies.homie.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.homies.homie.Platform

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Platform.context = this

        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Hooomie()
                }
            }
        }
    }
}

@Composable
fun Hooomie() {
    Text(text = "First compose component :-D")
}
