package com.example.socialnetwork.presenter.bottombar.search

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.socialnetwork.presenter.bottombar.search.event.SearchEvent
import com.example.socialnetwork.presenter.components.BottomSheetScreen
import com.example.socialnetwork.presenter.components.CardProfileListScreen
import com.example.socialnetwork.presenter.components.SearchView
import com.example.socialnetwork.presenter.destinations.*
import com.example.socialnetwork.ui.theme.ColorBackgroundApp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val searchField = viewModel.searchField.value

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )

    val coroutineScope = rememberCoroutineScope()

    BackHandler(sheetState.isVisible) {
        coroutineScope.launch { sheetState.hide() }
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            BottomSheetScreen(
                message = state.error?.message,
                raiseHeight = true,
                onClickOk = {
                    coroutineScope.launch {
                        viewModel.onEvent(SearchEvent.ClickedButtonSheetError)
                        sheetState.hide()
                    }
                },
                onClickCancel = {
                    coroutineScope.launch {
                        viewModel.onEvent(SearchEvent.ClickedButtonSheetError)
                        sheetState.hide()
                    }
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
            state.error?.let {
                coroutineScope.launch {
                    sheetState.animateTo(
                        ModalBottomSheetValue.Expanded, tween(500)
                    )
                }
            }

            if (state.isLoading) {
                CircularProgressIndicator()
            }

            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(top = 16.dp, bottom = 60.dp, start = 8.dp, end = 8.dp),
                columns = GridCells.Fixed(1)
            ) {
                item {
                    SearchView(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                            .alpha(if (state.isLoading) 0f else 1f),
                        hintText = searchField.hint,
                        text = searchField.text,
                        onTextChange = {
                            viewModel.onEvent(SearchEvent.EnteredSearch(it))
                        },
                        onClearText = {
                            viewModel.onEvent(SearchEvent.ClearTextSearch)
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

                items(state.users.size) {
                    if(state.users[it].id != state.userLocal?.id){
                        CardProfileListScreen(
                            following = false,
                            user = state.users[it],
                            showFollowAction = state.users[it].id != state.userLocal?.id,
                            followAction = { followedId ->
                                viewModel.onEvent(
                                    SearchEvent.FollowUser(
                                        userId = state.userLocal?.id ?: 0,
                                        followedId = followedId
                                    )
                                )
                            },
                            showAction = { userId ->
                                if (state.users[it].id != state.userLocal?.id) {
                                    navigator.navigate(ProfileUserScreenDestination(userId))
                                } else {
                                    navigator.navigate(ProfileScreenDestination())
                                }
                            },
                        )
                    }
                }

            }

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