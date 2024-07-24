@file:JvmName("BookDetailsScreenRootKt")

package com.codeplace.tapadoobooksapp.presentation.screens.book_details

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.codeplace.tapadoobooksapp.R
import com.codeplace.tapadoobooksapp.data.network.utils.NetworkError
import com.codeplace.tapadoobooksapp.domain.models.BookDetails
import com.codeplace.tapadoobooksapp.presentation.components.BookCard
import com.codeplace.tapadoobooksapp.presentation.screens.ErrorScreen
import com.codeplace.tapadoobooksapp.presentation.ui.theme.SpaceSize2XS
import com.codeplace.tapadoobooksapp.presentation.ui.theme.SpaceSizeL
import com.codeplace.tapadoobooksapp.presentation.ui.theme.SpaceSizeM
import com.codeplace.tapadoobooksapp.presentation.ui.theme.SpaceSizeXL
import com.example.compose.TapadooBooksAppTheme


@Composable
fun BookDetailsScreenRoot(
    id: Int,
    viewModel: BookDetailsScreenViewModel = hiltViewModel(),
    onNavigateToBookList: () -> Unit,

    ) {
    LaunchedEffect(true) {
        viewModel.getBooksDetails(id = id)
    }

    BookDetailsScreen(
        isLoading = viewModel.isLoading,
        error = viewModel.errorMessage,
        bookDetails = viewModel.bookDetails,
        onNavigateToBookList = onNavigateToBookList
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailsScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    error: NetworkError?,
    bookDetails: BookDetails,
    onNavigateToBookList: () -> Unit,
) {
    Scaffold(
        modifier = modifier.padding(top = SpaceSizeM),
        topBar ={
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        color = MaterialTheme.colorScheme.onSurface,
                        text = stringResource(R.string.topAppBar_title_details),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateToBookList){
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Back icon",
                            tint = MaterialTheme.colorScheme.onSurface,
                        )
                    }

                }
            )
        }
    ){ innerPadding ->

        Box(modifier = modifier
            .padding(innerPadding)){
            if (isLoading) {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary
                    )
                }

            } else if (error != null) {
                ErrorScreen(
                    error = error.name,
                    onNavigateToBookList = onNavigateToBookList
                )
            } else {
                Column(modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(state = rememberScrollState())) {
                    BookDetailsContent(
                        bookDetails = bookDetails,
                        modifier = modifier
                    )
                }
            }

        }
    }
}

@Composable
fun BookDetailsContent(
    bookDetails:BookDetails,
    modifier: Modifier
){
    Column(
        modifier = modifier
            .padding(
                top = SpaceSizeL,
                start = SpaceSizeM,
                end = SpaceSizeM,
                bottom = SpaceSizeL),
        verticalArrangement = Arrangement.spacedBy(SpaceSizeXL)
    ) {
        BookCard(
            title = bookDetails.title!!,
            author = bookDetails.author!!,
            isbn = bookDetails.isbn!!,
            currencyCode = bookDetails.currencyCode!!,
            price = bookDetails.price!!,
            onNavigateToBookDetails = {},
            showViewDetailsText = false,
            showRippleEffect = false
        )
        Column(
            modifier = modifier
                .padding(start = SpaceSizeM, end = SpaceSizeM, bottom = SpaceSizeL),
            verticalArrangement = Arrangement.spacedBy(SpaceSize2XS)
        ) {
            Text(
                text = stringResource(R.string.title_description),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = bookDetails.description!!,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

    }
}




@Composable
@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Full Preview", showSystemUi = true)
fun BookDetailsScreenPreview() {
    TapadooBooksAppTheme {
        BookDetailsScreen(
            isLoading = false,
            error = null,
            bookDetails = BookDetails(),
            onNavigateToBookList = {}
        )
    }

}
