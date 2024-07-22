package com.codeplace.tapadoobooksapp.data.network.mapper

import com.codeplace.tapadoobooksapp.data.network.models.BookDto
import com.codeplace.tapadoobooksapp.domain.models.Book


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

