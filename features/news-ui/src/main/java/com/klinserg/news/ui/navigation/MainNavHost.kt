package com.klinserg.news.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.klinserg.news.ui.FavoriteScreen
import com.klinserg.news.ui.NewsScreen
import com.klinserg.news.ui.ProfileScreen
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
                navigateToDetail = { productId ->
//                    navController.navigate(GeneralScreen.DetailProduct.createRoute(productId))
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
//        composable(
//            route = GeneralScreen.DetailProduct.route,
//            arguments = listOf(navArgument("productId") { type = NavType.IntType }),
//        ) {
//            val id = it.arguments?.getInt("productId") ?: -1
//            DetailScreen(
//                productId = id,
//                navigateBack = {
//                    navController.navigateUp()
//                },
//            )
//        }
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