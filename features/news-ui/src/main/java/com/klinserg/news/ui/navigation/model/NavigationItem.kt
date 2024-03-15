package com.klinserg.news.ui.navigation.model

import androidx.annotation.DrawableRes

sealed class NavigationItem(
    val route: String,
    val name: String,
    @DrawableRes val icon: Int,
    @DrawableRes val iconSelected: Int,
)