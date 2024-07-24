package com.codeplace.tapadoobooksapp.data.datasource

import com.codeplace.tapadoobooksapp.data.network.mapper.toDomain
import com.codeplace.tapadoobooksapp.data.network.models.BookDetailsDto
import com.codeplace.tapadoobooksapp.data.network.models.BookDto
import com.codeplace.tapadoobooksapp.data.network.utils.HttpRoutes
import com.codeplace.tapadoobooksapp.data.network.utils.NetworkError
import com.codeplace.tapadoobooksapp.domain.repository.TapadooBooksRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.SerializationException
import java.net.UnknownHostException
import java.nio.channels.UnresolvedAddressException
import com.codeplace.tapadoobooksapp.data.network.utils.Result
import com.codeplace.tapadoobooksapp.domain.models.Book
import com.codeplace.tapadoobooksapp.presentation.core.BookDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TapadooBooksRepositoryImpl @Inject constructor(
    private val client: HttpClient,
) : TapadooBooksRepository {

    override suspend fun getBooks(): Result<List<Book>, NetworkError> {
        val response = try {
            client.get(urlString = HttpRoutes.BOOKS)
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: UnknownHostException) {
            return Result.Error(NetworkError.UNABLE_TO_CONNECT)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }
        return when (response.status.value) {
            in 200..299 -> {
                val booksResponse = response.body<List<BookDto>>()
                Result.Success(booksResponse.map { it.toDomain() })
            }

            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }

    }

    override suspend fun getBookDetails(id: Int): Result<com.codeplace.tapadoobooksapp.domain.models.BookDetails, NetworkError> {
        val response = try {
            client.get(urlString = HttpRoutes.SINGLE_BOOK + id)
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: UnknownHostException) {
            return Result.Error(NetworkError.UNABLE_TO_CONNECT)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }
        return when (response.status.value) {
            in 200..299 -> {
                val bookDetailsResponse = response.body<BookDetailsDto>()
                Result.Success(bookDetailsResponse.toDomain())
            }
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }


}