package com.example.socialnetwork.presenter.following

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun FollowingScreen(
    navigator: DestinationsNavigator,
    viewModel: FollowingViewModel = hiltViewModel()
) {

}