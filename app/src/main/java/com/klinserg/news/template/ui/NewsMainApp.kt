package com.klinserg.news.template.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.klinserg.news.template.utils.NewsNavigationType

@Composable
fun NewsMainApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
) {
    val navigationType = when (windowSize) {
        WindowWidthSizeClass.Compact -> NewsNavigationType.BOTTOM_NAVIGATION
        WindowWidthSizeClass.Medium -> NewsNavigationType.NAVIGATION_RAIL
        WindowWidthSizeClass.Expanded -> NewsNavigationType.PERMANENT_NAVIGATION_DRAWER
        else -> NewsNavigationType.BOTTOM_NAVIGATION
    }

    NewMainScreen(navigationType, modifier)
}

