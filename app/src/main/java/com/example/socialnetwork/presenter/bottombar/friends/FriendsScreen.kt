package com.example.socialnetwork.presenter.bottombar.friends

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.socialnetwork.data.model.User
import com.example.socialnetwork.presenter.components.CardProfileListScreen
import com.example.socialnetwork.ui.theme.ColorBackgroundApp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Destination
@Composable
fun FriendsScreen(
    navigator: DestinationsNavigator
) {
    LazyColumn(
        modifier = Modifier
            .background(Color.White),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        val users = listOf(
            User(nickName = "arleysantana", lastName = "Arley Santana"),
            User(nickName = "marcosalmeida", lastName = "Marcos Almeida"),
            User(nickName = "jose.silva", lastName = "José Silva"),
            User(nickName = "lucas_almeida", lastName = "Lucas Almeida"),
            User(nickName = "luanrocha", lastName = "Luan Rocha"),
            User(nickName = "rafael.reis", lastName = "Rafael Reis"),
            User(nickName = "alissonsantana", lastName = "Alisson Santana"),
            User(nickName = "arleypereira", lastName = "Arley Pereira"),
            User(nickName = "arleysantana", lastName = "Arley Santana"),
            User(nickName = "marcosalmeida", lastName = "Marcos Almeida"),
            User(nickName = "jose.silva", lastName = "José Silva"),
            User(nickName = "lucas_almeida", lastName = "Lucas Almeida"),
            User(nickName = "luanrocha", lastName = "Luan Rocha"),
            User(nickName = "rafael.reis", lastName = "Rafael Reis"),
            User(nickName = "alissonsantana", lastName = "Alisson Santana"),
            User(nickName = "arleypereira", lastName = "Arley Pereira"),
            User(nickName = "arleysantana", lastName = "Arley Santana"),
            User(nickName = "marcosalmeida", lastName = "Marcos Almeida"),
            User(nickName = "jose.silva", lastName = "José Silva"),
            User(nickName = "lucas_almeida", lastName = "Lucas Almeida"),
            User(nickName = "luanrocha", lastName = "Luan Rocha"),
            User(nickName = "rafael.reis", lastName = "Rafael Reis"),
            User(nickName = "alissonsantana", lastName = "Alisson Santana"),
            User(nickName = "arleypereira", lastName = "Arley Pereira"),
        )

        items(users) { user ->
            CardProfileListScreen(
                following = false,
                profileName = user.lastName!!,
                nickName = user.nickName!!,
                followAction = { follow ->

                },
                showAction = {

                },
            )
        }
    }
}

@Preview
@Composable
fun FriendsScreenPreview() {
    Column(
        modifier = Modifier
            .background(ColorBackgroundApp)
            .fillMaxSize()
    ) {
        FriendsScreen(EmptyDestinationsNavigator)
    }
}