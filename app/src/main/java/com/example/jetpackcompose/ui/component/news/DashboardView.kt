package com.example.jetpackcompose.ui.component.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.example.jetpackcompose.model.NewsModel
import com.example.jetpackcompose.ui.component.common.Header
import com.example.jetpackcompose.ui.listener.HeaderListener
import com.example.jetpackcompose.ui.listener.NewsListListener
import com.example.jetpackcompose.ui.theme.Gray

@Composable
fun DashboardView(
    newsCategory: List<String>,
    newsItems: List<NewsModel>,
    headerListener: HeaderListener,
    newsListListener: NewsListListener,
){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Gray,
    ) {
        Column {
            Column(modifier = Modifier.background(Color.White)) {
                Header(listener = headerListener)
                NewsCategories(newsCategory, headerListener)
            }
            NewsList(newsItems, listener = newsListListener)
        }
    }
}

@Preview
@Composable
private fun PreviewDashboard(){
    val newsCategory = listOf(
        "Latest",
        "Top",
        "International",
        "Music",
        "Games",
        "Lifestyle",
    )
    val news = listOf(
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
        ),
    )

    DashboardView(
        newsCategory = newsCategory,
        newsItems = news,
        headerListener = object: HeaderListener{
            override fun onClickBurgerMenu() {
            }

            override fun onClickSearchMenu(searchValue: String) {
            }

            override fun onCategorySelected(category: String) {
            }
        },
        newsListListener = object : NewsListListener{
            override fun onClickNewsItem(news: NewsModel) {
            }
        }
    )
}