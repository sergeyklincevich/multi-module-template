package com.klinserg.news.news_details

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.klinserg.news.data.models.Article
import com.klinserg.news.news_details.viewmodels.DetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    articleId: String,
    viewModel: DetailsViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    val state by viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.detail_article))
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
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    titleContentColor = Color.White,
                )
            )
        }, content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray)
                    .padding(it)
            ) {
                when (val value = state) {
                    is DetailsViewModel.State.None -> Text(text = "Status NONE for ArticleId: $articleId")
                    is DetailsViewModel.State.InProgress -> CircularProgressIndicator()
                    is DetailsViewModel.State.Success -> ArticleInfo(article = value.article)
                    is DetailsViewModel.State.Error -> Text(text = "Status Error for ArticleId: $articleId, Error: ${value.message}")
                }
            }
        })
}

@Composable
fun ArticleInfo(article: Article) {
    Column {
        article.urlToImage?.let {
            AsyncImage(
                model = it,
                contentDescription = article.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.heightIn(max = 400.dp)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = article.title,
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                text = article.description,
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                modifier = Modifier
                    .padding(top = 48.dp)
                    .align(Alignment.End),
                text = article.publishedAt.toString(),
                style = MaterialTheme.typography.bodySmall,
            )
            val context = LocalContext.current
            Button(onClick = {
                Toast.makeText(
                    context,
                    "on Simple button click",
                    Toast.LENGTH_SHORT
                ).show()
            }) {
                Text("Simple Button")
            }
            FilledTonalButton(onClick = {
                Toast.makeText(
                    context,
                    "on Simple button click",
                    Toast.LENGTH_SHORT
                ).show()
            }) {
                Text("Filled Tonal Button")
            }
            OutlinedButton(onClick = {
                Toast.makeText(
                    context,
                    "on Simple button click",
                    Toast.LENGTH_SHORT
                ).show()
            }) {
                Text("Outlined Button")
            }
            ElevatedButton(onClick = {
                Toast.makeText(
                    context,
                    "on Simple button click",
                    Toast.LENGTH_SHORT
                ).show()
            }) {
                Text("Elevated Button")
            }
            TextButton(onClick = {
                Toast.makeText(
                    context,
                    "on Simple button click",
                    Toast.LENGTH_SHORT
                ).show()
            }) {
                Text("Text Button")
            }

        }
    }

}
