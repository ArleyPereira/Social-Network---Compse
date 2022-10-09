package com.example.socialnetwork.presenter.bottombar.search

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.socialnetwork.data.model.ErrorAPI
import com.example.socialnetwork.domain.model.User
import com.example.socialnetwork.presenter.auth.login.events.LoginUIEvent
import com.example.socialnetwork.presenter.bottombar.friends.event.FriendsEvent
import com.example.socialnetwork.presenter.bottombar.search.event.SearchUIEvent
import com.example.socialnetwork.presenter.components.BottomSheetScreen
import com.example.socialnetwork.presenter.components.CardProfileListScreen
import com.example.socialnetwork.presenter.components.SearchView
import com.example.socialnetwork.ui.theme.ColorBackgroundApp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val searchField = viewModel.searchField.value

    var apiError by remember { mutableStateOf(LoginUIEvent.LoginError(ErrorAPI(null))) }
    var isLoading by remember { mutableStateOf(false) }

    var users by remember { mutableStateOf(emptyList<User>()) }

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )

    val coroutineScope = rememberCoroutineScope()

    BackHandler(sheetState.isVisible) {
        coroutineScope.launch { sheetState.hide() }
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is SearchUIEvent.SearchLoading -> {
                    isLoading = true
                }
                is SearchUIEvent.SearchSucess -> {
                    users = event.users
                }
                is SearchUIEvent.SearchError -> {
                    isLoading = false

                    apiError = LoginUIEvent.LoginError(event.value)

                    coroutineScope.launch {
                        sheetState.animateTo(
                            ModalBottomSheetValue.Expanded,
                            tween(500)
                        )
                    }
                }
            }
        }
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            BottomSheetScreen(
                message = apiError.value.message,
                raiseHeight = true,
                onClickOk = {
                    coroutineScope.launch {
                        sheetState.hide()
                    }
                },
                onClickCancel = {
                    coroutineScope.launch { sheetState.hide() }
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            if (isLoading) {
                CircularProgressIndicator()
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(top = 16.dp, bottom = 60.dp)
            ) {
                item {
                    SearchView(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                            .alpha(if (isLoading) 0f else 1f),
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

                item {
                    Row(
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.White)
                    ) {

                    }
                }

                items(users) { user ->
                    CardProfileListScreen(
                        following = false,
                        profileName = "${user.firstName} ${user.lastName}",
                        nickName = user.nickName ?: "",
                        followAction = { follow ->

                        },
                        showAction = {

                        },
                    )
                }
            }

//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//            ) {
//                SearchView(
//                    modifier = Modifier
//                        .padding(start = 16.dp, end = 16.dp, top = 16.dp),
//                    hintText = searchField.hint,
//                    text = searchField.text,
//                    onTextChange = {
//                        viewModel.onEvent(FriendsEvent.EnteredSearch(it))
//                    },
//                    onClearText = {
//                        viewModel.onEvent(FriendsEvent.ClearTextSearch)
//                    }
//                )
//
//                LazyColumn(
//                    contentPadding = PaddingValues(top = 16.dp, bottom = 60.dp)
//                ) {
//                    items(users) { user ->
//                        CardProfileListScreen(
//                            following = false,
//                            profileName = "${user.firstName} ${user.lastName}",
//                            nickName = user.nickName ?: "",
//                            followAction = { follow ->
//
//                            },
//                            showAction = {
//
//                            },
//                        )
//                    }
//                }
//            }

        }
    }

}

@Preview
@Composable
fun SearchScreenPreview() {
    Column(
        modifier = Modifier
            .background(ColorBackgroundApp)
            .fillMaxSize()
    ) {
        SearchScreen(EmptyDestinationsNavigator)
    }
}