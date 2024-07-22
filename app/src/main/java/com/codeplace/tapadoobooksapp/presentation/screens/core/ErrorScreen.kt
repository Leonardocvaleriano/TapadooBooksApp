package com.codeplace.tapadoobooksapp.presentation.screens.core

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.codeplace.tapadoobooksapp.R
import com.codeplace.tapadoobooksapp.presentation.screens.books.BooksViewModel
import com.codeplace.tapadoobooksapp.presentation.ui.theme.SpaceSizeM
import com.codeplace.tapadoobooksapp.presentation.ui.theme.SpaceSizeS
import com.codeplace.tapadoobooksapp.presentation.ui.theme.SpaceSizeXL

@Composable
fun ErrorScreenRoot(
    error: String,
    viewModel: BooksViewModel = hiltViewModel(),
) {
    ErrorScreen(
        error = error,
        onReloadClick = { viewModel.getBooks() }
    )

}

@Composable
fun ErrorScreen(
    onReloadClick: () -> Unit,
    modifier: Modifier = Modifier,
    error: String,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = SpaceSizeM),
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(SpaceSizeS)
        )
        {
            Text(
                text = stringResource(id = R.string.error_title),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = error,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = SpaceSizeXL, bottom = SpaceSizeXL),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { onReloadClick.invoke() },
                ) {
                    Text(text = stringResource(id = R.string.reload_button))
                }
            }

        }

    }

}

@Preview
@Composable
fun ErrorScreenPreview() {
    ErrorScreen(
        onReloadClick = {},
        error = "Error message"
    )
}