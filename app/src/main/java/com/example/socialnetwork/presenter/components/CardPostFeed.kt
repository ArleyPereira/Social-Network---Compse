package com.example.socialnetwork.presenter.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.socialnetwork.R
import com.example.socialnetwork.domain.model.Post
import com.example.socialnetwork.domain.model.User
import com.example.socialnetwork.presenter.bottombar.feed.event.FeedEvent
import com.example.socialnetwork.ui.theme.ColorBackgroundApp
import com.example.socialnetwork.ui.theme.ColorPrimaryLight
import com.example.socialnetwork.ui.theme.ColorTextHint
import com.example.socialnetwork.util.getPrettyPastTime

@Composable
fun CardPostFeed(
    modifier: Modifier = Modifier,
    post: Post,
    onInteraction: (FeedEvent) -> Unit
) {
    var isLiked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
            .padding(horizontal = 0.dp)
            .background(ColorBackgroundApp)
    ) {

        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = 0.dp,
            backgroundColor = White
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
            ) {

                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(post.user?.avatar)
                                .crossfade(true)
                                .build(),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(10.dp))
                                .size(40.dp)
                                .clickable {
                                    onInteraction(
                                        FeedEvent.NavProfileUserScreen(
                                            post.userId ?: 0L
                                        )
                                    )
                                }
                        )

                        Column(
                            modifier = Modifier
                                .padding(start = 16.dp)
                        ) {
                            Text(
                                text = post.user?.firstName ?: "",
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = post.createdAt?.getPrettyPastTime() ?: "",
                                fontSize = 12.sp,
                                color = ColorTextHint
                            )
                        }
                    }

                    Image(
                        painter = painterResource(id = R.drawable.ic_more),
                        contentDescription = "",
                        modifier = modifier.size(24.dp)
                    )

                }

                Text(
                    text = post.content ?: "",
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(post.photos[0].file)
                        .crossfade(true)
                        .build(),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                        .clip(RoundedCornerShape(10.dp))
                )

                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                ) {
                    CardPostActionComment(
                        interactions = 256,
                    ) {
                        onInteraction(
                            FeedEvent.NavPostCommentScreen(
                                post.id ?: 0L
                            )
                        )
                    }

                    CardPostActionLike(
                        interactions = 500,
                        isLiked = isLiked,
                        onInteraction = { isLiked = !isLiked },
                        onLikeDetails = {
                            onInteraction(
                                FeedEvent.NavPostLikeDetailsScreen(
                                    post.id ?: 0L
                                )
                            )
                        }
                    )
                }

            }
        }
    }
}

@Composable
fun CardPostActionComment(
    modifier: Modifier = Modifier,
    interactions: Int,
    onInteraction: () -> Unit
) {

    Row(
        modifier = modifier
            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val text = if (interactions >= 1000) {
            "${interactions.toFloat() / 1000}" + "K"
        } else {
            interactions.toString()
        }

        Image(
            painter = painterResource(id = R.drawable.ic_comment_line),
            contentDescription = text,
            modifier = Modifier.clickable {
                onInteraction()
            }
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = text,
            modifier = Modifier.clickable {
                onInteraction()
            },
            fontSize = 14.sp
        )

    }

}

@Composable
fun CardPostActionLike(
    modifier: Modifier = Modifier,
    interactions: Int,
    isLiked: Boolean = false,
    onInteraction: () -> Unit,
    onLikeDetails: () -> Unit
) {

    val isLikedIcon = if (isLiked) {
        R.drawable.ic_heart_fill
    } else {
        R.drawable.ic_heart_line
    }

    Row(
        modifier = modifier
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val text = if (interactions >= 1000) {
            "${interactions.toFloat() / 1000}" + "K"
        } else {
            interactions.toString()
        }

        Image(
            painter = painterResource(id = isLikedIcon),
            contentDescription = text,
            modifier = Modifier
                .clickable { onInteraction() }
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = text,
            modifier = Modifier
                .clickable { onLikeDetails() },
            fontSize = 14.sp
        )

    }

}

@Composable
fun CardPostActionShared(
    modifier: Modifier = Modifier,
    interactions: Int,
    onInteraction: () -> Unit
) {

    Row(
        modifier = modifier
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val text = if (interactions >= 1000) {
            "${interactions.toFloat() / 1000}" + "K"
        } else {
            interactions.toString()
        }

        Image(
            painter = painterResource(id = R.drawable.ic_share_line),
            contentDescription = text,
            modifier = Modifier.clickable {
                onInteraction()
            }
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(text = text)

    }

}

@Preview
@Composable
fun CardPostFeedPreview() {
    val postPreview = Post(
        user = User(
            firstName = "Sabrina Santana",
        ),
        content = "O que não falta nesse mundo são lugares belíssimos para se visitar, afinal, ele é imenso, não é mesmo?"
    )

    CardPostFeed(
        post = postPreview,
        onInteraction = {}
    )
}