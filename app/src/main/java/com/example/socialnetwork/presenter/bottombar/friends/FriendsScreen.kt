package com.example.socialnetwork.presenter.bottombar.friends

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.socialnetwork.data.model.UserDto
import com.example.socialnetwork.presenter.bottombar.friends.event.FriendsEvent
import com.example.socialnetwork.presenter.components.CardProfileListScreen
import com.example.socialnetwork.presenter.components.SearchView
import com.example.socialnetwork.ui.theme.ColorBackgroundApp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Destination
@Composable
fun FriendsScreen(
    navigator: DestinationsNavigator,
    viewModel: FriendsViewModel = hiltViewModel()
) {

    val searchField = viewModel.searchField.value

    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {


        LazyColumn(
            contentPadding = PaddingValues(top = 16.dp, bottom = 60.dp)
        ) {
            item {
                SearchView(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    hintText = searchField.hint,
                    text = searchField.text,
                    onTextChange = {
                        viewModel.onEvent(FriendsEvent.EnteredSearch(it))
                    },
                    onClearText = {
                        viewModel.onEvent(FriendsEvent.ClearTextSearch)
                    }
                )
            }

            val userDtos = listOf(
                UserDto(nickName = "arleysantana", lastName = "Arley Santana"),
                UserDto(nickName = "marcosalmeida", lastName = "Marcos Almeida"),
                UserDto(nickName = "jose.silva", lastName = "José Silva"),
                UserDto(nickName = "lucas_almeida", lastName = "Lucas Almeida"),
                UserDto(nickName = "luanrocha", lastName = "Luan Rocha"),
                UserDto(nickName = "rafael.reis", lastName = "Rafael Reis"),
                UserDto(nickName = "alissonsantana", lastName = "Alisson Santana"),
                UserDto(nickName = "arleypereira", lastName = "Arley Pereira"),
                UserDto(nickName = "arleysantana", lastName = "Arley Santana"),
                UserDto(nickName = "marcosalmeida", lastName = "Marcos Almeida"),
                UserDto(nickName = "jose.silva", lastName = "José Silva"),
                UserDto(nickName = "lucas_almeida", lastName = "Lucas Almeida"),
                UserDto(nickName = "luanrocha", lastName = "Luan Rocha"),
                UserDto(nickName = "rafael.reis", lastName = "Rafael Reis"),
                UserDto(nickName = "alissonsantana", lastName = "Alisson Santana"),
                UserDto(nickName = "arleypereira", lastName = "Arley Pereira"),
                UserDto(nickName = "arleysantana", lastName = "Arley Santana"),
                UserDto(nickName = "marcosalmeida", lastName = "Marcos Almeida"),
                UserDto(nickName = "jose.silva", lastName = "José Silva"),
                UserDto(nickName = "lucas_almeida", lastName = "Lucas Almeida"),
                UserDto(nickName = "luanrocha", lastName = "Luan Rocha"),
                UserDto(nickName = "rafael.reis", lastName = "Rafael Reis"),
                UserDto(nickName = "alissonsantana", lastName = "Alisson Santana"),
                UserDto(nickName = "arleypereira", lastName = "Arley Pereira"),
            )

            items(userDtos) { user ->
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