package com.klinserg.news.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.klinserg.news.data.models.Article
import com.klinserg.news.ui.viewmodels.NewsViewModel

@Composable
fun HomeScreen() {
    NewsMain(hiltViewModel())
}

@Composable
internal fun NewsMain(viewModel: NewsViewModel) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Current State: + $state")
        when (val currentState = state) {
            is NewsViewModel.State.None -> Text(text = "State None")
            is NewsViewModel.State.InProgress -> CircularProgressIndicator()
            is NewsViewModel.State.Success -> ListArticles(currentState)
            is NewsViewModel.State.Error -> Text(text = "State Error: ${currentState.message}")
        }
    }
}


@Composable
fun ListArticles(state: NewsViewModel.State.Success) {
    LazyColumn {
        items(state.articles) {
            key(it.id) {
                ArticleItem(it)
            }
        }
    }
}

@Composable
fun ArticleItem(article: Article) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = article.title, style = MaterialTheme.typography.headlineMedium, maxLines = 1)
        Text(
            text = article.description,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 3
        )
    }

}
