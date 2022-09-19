package com.rino.redditinfinitylistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rino.redditinfinitylistapp.ui.screens.HotPostsScreen
import com.rino.redditinfinitylistapp.ui.screens.MainViewModel
import com.rino.redditinfinitylistapp.ui.theme.RedditInfinityListAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(mainViewModel)
        }
    }
}

@Composable
fun MyApp(mainViewModel: MainViewModel) {
    RedditInfinityListAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            HotPostsScreen(mainViewModel)
        }
    }
}