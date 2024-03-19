package com.klinserg.news.ui.navigation.drawernav.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.klinserg.news.ui.navigation.model.NavigationItem

@Composable
fun NavDrawerSection(
    modifier: Modifier = Modifier,
    navigationItemList: List<NavigationItem>,
    navController: NavHostController,
    currentDestination: NavDestination?,
    onDrawerAction: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary),
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
                .padding(32.dp),
        ) {
            Spacer(modifier = modifier.height(16.dp))
            NavDrawerHeader(modifier = modifier)
            Spacer(modifier = modifier.height(16.dp))
            Divider(
                thickness = 1.dp,
                color = Color.White.copy(alpha = 0.5f),
            )
            Spacer(modifier = modifier.height(16.dp))
            NavDrawerMenu(
                navigationItemContentList = navigationItemList,
                navController = navController,
                currentDestination = currentDestination,
                onDrawerAction = onDrawerAction,
            )
        }
    }
}