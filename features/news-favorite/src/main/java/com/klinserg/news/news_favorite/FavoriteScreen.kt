package com.klinserg.news.news_favorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.klinserg.news.uikit.theme.GrayApp
import com.klinserg.news.uikit.theme.PrimaryApp

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    navigateToDetail: (Long) -> Unit,
) {
    var enabled by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Black)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 55.dp)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 64.dp, bottomEnd = 64.dp)),
                    painter = painterResource(id = R.drawable.bg_header_home),
                    contentScale = ContentScale.Crop,
                    contentDescription = "background"
                )
            }
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .align(Alignment.BottomCenter)
                    .background(color = Color.Black, shape = CircleShape)
                    .shadow(
                        elevation = 40.dp,
                        shape = CircleShape,
                        ambientColor = Color.Black,
                        spotColor = Color.White
                    )
                    .padding(8.dp)
                    .border(
                        width = 1.dp,
                        color = Color.White.copy(alpha = 0.16f),
                        shape = CircleShape
                    )
                    .background(color = Color.Black, shape = CircleShape)
                    .padding(8.dp)
                    .border(
                        width = 1.dp,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Black,
                                Color.White.copy(alpha = 0.05f)
                            )
                        ),
                        shape = CircleShape
                    )
                    .background(
                        brush = Brush.verticalGradient(colors = listOf(Color.Black, GrayApp)),
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .clickable { enabled = !enabled }

            )
            {
                Image(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(12.dp),
                    painter = painterResource(id = R.drawable.ic_on_off),
                    contentScale = ContentScale.Inside,
                    contentDescription = "play button",
                    colorFilter = ColorFilter.tint(if (enabled) PrimaryApp else MaterialTheme.colorScheme.onPrimary),
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun modalBottomSheet() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    Scaffold(

        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Show bottom sheet") },
                icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                onClick = {
                    showBottomSheet = true
                }
            )
        }
    ) { contentPadding ->
        // Screen content
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Transparent, shape = RoundedCornerShape(16.dp)),
            painter = painterResource(id = R.drawable.bg_header_home),
            contentDescription = "background"
        )

        if (showBottomSheet) {
            ModalBottomSheet(
                modifier = Modifier.padding(contentPadding),
                containerColor = Color.Transparent,
                dragHandle = null,

                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                CustomButton(title = "Button 1")
                CustomButton(title = "Button 2")
                CustomButton(title = "Button 3")
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

@Composable
private fun CustomButton(title: String) {
    Button(modifier = Modifier
        .padding(horizontal = 16.dp)
        .fillMaxWidth(), onClick = {}) {
        Text(title)
    }
}
