package com.example.socialnetwork.presenter.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.socialnetwork.R
import com.example.socialnetwork.ui.theme.ColorBackgroundApp
import com.example.socialnetwork.ui.theme.ColorPrimaryDark
import com.example.socialnetwork.ui.theme.ColorTextLight

@Composable
fun CardProfileListScreen(
    following: Boolean,
    profileName: String,
    nickName: String,
    followAction: (Boolean) -> Unit,
    showAction: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(100.dp),
        backgroundColor = ColorBackgroundApp,
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .clickable { showAction() },
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
                    Text(text = profileName)
                    Text(
                        text = nickName,
                        color = ColorTextLight
                    )
                }
            }

            ButtonFollow(following = following) { followAction(!following) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardProfileListScreenPreview() {
    CardProfileListScreen(
        following = true,
        profileName = "sabrinasantana",
        nickName = "Sabrina Santana",
        followAction = {},
        showAction = {},
    )
}
