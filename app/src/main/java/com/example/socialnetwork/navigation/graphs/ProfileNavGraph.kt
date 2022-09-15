package com.example.socialnetwork.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.socialnetwork.navigation.routes.AuthRoutes
import com.example.socialnetwork.navigation.routes.GraphRoutes
import com.example.socialnetwork.navigation.routes.ProfileRoutes
import com.example.socialnetwork.presenter.bottombar.profile.EditProfileScreen

fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    navigation(
        route = GraphRoutes.ProfileGraph.route,
        startDestination = ProfileRoutes.EditProfileRoutes.route
    ) {
        composable(route = ProfileRoutes.EditProfileRoutes.route) {
            EditProfileScreen()
        }
    }
}