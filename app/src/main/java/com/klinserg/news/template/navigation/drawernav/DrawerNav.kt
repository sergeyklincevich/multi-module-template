package com.klinserg.news.ui.navigation.drawernav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.klinserg.news.ui.navigation.MainNavHost
import com.klinserg.news.ui.navigation.drawernav.sections.NavDrawerSection
import com.klinserg.news.ui.navigation.model.NavigationItem
import kotlinx.coroutines.launch

@Composable
fun DrawerNav(
    modifier: Modifier = Modifier,
    navigationItemList: List<NavigationItem>,
    navController: NavHostController = rememberNavController(),
    currentDestination: NavDestination?,
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = RectangleShape
            ) {
                NavDrawerSection(
                    modifier = modifier,
                    navigationItemList = navigationItemList,
                    navController = navController,
                    currentDestination = currentDestination,
                    onDrawerAction = {
                        coroutineScope.launch {
                            if (drawerState.isOpen) drawerState.close() else drawerState.open()
                        }
                    },
                )
            }
        }
    ) {
        MainNavHost(navController = navController, innerPadding = PaddingValues(end = 0.dp))
    }

}