package com.example.socialnetwork.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.socialnetwork.navigation.routes.GraphRoutes
import com.example.socialnetwork.presenter.bottombar.BottomNavItem
import com.example.socialnetwork.presenter.bottombar.FeedScreen
import com.example.socialnetwork.presenter.bottombar.FriendsScreen
import com.example.socialnetwork.presenter.bottombar.ProfileScreen
import com.example.socialnetwork.presenter.bottombar.SearchScreen

@Composable
fun BottomBarNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = GraphRoutes.HomeGraph.route,
        startDestination = BottomNavItem.Feed.route
    ) {
        composable(route = BottomNavItem.Feed.route) {
            FeedScreen()
        }
        composable(route = BottomNavItem.Search.route) {
            SearchScreen()
        }
        composable(route = BottomNavItem.Friends.route) {
            FriendsScreen()
        }
        composable(route = BottomNavItem.Profile.route) {
            ProfileScreen()
        }
    }
}