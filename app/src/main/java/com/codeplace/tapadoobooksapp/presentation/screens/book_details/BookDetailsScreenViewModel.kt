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


    var bookDetails by mutableStateOf(BookDetails())
        private set

    var errorMessage by mutableStateOf<NetworkError?>(null)
        private set

    var isLoading by mutableStateOf(true)
    private set



    fun getBooksDetails(id: Int) = viewModelScope.launch(Dispatchers.IO){
            tapadooBooksRepository.getBookDetails(id = id)
                .onSuccess {
                    bookDetails = it
                }
                .onError {
                     errorMessage = it
                }
        isLoading = false
        }

    }