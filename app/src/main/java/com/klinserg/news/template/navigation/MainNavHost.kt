package com.klinserg.news.ui.navigation

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
import com.klinserg.news.ui.DetailScreen
import com.klinserg.news.ui.FavoriteScreen
import com.klinserg.news.ui.NewsScreen
import com.klinserg.news.ui.ProfileScreen
import com.klinserg.news.ui.SearchScreen
import com.klinserg.news.ui.navigation.model.GeneralScreen
import com.klinserg.news.ui.navigation.model.NavigationPanel

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
            FavoriteScreen(
                navigateToDetail = { articleId ->
                    navController.navigate(GeneralScreen.DetailArticle.createRoute(articleId))
                }
            )
        }
        composable(NavigationPanel.Profile.route) {
            ProfileScreen()
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