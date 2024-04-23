package com.klinserg.news.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
//    viewModel: DetailViewModel = hiltViewModel(),
    navigateToDetail: (Long) -> Unit,
    navigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.search_article))
                },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back Icon",
                            tint = Color.White,
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                )
            )
        }, content = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray)
                    .padding(it)
            ) {
                Text(text = "Welcome on Search Article Screen")
//                viewModel.uiStateProduct.collectAsState(initial = UiState.Loading).value.let { uiState ->
//                    when (uiState) {
//                        is UiState.Loading -> {
//                            viewModel.getProductByIdApiCall(productId)
//                            ProgressProduct()
//                        }
//
//                        is UiState.Success -> {
//                            DetailContent(product = uiState.data, viewModel = viewModel)
//                        }
//
//                        is UiState.Error -> {
//                            Text(text = stringResource(R.string.error_product), color = MaterialTheme.colorScheme.onSurface)
//                        }
//                    }
//                }
            }
        })
}