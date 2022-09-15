package com.rino.redditinfinitylistapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rino.redditinfinitylistapp.R
import com.rino.redditinfinitylistapp.remote.entities.PostDataDTO
import com.rino.redditinfinitylistapp.ui.theme.Orange900

@Composable
fun HotPostsScreen(mainViewModel: MainViewModel) {

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
        Text(
            text = item.title,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star_border),
                contentDescription = "scores",
                tint = Orange900,
                modifier = Modifier
                    .size(30.dp)
                    .padding(end = 4.dp)
            )
            Text(text = item.score.toString())
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.ic_comment),
                contentDescription = "comments",
                tint = Color.Blue,
                modifier = Modifier
                    .size(30.dp)
                    .padding(end = 4.dp)
            )
            Text(text = item.comments.toString())
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