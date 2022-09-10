package com.example.socialnetwork.navigation.routes

sealed class GraphRoutes(val route: String) {
    object RootGraph : GraphRoutes("root_graph")
    object AuthGraph : GraphRoutes("auth_graph")
    object HomeGraph : GraphRoutes("home_graph")
}