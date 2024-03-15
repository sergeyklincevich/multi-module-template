package com.klinserg.news.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.klinserg.news.ui.navigation.model.NavigationItem

@Composable
fun DrawerNav(
    modifier: Modifier = Modifier,
    navigationItemList: List<NavigationItem>,
    navHostController: NavHostController = rememberNavController(),
    currentDestination: NavDestination?,
) {

}