package com.example.socialnetwork.presenter.post

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.socialnetwork.data.model.ErrorAPI
import com.example.socialnetwork.presenter.auth.register.events.RegisterUIEvent
import com.example.socialnetwork.presenter.components.BottomSheetScreen
import com.example.socialnetwork.presenter.components.Toolbar
import com.example.socialnetwork.presenter.destinations.FeedScreenDestination
import com.example.socialnetwork.presenter.destinations.PostLikeDetailsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Destination
@Composable
fun PostLikeDetailsScreen(
    navigator: DestinationsNavigator,
    postId: Long
) {

    var error by remember { mutableStateOf(RegisterUIEvent.RegisterError(ErrorAPI())) }

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )

    val coroutineScope = rememberCoroutineScope()

    BackHandler {
        if (sheetState.isVisible) {
            coroutineScope.launch { sheetState.hide() }
        } else {
            navigator.navigate(FeedScreenDestination) {
                popUpTo(PostLikeDetailsScreenDestination) {
                    inclusive = true
                }
            }
        }
    }

    var isLoading by remember { mutableStateOf(false) }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            BottomSheetScreen(
                message = error.value.message,
                onClickOk = {
                    coroutineScope.launch { sheetState.hide() }
                },
                onClickCancel = {
                    coroutineScope.launch { sheetState.hide() }
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Toolbar(title = "Curtidas") { navigator.popBackStack() }

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Curtidas")
            }

        }
    }

}