package com.rino.redditinfinitylistapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.rino.redditinfinitylistapp.R
import com.rino.redditinfinitylistapp.remote.entities.PostDataDTO
import com.rino.redditinfinitylistapp.ui.theme.Orange900

@Composable
fun HotPostsScreen(mainViewModel: MainViewModel) {
    val posts = mainViewModel.hotPostsPager.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(posts) { post ->
            post?.let {
                HotPostsItem(item = it.data)
            }
            Divider(
                color = Color.Gray, thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }

    when (posts.loadState.refresh) {
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
fun HotPostsItem(
    item: PostDataDTO
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row {
            Box(
                modifier = Modifier
                    .size(50.dp),
                contentAlignment = Alignment.Center
            ) {
                val painter = rememberAsyncImagePainter(model = item.thumbnail)
                Image(
                    painter = painter,
                    contentDescription = item.title,
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(50.dp)
                        .background(Color.LightGray)
                        .padding(2.dp),
                    alignment = Alignment.Center
                )

                when (painter.state) {
                    is AsyncImagePainter.State.Loading -> {
                        CircularProgressIndicator(
                            Modifier
                                .size(25.dp)
                                .align(Alignment.Center))
                    }
                    is AsyncImagePainter.State.Error -> {}
                    else -> {}
                }
            }
            Text(
                text = item.title,
                modifier = Modifier.padding(start = 8.dp, bottom = 16.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star_border),
                contentDescription = "scores",
                tint = Orange900,
                modifier = Modifier
                    .size(30.dp)
                    .padding(end = 8.dp)
            )
            Text(text = item.score.toString())
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.ic_comment),
                contentDescription = "comments",
                tint = Color.Blue,
                modifier = Modifier
                    .size(30.dp)
                    .padding(end = 8.dp)
            )
            Text(
                text = item.comments.toString(),
                modifier = Modifier.padding(end = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HotPostsItemPreview() {
    val item = PostDataDTO(
        title = "r/aww has a Discord server",
        name = "t3_v9axko",
        score = 495,
        thumbnail = "https://b.thumbs.redditmedia.com/_x02HPa2oUvrRelM1gMoc4GsHuJz2iQ0cH27_RU_BJk.jpg",
        id = "v9axko",
        comments = 111
    )
    HotPostsItem(item = item)
}