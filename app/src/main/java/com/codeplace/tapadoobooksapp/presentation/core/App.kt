package com.codeplace.tapadoobooksapp.presentation.core

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.codeplace.tapadoobooksapp.presentation.screens.book_details.BookDetailsScreenRoot
import com.codeplace.tapadoobooksapp.presentation.screens.books_list.BooksListScreenRoot
import kotlinx.serialization.Serializable

@Serializable
object BooksList

@Serializable
data class BookDetails(val id:Int)

@Composable
fun App() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = BooksList) {

        composable<BooksList> {
            BooksListScreenRoot(
                onNavigateToBookDetails = { id ->
                    navController.navigate(route = BookDetails(id))
                }
            )
        }
        composable<BookDetails> { backStackEntry ->
            val bookDetails: BookDetails = backStackEntry.toRoute()

            BookDetailsScreenRoot(id = bookDetails.id)
            }
        }

    }
