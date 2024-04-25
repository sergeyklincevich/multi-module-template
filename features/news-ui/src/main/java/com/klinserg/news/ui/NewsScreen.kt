package com.klinserg.news.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.klinserg.news.data.models.Article
import com.klinserg.news.ui.viewmodels.NewsViewModel

@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsViewModel = hiltViewModel(),
    navigateToDetail: (Long) -> Unit,
    navigateToSearch: () -> Unit,
) {
    val state by viewModel.state.collectAsState()
    Box(
        modifier = modifier
            .background(Color.Gray)
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Current State: + $state")
            when (val currentState = state) {
                is NewsViewModel.State.None -> Text(text = "State None")
                is NewsViewModel.State.InProgress -> CircularProgressIndicator()
                is NewsViewModel.State.Success -> ListArticles(currentState, navigateToDetail)
                is NewsViewModel.State.Error -> Text(text = "State Error: ${currentState.message}")
            }
        }
    }
}


@Composable
fun ListArticles(state: NewsViewModel.State.Success, navigateToDetail: (Long) -> Unit) {
    LazyColumn {
        items(state.articles) {
            key(it.id) {
                ArticleItem(it, navigateToDetail)
            }
        }
    }
}

@Composable
fun ArticleItem(article: Article, navigateToDetail: (Long) -> Unit) {
    Row(
        Modifier
            .padding(4.dp)
            .clickable { navigateToDetail(article.id) }) {
        article.urlToImage?.let {
            AsyncImage(
                model = it,
                contentDescription = article.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .align(Alignment.CenterVertically),
            )
            Spacer(modifier = Modifier.size(4.dp))
        }
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.headlineMedium,
                maxLines = 1
            )
            Text(
                text = article.description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3
            )
        }
    }

}
