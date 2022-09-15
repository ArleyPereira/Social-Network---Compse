package com.example.socialnetwork.presenter.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.socialnetwork.ui.theme.ColorBackgroundApp

@Composable
fun CardPostFeed(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorBackgroundApp)
    ) {

        Card(
            modifier = Modifier
                .padding(16.dp),
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
                            Text(text = "Sabrina Santana")
                            Text(text = "Há 15 min")
                        }
                    }

                    Image(
                        painter = painterResource(id = R.drawable.ic_more),
                        contentDescription = "",
                        modifier = modifier.size(32.dp)
                    )

                }

                Text(
                    text = "O que não falta nesse mundo são lugares belíssimos para se visitar, afinal, ele é imenso, não é mesmo?",
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.image_feed_place_holder),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(16.dp)
                        .shadow(
                            elevation = 5.dp,
                            shape = RoundedCornerShape(10.dp)
                        )
                )

            }
        }

    }

}

@Preview
@Composable
fun CardPostFeedPreview() {
    CardPostFeed()
}