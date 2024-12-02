package com.example.jetpackcompose.ui.listener

import com.example.jetpackcompose.model.NewsModel

interface NewsListListener {

    fun onClickNewsItem(news: NewsModel)
}