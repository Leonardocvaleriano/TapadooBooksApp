package com.codeplace.tapadoobooksapp.presentation.screens.book_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeplace.tapadoobooksapp.data.network.utils.NetworkError
import com.codeplace.tapadoobooksapp.data.network.utils.onError
import com.codeplace.tapadoobooksapp.data.network.utils.onSuccess
import com.codeplace.tapadoobooksapp.domain.models.BookDetails
import com.codeplace.tapadoobooksapp.domain.repository.TapadooBooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookDetailsScreenViewModel @Inject constructor(
    val tapadooBooksRepository: TapadooBooksRepository,
) : ViewModel() {


    var bookDetails by mutableStateOf<BookDetails>(BookDetails())
        private set

    var errorMessage by mutableStateOf<NetworkError?>(null)
        private set

    var isLoading by mutableStateOf(false)
    private set



    fun getBooksDetails(id: Int) = viewModelScope.launch {
            isLoading = true
            tapadooBooksRepository.getBookDetails(id = id)
                .onSuccess {
                    isLoading = false
                    bookDetails = it
                }
                .onError {
                     errorMessage = it
                }
        isLoading = false
        }

    }