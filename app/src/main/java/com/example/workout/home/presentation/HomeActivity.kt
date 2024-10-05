package com.example.workout.home.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {

}

@Preview
@Composable
fun PreviewHome() {
    Home()
}

@Composable
fun Home(

) {

}