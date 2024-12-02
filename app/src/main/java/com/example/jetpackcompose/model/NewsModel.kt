package com.example.jetpackcompose.model

data class NewsModel(
    val id: Int = -1,
    val category: String = "",
    val title: String,
    val shortDescription: String
)
