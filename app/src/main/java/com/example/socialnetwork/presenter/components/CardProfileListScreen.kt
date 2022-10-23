package com.example.socialnetwork.presenter.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.socialnetwork.R
import com.example.socialnetwork.domain.model.User
import com.example.socialnetwork.ui.theme.ColorPrimaryDark
import com.example.socialnetwork.ui.theme.ColorTextDark
import com.example.socialnetwork.ui.theme.ColorTextLight

@Composable
fun CardProfileListScreen(
    following: Boolean,
    user: User,
    showFollowAction: Boolean = true,
    followAction: (Long) -> Unit,
    showAction: (Long) -> Unit
) {

    var followUser by remember { mutableStateOf(following) }

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth(),
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .clickable { showAction(user.id ?: 0L) },
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.person_1),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .border(2.dp, ColorPrimaryDark, CircleShape)
                )

                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                ) {
                    Text(
                        text = user.firstName ?: "",
                        color = ColorTextDark,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = user.nickName ?: "",
                        color = ColorTextLight
                    )
                }
            }

            if (showFollowAction) {
                ButtonFollow(
                    modifier = Modifier
                        .align(Alignment.CenterEnd),
                    following = followUser
                ) {
                    followUser = !followUser
                    followAction(user.id ?: 0L)
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CardProfileListScreenPreview() {
    CardProfileListScreen(
        following = true,
        user = User(
            firstName = "Sabrina",
            nickName = "sabrinasantana"
        ),
        followAction = {},
        showAction = {},
    )
}
