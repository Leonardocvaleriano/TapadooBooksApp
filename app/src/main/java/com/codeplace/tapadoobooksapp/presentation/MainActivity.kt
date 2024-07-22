package com.codeplace.tapadoobooksapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.codeplace.tapadoobooksapp.presentation.screens.books.BooksScreenRoot
import com.example.compose.TapadooBooksAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentCompose()

    }
}

private fun MainActivity.setContentCompose() {
    enableEdgeToEdge()
    setContent {
        TapadooBooksAppTheme {

            // A surface container using the 'background' color from the theme
            Surface(
                color = MaterialTheme.colorScheme.surface
            ) {
                BooksScreenRoot()
            }
        }
    }
}

