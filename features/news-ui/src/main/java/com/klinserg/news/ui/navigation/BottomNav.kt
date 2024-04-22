package com.klinserg.news.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.klinserg.news.ui.navigation.model.NavigationItem
import com.klinserg.news.ui.navigation.model.NavigationPanel
import com.klinserg.news.uikit.theme.DividerApp

@Composable
fun BottomNav(
    modifier: Modifier = Modifier,
    navigationItemList: List<NavigationItem>,
    navController: NavHostController = rememberNavController(),
    currentDestination: NavDestination?,
) {
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
//        contentWindowInsets = WindowInsets(0.dp),
        bottomBar = {
            // show and hide bottom navigation
            currentDestination?.let {
                if (it.route == NavigationPanel.News.route ||
                    it.route == NavigationPanel.Favorite.route ||
                    it.route == NavigationPanel.Profile.route
                ) {
                    BottomBar(
                        modifier = modifier,
                        navController = navController,
                        navigationItemList = navigationItemList,
                        currentDestination = currentDestination
                    )
                }
            }
        },
    ) {
        MainNavHost(navController = navController, innerPadding = it)
    }
}

@Composable
fun BottomBar(
    modifier: Modifier,
    navController: NavHostController,
    navigationItemList: List<NavigationItem>,
    currentDestination: NavDestination,
) {
    Column {
        HorizontalDivider(color = DividerApp)
        Row(
            modifier = modifier
                .padding(start = 0.dp, end = 0.dp, top = 10.dp, bottom = 20.dp)
                .background(Color.Transparent)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            navigationItemList.forEach { screen ->
                BottomNavItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun BottomNavItem(
    screen: NavigationItem,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
//    val background =
//        if (selected) MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.1f)
//        else Color.Transparent
    val contentColor =
        if (selected) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.secondary

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
//            .background(background)
            .clickable(onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                painter = painterResource(id = if (selected) screen.iconSelected else screen.icon),
                contentDescription = screen.name,
                tint = contentColor
            )
            Text(text = screen.name, color = contentColor)
        }
    }
}