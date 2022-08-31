package com.rino.popularmovies.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.rino.popularmovies.BuildConfig
import com.rino.popularmovies.remote.entites.MovieDTO
import com.rino.popularmovies.ui.theme.Grey100
import com.rino.popularmovies.utils.toStringFormat

@Composable
fun TopMoviesScreen(
    viewModel: MainViewModel,
    onNavigateToMovieDetails: (Long) -> Unit
) {
    Column {
        TitleSection(modifier = Modifier.background(Grey100))
        TopMoviesListSection(
            viewModel = viewModel,
            onNavigateToMovieDetails = onNavigateToMovieDetails
        )
    }
}

@Composable
fun TitleSection(modifier: Modifier = Modifier) {
    Text(
        text = "Top Movies",
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun TopMoviesListSection(
    viewModel: MainViewModel,
    onNavigateToMovieDetails: (Long) -> Unit
) {
    val moviesList = viewModel.moviesPager.collectAsLazyPagingItems()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(moviesList.itemCount) {
            moviesList[it]?.let { item ->
                TopMoviesItem(
                    item = item,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { onNavigateToMovieDetails(item.id) }
                )
            }
        }
    }

    when (moviesList.loadState.refresh) {
        is LoadState.Error -> Unit
        LoadState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is LoadState.NotLoading -> Unit
    }
}

@Composable
fun TopMoviesItem(item: MovieDTO, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            val imageUrl =
                "${BuildConfig.IMAGE_TMDB_BASE_URL}${BuildConfig.IMAGE_TMDB_RELATIVE_PATH}${item.posterPath}"
            val painter = rememberAsyncImagePainter(model = imageUrl)

            Image(
                painter = painter,
                contentDescription = item.title,
                modifier = Modifier
                    .wrapContentWidth()
                    .height(260.dp)
                    .background(Color.LightGray)
                    .padding(4.dp),
                alignment = Alignment.Center
            )

            when (painter.state) {
                is AsyncImagePainter.State.Loading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
                is AsyncImagePainter.State.Error -> {}
                else -> {}
            }
        }

        CircularProgressBar(
            modifier = Modifier.offset(15.dp, (-25).dp),
            percentage = item.voteAverage.toFloat() / 10f,
        )

        Text(
            text = item.title,
            modifier = Modifier
                .padding(horizontal = 4.dp)
        )
        Text(
            text = item.releaseDate.toStringFormat("dd MMM yyyy"),
            modifier = Modifier
                .padding(horizontal = 4.dp),
            fontSize = 12.sp,
            color = Color.Gray,
        )
    }
}