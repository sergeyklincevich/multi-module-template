package com.klinserg.news.ui.navigation.model

sealed class NavigationPanel {
    data object News : NavigationItem(
        route = "route_news",
        name = "News",
        icon = androidx.core.R.drawable.ic_call_answer,
        iconSelected = androidx.core.R.drawable.ic_call_decline
    )

    data object Favorite : NavigationItem(
        route = "route_favorite",
        name = "Favorite",
        icon = androidx.core.R.drawable.ic_call_answer,
        iconSelected = androidx.core.R.drawable.ic_call_decline
    )

    data object Profile : NavigationItem(
        route = "route_profile",
        name = "Profile",
        icon = androidx.core.R.drawable.ic_call_answer,
        iconSelected = androidx.core.R.drawable.ic_call_decline
    )
}