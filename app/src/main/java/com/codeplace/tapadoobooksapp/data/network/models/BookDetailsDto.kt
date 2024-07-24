package com.codeplace.tapadoobooksapp.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class BookDetailsDto(
    val id:Int,
    val title:String,
    val isbn:String,
    val description:String,
    val price:Int,
    val currencyCode:String,
    val author:String,
)