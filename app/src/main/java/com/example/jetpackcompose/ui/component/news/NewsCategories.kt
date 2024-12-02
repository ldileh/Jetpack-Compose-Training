package com.example.jetpackcompose.ui.component.news

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.ui.listener.HeaderListener
import com.example.jetpackcompose.ui.theme.GrayLight

@Composable
fun NewsCategories(items: List<String>, listener: HeaderListener){
    val selectedCategory = remember {
        mutableIntStateOf(0)
    }

    LazyRow {
        items(items.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(4.dp, 4.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(50),
                    )
                    .background(
                        color = if (selectedCategory.intValue == it) GrayLight else Color.White,
                        shape = RoundedCornerShape(50),
                    )
                    .clickable {
                        listener.onCategorySelected(items[it])
                        selectedCategory.intValue = it
                    }
                    .padding(4.dp, 2.dp)
            ) {
                Text(
                    text = items[it],
                    modifier = Modifier.padding(16.dp, 4.dp),
                    textDecoration = if (selectedCategory.intValue == it)
                        TextDecoration.Underline
                    else
                        TextDecoration.None,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCategories(){
    NewsCategories(
        listOf(
            "Latest",
            "Top",
            "International",
            "Music",
            "Games",
            "Lifestyle",
        ),
        object : HeaderListener {
            override fun onClickBurgerMenu() {
            }

            override fun onClickSearchMenu(searchValue: String) {
            }

            override fun onCategorySelected(category: String) {
            }
        }
    )
}