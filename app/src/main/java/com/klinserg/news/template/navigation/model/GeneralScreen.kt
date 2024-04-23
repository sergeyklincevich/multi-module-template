package com.klinserg.news.ui.navigation.model

sealed class GeneralScreen(val route: String) {

    data object DetailArticle : NavigationPanel(route = "route_news/{articleId}") {
        fun createRoute(articleId: Long) = "route_news/$articleId"
    }

    data object SearchProduct : GeneralScreen(route = "route_news/search")
}