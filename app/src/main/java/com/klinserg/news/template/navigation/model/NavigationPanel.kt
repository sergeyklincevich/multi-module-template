package com.klinserg.news.template.navigation.model

import com.klinserg.news.ui.R

sealed class NavigationPanel(val route: String) {
    data object News : NavigationItem(
        route = "route_news",
        name = "News",
        icon = R.drawable.home,
        iconSelected = R.drawable.home,
    )

    data object Search : NavigationItem(
        route = "route_search",
        name = "Search",
        icon = android.R.drawable.ic_menu_search,
        iconSelected = android.R.drawable.ic_menu_search,
    )

    data object Favorite : NavigationItem(
        route = "route_favorite",
        name = "Favorite",
        icon = R.drawable.favorite,
        iconSelected = R.drawable.favorite,
    )

    data object Profile : NavigationItem(
        route = "route_profile",
        name = "Profile",
        icon = R.drawable.user,
        iconSelected = R.drawable.user,
    )
}