package com.codeplace.tapadoobooksapp.data.network.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class BookDto(
    val id:Int,
    val title:String,
    val isbn:String,
    val price: Int,
    val currencyCode:String,
    val author:String
)