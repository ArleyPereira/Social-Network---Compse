package com.example.socialnetwork.presenter.home

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.socialnetwork.navigation.BottomBar
import com.example.socialnetwork.presenter.NavGraphs
import com.example.socialnetwork.presenter.destinations.FeedScreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost

@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Composable
fun HomeScreen() {

    val navController = rememberNavController()

    val userLogged = true
    val startRoute = if (userLogged) FeedScreenDestination else NavGraphs.root.startRoute

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        DestinationsNavHost(
            navGraph = NavGraphs.root,
            navController = navController,
            startRoute = startRoute
        )
    }
}