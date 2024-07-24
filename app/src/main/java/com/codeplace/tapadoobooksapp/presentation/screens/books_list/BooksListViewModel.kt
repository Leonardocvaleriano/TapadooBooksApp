package com.codeplace.tapadoobooksapp.presentation.screens.books_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeplace.tapadoobooksapp.data.network.utils.NetworkError
import com.codeplace.tapadoobooksapp.data.network.utils.onError
import com.codeplace.tapadoobooksapp.data.network.utils.onSuccess
import com.codeplace.tapadoobooksapp.domain.models.Book
import com.codeplace.tapadoobooksapp.domain.repository.TapadooBooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BooksListViewModel @Inject constructor(
    private val repository: TapadooBooksRepository,
) : ViewModel() {

    var books by mutableStateOf<List<Book>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
        private set

    var errorMessage by mutableStateOf<NetworkError?>(null)
        private set

    init {
        getBooks()
    }

    fun getBooks() = viewModelScope.launch(Dispatchers.IO) {
        repository.getBooks().let { response ->
            response.onSuccess {
                books = it
            }.onError {
                errorMessage = it
            }
        }
        isLoading = false
    }
}
