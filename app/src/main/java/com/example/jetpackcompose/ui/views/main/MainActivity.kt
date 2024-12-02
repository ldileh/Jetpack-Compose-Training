package com.example.jetpackcompose.ui.views.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jetpackcompose.model.NewsModel
import com.example.jetpackcompose.ui.component.news.DashboardView
import com.example.jetpackcompose.ui.listener.HeaderListener
import com.example.jetpackcompose.ui.listener.NewsListListener
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import com.example.jetpackcompose.utils.MessageViewUtil

class MainActivity : ComponentActivity(), HeaderListener, NewsListListener {

    private val message: MessageViewUtil by lazy {
        MessageViewUtil(this@MainActivity)
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetpackComposeTheme {
                val newsCategory by viewModel.categories.collectAsStateWithLifecycle(
                    initialValue = emptyList()
                )

                val newsItems by viewModel.news().collectAsStateWithLifecycle(
                    initialValue = emptyList()
                )

                DashboardView(
                    newsCategory = newsCategory,
                    newsItems = newsItems,
                    headerListener = this,
                    newsListListener = this,
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

    override fun onCategorySelected(category: String) {
    }

    override fun onClickNewsItem(news: NewsModel) {
        message.show(news.title)
    }
}