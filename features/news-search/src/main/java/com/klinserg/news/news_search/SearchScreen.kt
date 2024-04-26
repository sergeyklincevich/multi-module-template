package com.klinserg.news.news_search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.klinserg.news.news_search.viewmodels.SearchNewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchNewsViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit,
) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(text = stringResource(R.string.search_article))
//                },
//                navigationIcon = {
//                    IconButton(onClick = { navigateBack() }) {
//                        Icon(
//                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                            contentDescription = "Back Icon",
//                            tint = Color.White,
//                        )
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primary,
//                    titleContentColor = Color.White,
//                )
//            )
//        }, content = {
//            Box(
//                contentAlignment = Alignment.Center,
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color.Gray)
//                    .padding(it)
//            ) {
//                Text(text = "Welcome on Search Article Screen")
//            }
//        })
    val searchText by viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    val newsList by viewModel.newsList.collectAsState()

    Scaffold(
        topBar = {
            SearchBar(
                query = searchText,
                onQueryChange = viewModel::onSearchTextChange,
                onSearch = viewModel::onSearchTextChange,
                active = isSearching,
                onActiveChange = { viewModel.onToogleSearch() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                LazyColumn {
                    items(newsList) { news ->
                        Text(
                            text = news.title,
                            modifier = Modifier.padding(
                                start = 8.dp,
                                top = 4.dp,
                                end = 8.dp,
                                bottom = 4.dp
                            )
                        )
                    }
                }
            }
        }
    ) {
        Text("-----------", modifier = Modifier.padding(it))
    }
}