package com.codeplace.tapadoobooksapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.codeplace.tapadoobooksapp.R
import com.codeplace.tapadoobooksapp.presentation.core.BookDetails
import com.codeplace.tapadoobooksapp.presentation.core.BooksList
import com.codeplace.tapadoobooksapp.presentation.ui.theme.SpaceSize2XS
import com.codeplace.tapadoobooksapp.presentation.ui.theme.SpaceSize3XS
import com.codeplace.tapadoobooksapp.presentation.ui.theme.SpaceSize4XS
import com.codeplace.tapadoobooksapp.presentation.ui.theme.SpaceSizeM
import com.codeplace.tapadoobooksapp.presentation.ui.theme.SpaceSizeS
import com.codeplace.tapadoobooksapp.presentation.ui.theme.SpaceSizeXS
import com.example.compose.TapadooBooksAppTheme
import java.math.BigDecimal
import java.text.DecimalFormat

@Composable
fun BookCard(
    title: String,
    author: String,
    isbn: String,
    currencyCode: String,
    price: Int,
    id:Int,
    modifier: Modifier = Modifier,
    onNavigateToBookDetails: (id:Int) -> Unit,
) {
    Card(
        onClick = {onNavigateToBookDetails(id) },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
        //elevation = CardDefaults.cardElevation(0.dp)
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(SpaceSizeM),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(SpaceSizeS)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(SpaceSize3XS)) {
                    Text(
                        modifier = modifier.fillMaxWidth(0.9f),
                        text = FormatTitleSize(title),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = author,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface

                    )
                }
                Text(
                    text = isbn,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Text(
                    text = stringResource(R.string.button_view_details),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                )

            }
            Column() {
                Row(horizontalArrangement = Arrangement.spacedBy(SpaceSize4XS)) {
                    Text(
                        text = FormatIntenger(price),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium

                    )
                    Text(
                        text = currencyCode,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )

                }

            }

        }
    }
}


@Preview
@Composable
fun BookCardPreview() {

    TapadooBooksAppTheme {

        BookCard(
            title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            author = "Lorem ipsum",
            isbn = "22222-333",
            price = 2222,
            currencyCode = "Euro",
            id = -1,
            onNavigateToBookDetails = {}
        )
    }
}

@Composable
fun FormatIntenger(price: Int): String {
    val decimalNumber = price.toDouble() / 100 // Define the desired format
    val formatter = DecimalFormat("#0.00")
    return formatter.format(decimalNumber)
}

@Composable
fun FormatTitleSize(title: String): String {
    return if (title.length > 30) {
        title.substring(0, 27) + "..."
    } else {
        title
    }
}




