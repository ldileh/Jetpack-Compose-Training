package com.example.jetpackcompose.ui.listener

interface HeaderListener {

    fun onClickBurgerMenu()
    fun onClickSearchMenu(searchValue: String)
    fun onCategorySelected(category: String)
}