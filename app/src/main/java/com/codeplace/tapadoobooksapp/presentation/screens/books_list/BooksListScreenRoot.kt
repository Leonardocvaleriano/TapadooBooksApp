package com.codeplace.tapadoobooksapp.presentation.screens.books_list

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.codeplace.tapadoobooksapp.R
import com.codeplace.tapadoobooksapp.data.network.utils.NetworkError
import com.codeplace.tapadoobooksapp.domain.models.Book
import com.codeplace.tapadoobooksapp.presentation.components.BookCard
import com.codeplace.tapadoobooksapp.presentation.screens.ErrorScreen
import com.codeplace.tapadoobooksapp.presentation.ui.theme.SpaceSize4XL
import com.codeplace.tapadoobooksapp.presentation.ui.theme.SpaceSizeM
import com.example.compose.TapadooBooksAppTheme

@Composable
fun BooksListScreenRoot(
    viewModel: BooksListViewModel = hiltViewModel(),
    onNavigateToBookDetails: (id:Int) -> Unit,
    onNavigateToBookList: () -> Unit
) {

    BooksListScreen(
        books = viewModel.books,
        isLoading = viewModel.isLoading,
        error = viewModel.errorMessage,
        onNavigateToBookDetails = onNavigateToBookDetails,
        onNavigateToBookList = onNavigateToBookList

    )

}

@Composable
fun BooksListScreen(
    modifier: Modifier = Modifier,
    books: List<Book>,
    isLoading: Boolean,
    error: NetworkError?,
    onNavigateToBookDetails: (id:Int) -> Unit,
    onNavigateToBookList: () -> Unit
    ) {

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
            onNavigateToBookList =  onNavigateToBookList)

    } else {
        BookListContent(
            books = books,
            onNavigateToBookDetails = onNavigateToBookDetails
        )
    }
}


@Composable
fun BookListContent(
    modifier: Modifier = Modifier,
    books: List<Book>,
    onNavigateToBookDetails: (id:Int) -> Unit
){
    LazyColumn(
        modifier = modifier.padding(
            top = SpaceSize4XL,
            start = SpaceSizeM,
            end = SpaceSizeM,
            bottom = SpaceSize4XL
        ), verticalArrangement = Arrangement.spacedBy(SpaceSizeM)
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(SpaceSizeM)) {
                Text(
                    text = stringResource(id = R.string.headline_hello),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = stringResource(id = R.string.title_tapadoo_books),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        items(books) { bookItem ->
            BookCard(
                onNavigateToBookDetails = onNavigateToBookDetails,
                title = bookItem.title,
                author = bookItem.author,
                isbn = bookItem.isbn,
                currencyCode = bookItem.currencyCode,
                price = bookItem.price,
                id = bookItem.id,
            )
        }
    }
}





@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Full Preview", showSystemUi = true)
@Composable
fun BooksScreenPreview() {

    Surface {
        TapadooBooksAppTheme {
            val booksListMock: ArrayList<Book> = ArrayList()
            val baseTitle = "Lorem ipsum"
            val increasedTitle = mutableListOf<String>()
            var currentTitle = baseTitle

            for (i in 1..7) {
                increasedTitle.add(currentTitle)
                currentTitle += baseTitle

                booksListMock.add(
                    Book(
                        id = 1,
                        title = currentTitle,
                        author = "John",
                        isbn = "22222",
                        price = 3333,
                        currencyCode = "€"
                    )
                )

            }

            BooksListScreen(
                onNavigateToBookDetails = {},
                books = booksListMock,
                isLoading = false,
                error = null,
                onNavigateToBookList = {}
            )
        }
    }
}
