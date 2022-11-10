package com.example.socialnetwork.presenter.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.sp
import com.example.socialnetwork.R
import com.example.socialnetwork.domain.model.User
import com.example.socialnetwork.ui.theme.ColorPrimaryDark
import com.example.socialnetwork.ui.theme.ColorPrimaryLight
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

    var followUser by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(8.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Card(
            modifier = Modifier
                .padding(top = 32.dp)
                .wrapContentWidth(),
            shape = RoundedCornerShape(20.dp),
            backgroundColor = Color(0xFF7067DA).copy(alpha = 0.1f),
            elevation = 0.dp
        ) {

            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 50.dp)
                    .wrapContentWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 16.dp),
                    text = user.firstName ?: "",
                    color = ColorTextDark,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier
                        .padding(vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(
                        modifier = Modifier
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "Publicações",
                            color = Color(0xFF9FA0B9)
                        )

                        Text(
                            text = "14",
                            color = ColorTextDark,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Column(
                        modifier = Modifier
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Seguidores",
                            color = Color(0xFF9FA0B9)
                        )

                        Text(
                            text = "1000",
                            color = ColorTextDark,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Column(
                        modifier = Modifier
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Seguindo",
                            color = Color(0xFF9FA0B9)
                        )

                        Text(
                            text = "256",
                            color = ColorTextDark,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }

                if (showFollowAction) {
                    ButtonOutline(
                        modifier = Modifier
                            .fillMaxWidth(),
                        following = following
                    ) {
                        followUser = !followUser
                        followAction(user.id ?: 0L)
                    }
                }
            }

        }

        Image(
            painter = painterResource(id = R.drawable.person_1),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .border(2.dp, Color.White, CircleShape)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CardProfileListScreenPreview() {
    CardProfileListScreen(
        following = true,
        user = User(
            firstName = "Sabrina Santana",
            nickName = "sabrinasantana"
        ),
        followAction = {},
        showAction = {},
    )
}
