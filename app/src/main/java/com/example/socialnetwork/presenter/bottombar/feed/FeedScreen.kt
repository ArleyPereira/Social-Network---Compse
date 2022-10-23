package com.example.socialnetwork.presenter.bottombar.feed

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.socialnetwork.presenter.bottombar.feed.event.FeedEvent
import com.example.socialnetwork.presenter.components.BottomSheetScreen
import com.example.socialnetwork.presenter.components.CardPostFeed
import com.example.socialnetwork.presenter.destinations.PostCommentScreenDestination
import com.example.socialnetwork.presenter.destinations.PostLikeDetailsScreenDestination
import com.example.socialnetwork.presenter.destinations.ProfileUserScreenDestination
import com.example.socialnetwork.ui.theme.ColorBackgroundApp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun FeedScreen(
    navigator: DestinationsNavigator,
    viewModel: FeedViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded })

    val coroutineScope = rememberCoroutineScope()

    BackHandler(sheetState.isVisible) {
        coroutineScope.launch { sheetState.hide() }
    }

    ModalBottomSheetLayout(
        sheetState = sheetState, sheetContent = {
            BottomSheetScreen(message = state.error?.message, raiseHeight = true, onClickOk = {
                coroutineScope.launch {
                    sheetState.hide()
                }
            }, onClickCancel = {
                coroutineScope.launch { sheetState.hide() }
            })
        }, modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
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

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(top = 16.dp, bottom = 60.dp)
            ) {
                items(state.posts) { post ->
                    CardPostFeed(
                        post = post
                    ) { interaction ->
                        when (interaction) {
                            is FeedEvent.NavPostCommentScreen -> {
                                navigator.navigate(PostCommentScreenDestination(interaction.postId))
                            }
                            is FeedEvent.NavPostLikeDetailsScreen -> {
                                navigator.navigate(PostLikeDetailsScreenDestination(interaction.postId))
                            }
                            is FeedEvent.NavProfileUserScreen -> {
                                navigator.navigate(ProfileUserScreenDestination(interaction.userId))
                            }
                        }
                    }
                }
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
        FeedScreen(EmptyDestinationsNavigator)
    }
}