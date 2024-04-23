package com.klinserg.news.template.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.klinserg.news.bluetooth.BluetoothScreen
import com.klinserg.news.news_details.DetailScreen
import com.klinserg.news.news_search.SearchScreen
import com.klinserg.news.template.navigation.model.GeneralScreen
import com.klinserg.news.template.navigation.model.NavigationPanel
import com.klinserg.news.news_favorite.FavoriteScreen
import com.klinserg.news.ui.NewsScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = NavigationPanel.News.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(NavigationPanel.News.route) {
            NewsScreen(
                navigateToDetail = { articleId ->
                    navController.navigate(GeneralScreen.DetailArticle.createRoute(articleId))
                },
                navigateToSearch = {
                    navController.navigate(GeneralScreen.SearchProduct.route)
                }
            )
        }
        composable(NavigationPanel.Favorite.route) {
            com.klinserg.news.news_favorite.FavoriteScreen(
                navigateToDetail = { articleId ->
                    navController.navigate(GeneralScreen.DetailArticle.createRoute(articleId))
                }
            )
        }
        composable(NavigationPanel.Profile.route) {
            BluetoothScreen()
        }
        composable(
            route = GeneralScreen.DetailArticle.route,
            arguments = listOf(navArgument("articleId") { type = NavType.IntType }),
        ) {
            val id = it.arguments?.getInt("articleId") ?: -1
            DetailScreen(
                articleId = id,
                navigateBack = {
                    navController.navigateUp()
                },
            )
        }
        composable(
            route = GeneralScreen.SearchProduct.route,
        ) {
            SearchScreen(
                navigateToDetail = { articleId ->
                    navController.navigate(GeneralScreen.DetailArticle.createRoute(articleId))
                },
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}

@Composable
fun PaddingValues.copy(
    start: Dp? = null,
    top: Dp? = null,
    end: Dp? = null,
    bottom: Dp? = null,
): PaddingValues {
    val layoutDirection = LocalLayoutDirection.current
    return PaddingValues(
        start = start ?: this.calculateStartPadding(layoutDirection),
        top = top ?: this.calculateTopPadding(),
        end = end ?: this.calculateEndPadding(layoutDirection),
        bottom = bottom ?: this.calculateBottomPadding(),
    )
}