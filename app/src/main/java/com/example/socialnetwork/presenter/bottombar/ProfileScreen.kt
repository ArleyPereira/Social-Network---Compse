package com.example.socialnetwork.presenter.bottombar

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.socialnetwork.presenter.destinations.EditProfileScreenDestination
import com.example.socialnetwork.ui.theme.ColorBackgroundApp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Destination
@Composable
fun ProfileScreen(navigator: DestinationsNavigator) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Perfil",
            modifier = Modifier
                .clickable { navigator.navigate(EditProfileScreenDestination("Arley")) }
        )
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    Column(
        modifier = Modifier
            .background(ColorBackgroundApp)
            .fillMaxSize()
    ) {
        ProfileScreen(EmptyDestinationsNavigator)
    }
}