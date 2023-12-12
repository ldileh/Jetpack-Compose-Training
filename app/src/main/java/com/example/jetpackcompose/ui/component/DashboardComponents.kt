package com.example.jetpackcompose.ui.component

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
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.jetpackcompose.R
import com.example.jetpackcompose.model.NewsModel
import com.example.jetpackcompose.ui.listener.DashboardListener
import com.example.jetpackcompose.ui.listener.HeaderListener

@Composable
fun DashboardView(
    newsCategory: List<String>,
    news: List<NewsModel>,
    headerListener: HeaderListener,
    dashboardListener: DashboardListener
){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column{
            Header(listener = headerListener)
            NewsCategories(newsCategory)
            NewsList(news, listener = dashboardListener)
        }
    }
}

@Composable
fun NewsItem(item: NewsModel, modifier: Modifier = Modifier, listener: DashboardListener){
    val constraintSet = ConstraintSet {
        val banner = createRefFor("bannerNews")
        val containerNewsContent = createRefFor("containerNewsContent")

        constrain(banner){
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
            height = Dimension.value(240.dp)
        }

        constrain(containerNewsContent){
            linkTo(
                start = banner.start,
                top = banner.bottom,
                end = banner.end,
                bottom = parent.bottom,
                verticalBias = 0f,
                topMargin = (-56).dp
            )

            width = Dimension.value(320.dp)
            height = Dimension.wrapContent
        }
    }
    ConstraintLayout(
        constraintSet = constraintSet,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                listener.onClickNewsItem(item)
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_animals),
            contentDescription = "Banner news items",
            modifier = Modifier.layoutId("bannerNews"),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .layoutId("containerNewsContent")
                .background(Color.White)
                .heightIn(max = 320.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = item.title,
                    modifier = modifier.fillMaxWidth(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.headlineSmall
                )

                Text(
                    text = item.shortDescription,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(
                            start = 0.dp,
                            top = 8.dp,
                            end = 0.dp,
                            bottom = 0.dp
                        ),
                    maxLines = 6,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun NewsCategories(items: List<String>){
    val selectedCategory = remember{
        mutableIntStateOf(0)
    }

    LazyRow{
        items(items.size){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(8.dp, 4.dp)
                    .clickable {
                        selectedCategory.intValue = it
                    }
                    .padding(4.dp, 2.dp)
            ){
                Text(
                    text = items[it],
                    modifier = Modifier.padding(4.dp),
                    textDecoration = if(selectedCategory.intValue == it)
                        TextDecoration.Underline
                    else
                        TextDecoration.None,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
fun NewsList(items: List<NewsModel>, listener: DashboardListener){
    LazyColumn(
        modifier = Modifier
            .padding(0.dp, 8.dp, 0.dp, 0.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        itemsIndexed(items){ _, item ->
            NewsItem(
                item = item,
                modifier = Modifier,
                listener = listener
            )
        }
    }
}