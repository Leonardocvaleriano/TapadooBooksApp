@file:JvmName("BookDetailsScreenRootKt")

package com.codeplace.tapadoobooksapp.presentation.screens.book_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.codeplace.tapadoobooksapp.data.network.utils.NetworkError
import com.codeplace.tapadoobooksapp.domain.models.BookDetails
import com.codeplace.tapadoobooksapp.presentation.components.BookCard
import com.codeplace.tapadoobooksapp.presentation.screens.ErrorScreen
import com.codeplace.tapadoobooksapp.presentation.ui.theme.SpaceSize4XL
import com.codeplace.tapadoobooksapp.presentation.ui.theme.SpaceSizeM


@Composable
fun BookDetailsScreenRoot(
    id:Int,
    viewModel: BookDetailsScreenViewModel = hiltViewModel(),
    onNavigateToBookList: () -> Unit

    ){
    LaunchedEffect(true){
        viewModel.getBooksDetails(id = id)
    }

    BookDetailsScreen(
        isLoading = viewModel.isLoading,
        error = viewModel.errorMessage,
        bookDetails = viewModel.bookDetails,
        onNavigateToBookList = onNavigateToBookList
    )
}

@Composable
fun BookDetailsScreen(
    modifier:Modifier = Modifier,
    isLoading:Boolean,
    error:NetworkError?,
    bookDetails: BookDetails,
    onNavigateToBookList: () -> Unit
) {
    if (isLoading){
        Box(modifier = modifier.fillMaxSize(),
            contentAlignment = androidx.compose.ui.Alignment.Center
            ){
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary
            )
        }

    } else if(error != null){
        ErrorScreen(
            error = error.name,
            onNavigateToBookList = onNavigateToBookList
            )
    } else {
        Column(modifier = modifier
            .padding(
                top = SpaceSize4XL,
                start = SpaceSizeM,
                end = SpaceSizeM,
                bottom = SpaceSize4XL)
        ) {
            BookCard(
                title = bookDetails.title!!,
                author = bookDetails.author!!,
                isbn = bookDetails.isbn!!,
                currencyCode = bookDetails.currencyCode!!,
                price = bookDetails.price!!,
                onNavigateToBookDetails = {},
                showViewDetailsText = false
            )

        }
    }
}
@Composable
@Preview(name = "Light Mode", showBackground = true)
//@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
//@Preview(name = "Full Preview", showSystemUi = true)
fun BookDetailsScreenPreview() {
    BookDetailsScreen(
        isLoading = true,
        error = null,
        bookDetails = BookDetails(),
        onNavigateToBookList = {}
    )
}
