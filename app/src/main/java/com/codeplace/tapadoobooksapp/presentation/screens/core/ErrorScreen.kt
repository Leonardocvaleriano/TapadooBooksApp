package com.codeplace.tapadoobooksapp.presentation.screens.core

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.codeplace.tapadoobooksapp.R
import com.codeplace.tapadoobooksapp.presentation.screens.books.BooksViewModel
import com.codeplace.tapadoobooksapp.presentation.ui.theme.IconSizeXlarge
import com.codeplace.tapadoobooksapp.presentation.ui.theme.SpaceSizeM
import com.codeplace.tapadoobooksapp.presentation.ui.theme.SpaceSizeS
import com.codeplace.tapadoobooksapp.presentation.ui.theme.SpaceSizeXL
import com.example.compose.TapadooBooksAppTheme

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
            Icon(
                imageVector = androidx.compose.material.icons.Icons.Rounded.Warning,
                contentDescription = "Warning icon ",
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = modifier.size(IconSizeXlarge)
            )

            Text(
                text = stringResource(id = R.string.title_error),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = error,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = SpaceSizeXL, bottom = SpaceSizeXL),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    onClick = { onReloadClick.invoke() },
                ) {
                    Text(
                        text = stringResource(id = R.string.button_reload)
                    )
                }
            }

        }

    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
fun ErrorScreenPreview(showBackground: Boolean = true) {
    TapadooBooksAppTheme {
        ErrorScreen(
            onReloadClick = {},
            error = "Error message"
        )
    }
}