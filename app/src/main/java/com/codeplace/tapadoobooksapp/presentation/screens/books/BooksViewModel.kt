package com.codeplace.tapadoobooksapp.presentation.screens.books

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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val repository: TapadooBooksRepository,
) : ViewModel() {

    var books = mutableStateOf<List<Book>>(emptyList())
        private set

    var isLoading = mutableStateOf(false)
        private set
    var errorMessage by mutableStateOf<NetworkError?>(null)
        private set

    init {
        getBooks()
    }


    fun getBooks() = viewModelScope.launch {
        isLoading.value = true
        repository.getBooks().onSuccess {
            isLoading.value = false
            books.value = it
            errorMessage = null
        }
            .onError {
                isLoading.value = false
                errorMessage = it
            }
    }
}