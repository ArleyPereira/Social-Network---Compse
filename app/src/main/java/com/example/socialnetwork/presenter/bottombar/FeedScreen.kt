package com.example.socialnetwork.presenter.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.socialnetwork.data.model.Post
import com.example.socialnetwork.presenter.components.CardPostFeed
import com.example.socialnetwork.ui.theme.ColorBackgroundApp

@Composable
fun FeedScreen() {
    Box(
        modifier = Modifier
            .background(ColorBackgroundApp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        val posts = listOf(
            Post(
                nameProfile = "Arley Santana",
                description = "O que não falta nesse mundo são lugares belíssimos para se visitar, afinal, ele é imenso, não é mesmo?"
            ),
            Post(
                nameProfile = "Marcos Almeida",
                description = "O que não falta nesse mundo são lugares belíssimos para se visitar, afinal, ele é imenso, não é mesmo?"
            ),
            Post(
                nameProfile = "Arley Santana",
                description = "O que não falta nesse mundo são lugares belíssimos para se visitar, afinal, ele é imenso, não é mesmo?"
            ),
            Post(
                nameProfile = "Marcos Almeida",
                description = "O que não falta nesse mundo são lugares belíssimos para se visitar, afinal, ele é imenso, não é mesmo?"
            ),
            Post(
                nameProfile = "Arley Santana",
                description = "O que não falta nesse mundo são lugares belíssimos para se visitar, afinal, ele é imenso, não é mesmo?"
            ),
            Post(
                nameProfile = "Marcos Almeida",
                description = "O que não falta nesse mundo são lugares belíssimos para se visitar, afinal, ele é imenso, não é mesmo?"
            )
        )

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(posts) { post ->
                CardPostFeed(post = post)
            }
        }

    }
}

@Preview
@Composable
fun FeedScreenPreview() {
    Column(
        modifier = Modifier
            .background(ColorBackgroundApp)
            .fillMaxSize()
    ) {
        FeedScreen()
    }
}