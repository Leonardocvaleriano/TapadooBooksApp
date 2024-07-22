package com.codeplace.tapadoobooksapp.domain.models

import java.math.BigDecimal

data class Book(
    val id:Int,
    val title:String,
    val isbn:String,
    val price:Int,
    val currencyCode:String,
    val author:String
)
