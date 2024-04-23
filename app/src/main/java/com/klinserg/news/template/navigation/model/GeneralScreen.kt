package com.klinserg.news.template.navigation.model

sealed class GeneralScreen(val route: String) {

    data object DetailArticle : GeneralScreen(route = "route_news/{articleId}") {
        fun createRoute(articleId: Long) = "route_news/$articleId"
    }

    data object SearchProduct : GeneralScreen(route = "route_news/search")
}