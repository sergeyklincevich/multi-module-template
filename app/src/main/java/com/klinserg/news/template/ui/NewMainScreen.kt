package com.klinserg.news.template.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.klinserg.news.template.utils.NewsNavigationType
import com.klinserg.news.ui.navigation.BottomNav
import com.klinserg.news.ui.navigation.drawernav.DrawerNav
import com.klinserg.news.ui.navigation.RailNav
import com.klinserg.news.ui.navigation.model.NavigationPanel

@Composable
fun NewMainScreen(
    navigationType: NewsNavigationType,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val navigationItemList =
        listOf(NavigationPanel.News, NavigationPanel.Favorite, NavigationPanel.Profile)

    when (navigationType) {
        NewsNavigationType.BOTTOM_NAVIGATION -> BottomNav(
            modifier,
            navigationItemList,
            navController,
            currentDestination
        )

        NewsNavigationType.NAVIGATION_RAIL -> RailNav(
            modifier,
            navigationItemList,
            navController,
            currentDestination
        )

        NewsNavigationType.PERMANENT_NAVIGATION_DRAWER -> DrawerNav(
            modifier,
            navigationItemList,
            navController,
            currentDestination
        )
    }
}