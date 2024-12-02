package com.example.jetpackcompose.ui.component.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.model.NewsModel
import com.example.jetpackcompose.ui.listener.NewsListListener

@Composable
fun NewsList(items: List<NewsModel>, listener: NewsListListener){
    LazyColumn(
        modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 0.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        itemsIndexed(items){ index, item ->
            NewsItem(
                item = item,
                isLastItem = index == (items.size - 1),
                modifier = Modifier,
                listener = listener
            )
        }
    }
}

@Preview
@Composable
private fun PreviewListItem(@PreviewParameter(LoremIpsum::class) shortDescription: String){
    val news = listOf(
        NewsModel(
            title = "Hello World 1",
            shortDescription = LoremIpsum(240).values.joinToString()
        ),
        NewsModel(
            title = "Hello World 2",
            shortDescription = LoremIpsum(240).values.joinToString()
        ),
        NewsModel(
            title = "Hello World 3",
            shortDescription = LoremIpsum(240).values.joinToString()
        ),
        NewsModel(
            title = "Hello World 4",
            shortDescription = LoremIpsum(240).values.joinToString()
        ),
    )

    Column {
        NewsList(news, listener = object : NewsListListener{
            override fun onClickNewsItem(news: NewsModel) {

            }
        })
    }
}