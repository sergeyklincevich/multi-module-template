package com.klinserg.news.template.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.klinserg.news.template.navigation.model.NavigationItem

@Composable
fun RailNav(
    modifier: Modifier = Modifier,
    navigationItemList: List<NavigationItem>,
    navController: NavHostController = rememberNavController(),
    currentDestination: NavDestination?,
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        LazyColumn(
            modifier = modifier.fillMaxHeight(),
            contentPadding = PaddingValues(12.dp)
        ) {
            items(navigationItemList) { screen ->
                NavRailItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController,
                    modifier = modifier,
                )
                Spacer(modifier = modifier.height(24.dp))
            }
        }
        MainNavHost(navController = navController, innerPadding = PaddingValues(end = 0.dp))
    }
}

@Composable
fun NavRailItem(
    screen: NavigationItem,
    currentDestination: NavDestination?,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
    val background =
        if (selected) MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.1f)
        else Color.Transparent
    val contentColor =
        if (selected) MaterialTheme.colorScheme.background
        else MaterialTheme.colorScheme.background.copy(alpha = 0.4f)

    Box(
        modifier = modifier
            .height(42.dp)
            .width(42.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(background)
            .clickable(onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
    ) {
        Row(
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            Icon(
                painter = painterResource(id = if (selected) screen.iconSelected else screen.icon),
                contentDescription = screen.name,
                tint = contentColor
            )
        }
    }
}