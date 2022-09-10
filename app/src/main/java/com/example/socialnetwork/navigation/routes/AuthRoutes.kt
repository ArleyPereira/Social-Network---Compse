package com.example.socialnetwork.navigation.routes

sealed class AuthRoutes(val route: String) {
    object LoginAuthRoutes : AuthRoutes("login_screen")
    object RegisterAuthRoutes : AuthRoutes("register_screen")
    object RecoverAccountAuthRoutes : AuthRoutes("recover_account_screen")
}
