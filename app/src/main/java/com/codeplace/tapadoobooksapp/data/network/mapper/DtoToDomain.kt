package com.codeplace.tapadoobooksapp.data.network.mapper

import com.codeplace.tapadoobooksapp.data.network.models.BookDetailsDto
import com.codeplace.tapadoobooksapp.data.network.models.BookDto
import com.codeplace.tapadoobooksapp.domain.models.Book
import com.codeplace.tapadoobooksapp.domain.models.BookDetails


fun BookDto.toDomain(): Book {
    return Book(
        id = id,
        title = title,
        isbn = isbn,
        price = price,
        currencyCode = currencyCode,
        author = author
    )
}

fun BookDetailsDto.toDomain(): BookDetails {
    return BookDetails(
        id = id,
        title = title,
        isbn = isbn,
        description = description,
        price = price,
        currencyCode = currencyCode,
        author = author
    )
}

