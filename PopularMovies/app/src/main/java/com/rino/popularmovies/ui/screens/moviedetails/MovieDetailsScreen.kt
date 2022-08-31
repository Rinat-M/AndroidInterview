package com.rino.popularmovies.ui.screens.moviedetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.rino.popularmovies.BuildConfig
import com.rino.popularmovies.remote.entites.MovieDetailsDTO
import com.rino.popularmovies.ui.screens.main.CircularProgressBar
import com.rino.popularmovies.ui.theme.BlackWithOpacity80
import com.rino.popularmovies.utils.toStringFormat
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

const val MOVIE_ID_ARG = "movieId"

@Composable
fun MovieDetailsScreen(movieId: String) {
    val detailsViewModel = getViewModel<MovieDetailsViewModel> { parametersOf(movieId) }

    Column(modifier = Modifier.fillMaxSize()) {
        detailsViewModel.movieDetails?.let {
            PosterSection(it)
            OverviewSection(it)
        }
    }

    ProgressIndicator(detailsViewModel.isLoaded)
}

@Composable
fun PosterSection(movieDetails: MovieDetailsDTO) {
    Box(modifier = Modifier.fillMaxWidth()) {
        val backDropImageUrl =
            "${BuildConfig.IMAGE_TMDB_BASE_URL}${BuildConfig.IMAGE_TMDB_RELATIVE_PATH}${movieDetails.backdropPath}"
        val posterImageUrl =
            "${BuildConfig.IMAGE_TMDB_BASE_URL}${BuildConfig.IMAGE_TMDB_RELATIVE_PATH}${movieDetails.posterPath}"

        val backDropPainter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(backDropImageUrl)
                .crossfade(true)
                .build()
        )

        val posterPainter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(posterImageUrl)
                .crossfade(true)
                .build()
        )

        Image(
            painter = backDropPainter,
            contentDescription = movieDetails.title,
            modifier = Modifier
                .fillMaxWidth(),
            alignment = Alignment.Center
        )

        when (backDropPainter.state) {
            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator(
                    Modifier.align(Alignment.Center)
                )
            }
            is AsyncImagePainter.State.Error -> {}
            else -> {}
        }

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(BlackWithOpacity80)
        )

        Text(
            text = movieDetails.title,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Row(
            modifier = Modifier.align(Alignment.BottomCenter),
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp))
            ) {
                Image(
                    painter = posterPainter,
                    contentDescription = null,
                    modifier = Modifier
                        .height(140.dp)
                        .offset(0.dp, 10.dp)
                        .background(Color.LightGray)
                        .padding(2.dp),
                    alignment = Alignment.Center
                )

                when (posterPainter.state) {
                    is AsyncImagePainter.State.Loading -> {
                        CircularProgressIndicator(
                            Modifier.align(Alignment.Center)
                        )
                    }
                    is AsyncImagePainter.State.Error -> {}
                    else -> {}
                }
            }
            Column(
                modifier = Modifier.weight(2f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Released: ${movieDetails.releaseDate.toStringFormat("dd MMM yyyy")}",
                    modifier = Modifier.wrapContentSize(),
                    color = Color.White,
                    fontSize = 14.sp
                )
                Text(
                    text = "Revenue: ${movieDetails.revenue}",
                    modifier = Modifier.wrapContentSize(),
                    color = Color.White,
                    fontSize = 14.sp
                )
                Text(
                    text = "Runtime: ${movieDetails.runtime} min.",
                    modifier = Modifier.wrapContentSize(),
                    color = Color.White,
                    fontSize = 14.sp
                )
                Text(
                    text = movieDetails.genreIds.joinToString { genre -> genre.name },
                    modifier = Modifier.wrapContentSize(),
                    color = Color.White,
                    fontSize = 14.sp
                )
                CircularProgressBar(
                    percentage = movieDetails.voteAverage.toFloat() / 10f,
                )
            }
        }
    }
}

@Composable
fun OverviewSection(movieDetails: MovieDetailsDTO) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Overview",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Text(
            text = movieDetails.overview,
        )
    }
}

@Composable
private fun ProgressIndicator(loadingIsComplete: Boolean, modifier: Modifier = Modifier) {
    if (!loadingIsComplete) {
        AnimatedVisibility(
            modifier = modifier.fillMaxSize(),
            visible = !loadingIsComplete,
            enter = EnterTransition.None,
            exit = fadeOut()
        ) {
            CircularProgressIndicator(
                modifier = modifier
                    .background(MaterialTheme.colors.background)
                    .wrapContentSize()
            )
        }
    }
}