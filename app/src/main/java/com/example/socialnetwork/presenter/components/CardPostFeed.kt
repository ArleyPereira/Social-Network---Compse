package com.example.socialnetwork.presenter.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.socialnetwork.R
import com.example.socialnetwork.data.model.Post
import com.example.socialnetwork.ui.theme.ColorBackgroundApp

@Composable
fun CardPostFeed(
    modifier: Modifier = Modifier,
    post: Post
) {
    var isLiked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
            .background(ColorBackgroundApp)
    ) {

        Card(
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
                        Image(
                            painter = painterResource(id = R.drawable.person_1),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(10.dp))
                                .size(60.dp)
                        )

                        Column(
                            modifier = Modifier
                                .padding(start = 16.dp)
                        ) {
                            Text(text = post.nameProfile!!)
                            Text(text = "Há 15 min")
                        }
                    }

                    Image(
                        painter = painterResource(id = R.drawable.ic_more),
                        contentDescription = "",
                        modifier = modifier.size(24.dp)
                    )

                }

                Text(
                    text = post.description!!,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.image_feed_place_holder),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                        .shadow(
                            elevation = 5.dp,
                            shape = RoundedCornerShape(10.dp)
                        )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CardPostActionComment(
                        interactions = 256,
                    ) {

                    }
                    CardPostActionShared(
                        interactions = 2800,
                    ) {

                    }
                    CardPostActionLike(
                        interactions = 500,
                        isLiked = isLiked
                    ) {
                        isLiked = !isLiked
                    }
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
            .padding(16.dp),
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

        Spacer(modifier = Modifier.width(10.dp))

        Text(text = text)

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

@Composable
fun CardPostActionLike(
    modifier: Modifier = Modifier,
    interactions: Int,
    isLiked: Boolean = false,
    onInteraction: () -> Unit
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
        nameProfile = "Sabrina Santana",
        description = "O que não falta nesse mundo são lugares belíssimos para se visitar, afinal, ele é imenso, não é mesmo?"
    )

    CardPostFeed(post = postPreview)
}