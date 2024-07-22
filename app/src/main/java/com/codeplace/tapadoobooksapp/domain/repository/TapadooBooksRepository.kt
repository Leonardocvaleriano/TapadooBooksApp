package com.codeplace.tapadoobooksapp.domain.repository

import com.codeplace.tapadoobooksapp.data.network.utils.NetworkError
import com.codeplace.tapadoobooksapp.data.network.utils.Result
import com.codeplace.tapadoobooksapp.domain.models.Book

interface TapadooBooksRepository {

    suspend fun getBooks(): Result<List<Book>, NetworkError>
}