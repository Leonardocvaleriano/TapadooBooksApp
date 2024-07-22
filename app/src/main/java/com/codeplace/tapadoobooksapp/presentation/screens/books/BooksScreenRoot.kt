package com.codeplace.tapadoobooksapp.presentation.screens.books

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BooksScreenRoot(
    viewModel: BooksViewModel = hiltViewModel()
) {

    BooksScreen(
        books = viewModel.books.value,
        isLoading = viewModel.isLoading.value,
        error = viewModel.errorMessage
    )

}
