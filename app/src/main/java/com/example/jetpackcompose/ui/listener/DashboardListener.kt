package com.example.jetpackcompose.ui.listener

import com.example.jetpackcompose.model.NewsModel

interface DashboardListener {

    fun onClickNewsItem(news: NewsModel)
}