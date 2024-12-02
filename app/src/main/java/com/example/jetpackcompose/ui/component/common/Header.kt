package com.example.jetpackcompose.ui.component.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.R
import com.example.jetpackcompose.ui.listener.HeaderListener

@Composable
fun Header(modifier: Modifier = Modifier, listener: HeaderListener){
    val iconModifier = modifier
        .width(56.dp)
        .fillMaxHeight()
        .padding(4.dp)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Box(
            modifier = iconModifier
                .clickable { listener.onClickBurgerMenu() }
                .padding(4.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_menu_burger),
                modifier = Modifier.fillMaxSize(),
                contentDescription = "Menu Sandwich"
            )
        }

        Box(
            modifier = iconModifier
                .clickable { listener.onClickSearchMenu("") }
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview
@Composable
private fun PreviewHeader() {
    Header(listener = object : HeaderListener {
        override fun onClickBurgerMenu() {
        }

        override fun onClickSearchMenu(searchValue: String) {
        }

        override fun onCategorySelected(category: String) {
        }
    })
}