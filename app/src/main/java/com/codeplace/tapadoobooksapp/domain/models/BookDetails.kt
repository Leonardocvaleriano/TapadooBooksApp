package com.codeplace.tapadoobooksapp.domain.models


data class BookDetails(
    val id:Int? = null,
    val title:String?="",
    val isbn:String? ="",
    val description:String?="",
    val price:Int?= 0,
    val currencyCode:String? = "",
    val author:String? = ""
)