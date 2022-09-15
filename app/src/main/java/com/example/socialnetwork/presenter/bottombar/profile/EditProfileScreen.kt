package com.example.socialnetwork.presenter.bottombar.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.socialnetwork.ui.theme.ColorBackgroundApp

@Composable
fun EditProfileScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "EditProfileScreen")
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
        EditProfileScreen()
    }
}