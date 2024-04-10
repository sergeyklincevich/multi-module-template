package com.klinserg.news.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.klinserg.news.ui.DetailScreen
import com.klinserg.news.ui.FavoriteScreen
import com.klinserg.news.ui.NewsScreen
import com.klinserg.news.ui.ProfileScreen
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
//                    navController.navigate(GeneralScreen.SearchProduct.route)
                }
            )
        }
        composable(NavigationPanel.Favorite.route) {
            FavoriteScreen(
//                navigateToDetail = { productId ->
//                    navController.navigate(GeneralScreen.DetailProduct.createRoute(productId))
//                }
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
//        composable(
//            route = GeneralScreen.SearchProduct.route,
//        ) {
//            SearchScreen(
//                navigateToDetail = { productId ->
//                    navController.navigate(GeneralScreen.DetailProduct.createRoute(productId))
//                },
//                navigateBack = {
//                    navController.navigateUp()
//                }
//            )
//        }
    }
}