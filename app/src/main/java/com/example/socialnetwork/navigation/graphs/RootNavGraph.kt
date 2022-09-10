package com.example.socialnetwork.navigation.graphs

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.socialnetwork.navigation.routes.GraphRoutes
import com.example.socialnetwork.presenter.home.HomeScreen

@ExperimentalMaterial3Api
@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = GraphRoutes.RootGraph.route,
        startDestination = GraphRoutes.AuthGraph.route
    ) {
        authNavGraph(navController = navController)
        composable(route = GraphRoutes.HomeGraph.route) {
            HomeScreen()
        }
    }
}