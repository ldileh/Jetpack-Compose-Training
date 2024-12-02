package com.example.jetpackcompose.ui.component.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.jetpackcompose.R
import com.example.jetpackcompose.model.NewsModel
import com.example.jetpackcompose.ui.listener.NewsListListener

@Composable
fun NewsItem(item: NewsModel, isLastItem: Boolean, modifier: Modifier = Modifier, listener: NewsListListener){
    val constraintSet = ConstraintSet {
        val banner = createRefFor("bannerNews")
        val containerNewsContent = createRefFor("containerNewsContent")

        constrain(banner) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
            height = Dimension.value(240.dp)
        }

        constrain(containerNewsContent) {
            linkTo(
                start = banner.start,
                top = banner.bottom,
                end = banner.end,
                bottom = parent.bottom,
                verticalBias = 0f,
                topMargin = (-56).dp,
                bottomMargin = (if(isLastItem) 32 else 0).dp
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
                    text = item.category,
                    modifier = modifier.wrapContentWidth(),
                    maxLines = 1,
                    fontSize = 11.sp,
                    fontStyle = FontStyle.Italic,
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

@Preview
@Composable
private fun PreviewListItem(@PreviewParameter(LoremIpsum::class) shortDescription: String){
    NewsItem(
        item = NewsModel(
            title = "Hello World 1",
            category = "Sport",
            shortDescription = shortDescription,
        ),
        false,
        listener = object : NewsListListener{
            override fun onClickNewsItem(news: NewsModel) {
            }
        }
    )
}