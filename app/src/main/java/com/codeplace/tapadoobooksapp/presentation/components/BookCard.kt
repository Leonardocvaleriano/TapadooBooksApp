package com.codeplace.tapadoobooksapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.codeplace.tapadoobooksapp.R
import com.codeplace.tapadoobooksapp.presentation.ui.theme.SpaceSize3XS
import com.codeplace.tapadoobooksapp.presentation.ui.theme.SpaceSize4XS
import com.codeplace.tapadoobooksapp.presentation.ui.theme.SpaceSizeM
import com.codeplace.tapadoobooksapp.presentation.ui.theme.SpaceSizeS
import com.example.compose.TapadooBooksAppTheme
import java.text.DecimalFormat

@Composable
fun BookCard(
    modifier: Modifier = Modifier,
    title: String,
    author: String,
    isbn: String,
    currencyCode: String,
    price: Int,
    id: Int? = -1,
    onNavigateToBookDetails: (id: Int) -> Unit,
    showViewDetailsText: Boolean? = true,
    showRippleEffect: Boolean = true,
) {

    val rippleEnabled by remember { mutableStateOf(showRippleEffect) }
    val interactionSource = remember { MutableInteractionSource() }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (rippleEnabled) {
                    Modifier.clickable {
                        onNavigateToBookDetails(id!!)
                    }

                } else {
                    Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                    }

                }

            ),
        //onClick = {onNavigateToBookDetails(id!!)},
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),


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
                        text = if (showViewDetailsText!!) {
                            FormatTitleSize(title)
                        } else {
                            title
                        },
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
                if (showViewDetailsText!!) {
                    Text(
                        text = stringResource(R.string.button_view_details),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium
                    )

                }


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
