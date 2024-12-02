package com.example.jetpackcompose.ui.views.main

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcompose.model.NewsModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class MainViewModel: ViewModel() {

    val news: Flow<List<NewsModel>> = flow<List<NewsModel>>{}
        .stateIn(viewModelScope, SharingStarted.Lazily, listOf())

    val categories: Flow<List<String>> = flow {
        val items = listOf(
            "Latest",
            "Top",
            "International",
            "Music",
            "Games",
            "Lifestyle",
        )
        delay(500)
        emit(items)
    }.stateIn(viewModelScope, SharingStarted.Lazily, listOf())

    fun sourceNews(category: String? = null){
        val items = listOf(
            NewsModel(
                title = "Hello World 1",
                category = "Latest",
                shortDescription = LoremIpsum(240).values.joinToString()
            ),
            NewsModel(
                title = "Hello World 2",
                category = "Top",
                shortDescription = LoremIpsum(240).values.joinToString()
            ),
            NewsModel(
                title = "Hello World 3",
                category = "International",
                shortDescription = LoremIpsum(240).values.joinToString()
            ),
            NewsModel(
                title = "Hello World 4",
                category = "Music",
                shortDescription = LoremIpsum(240).values.joinToString()
            )
        ).apply {
            if (category != null){
                filter { it.category.equals(category, ignoreCase = true) }
            }
        }
    }
}