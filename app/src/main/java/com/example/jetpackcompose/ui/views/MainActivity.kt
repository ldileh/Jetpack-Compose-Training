package com.example.jetpackcompose.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.jetpackcompose.R
import com.example.jetpackcompose.model.NewsModel
import com.example.jetpackcompose.ui.component.DashboardView
import com.example.jetpackcompose.ui.component.Header
import com.example.jetpackcompose.ui.component.NewsCategories
import com.example.jetpackcompose.ui.component.NewsItem
import com.example.jetpackcompose.ui.listener.DashboardListener
import com.example.jetpackcompose.ui.listener.HeaderListener
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import com.example.jetpackcompose.utils.MessageViewUtil

class MainActivity : ComponentActivity(), HeaderListener, DashboardListener {

    private val message: MessageViewUtil by lazy {
        MessageViewUtil(this@MainActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetpackComposeTheme {
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
                DashboardView(
                    newsCategory = newsCategory,
                    news = news,
                    headerListener = this,
                    dashboardListener = this
                )
            }
        }
    }

    override fun onClickBurgerMenu() {
        message.show("Burger Menu")
    }

    override fun onClickSearchMenu(searchValue: String) {
        message.show("Search Menu")
    }

    override fun onClickNewsItem(news: NewsModel) {
        message.show(news.title)
    }

    @Preview
    @Composable
    fun PreviewListItem(
        @PreviewParameter(LoremIpsum::class) shortDescription: String
    ){
        NewsItem(
            item = NewsModel(
                title = "Hello World 1",
                shortDescription = shortDescription,
            ),
            listener = this
        )
    }

    @Preview
    @Composable
    fun PreviewHeader() {
        Header(listener = this)
    }

    @Preview
    @Composable
    fun PreviewCategories(){
        NewsCategories(listOf(
            "Latest",
            "Top",
            "International",
            "Music",
            "Games",
            "Lifestyle",
        ))
    }

    @Preview
    @Composable
    fun PreviewDashboard(){
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
        DashboardView(
            newsCategory = newsCategory,
            news = news,
            headerListener = this,
            dashboardListener = this
        )
    }
}