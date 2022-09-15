package com.example.socialnetwork.navigation.routes

sealed class BottomBarRoutes(val route: String) {
    object Feed : BottomBarRoutes("feed_route")
    object Search : BottomBarRoutes("search_route")
    object Friends : BottomBarRoutes("friends_route")
    object Profile : BottomBarRoutes("profile_route")
}
